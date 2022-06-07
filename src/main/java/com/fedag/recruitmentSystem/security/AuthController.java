package com.fedag.recruitmentSystem.security;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.security.jwt.JwtUtils;
import com.fedag.recruitmentSystem.security.pojo.JwtResopnse;
import com.fedag.recruitmentSystem.security.pojo.LoginRequest;
import com.fedag.recruitmentSystem.security.pojo.MessageResponse;
import com.fedag.recruitmentSystem.security.pojo.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResopnse(jwt,
                userDetails.getId(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                userDetails.getEmail(),
                userDetails.getRole()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> responseUser(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        User user = new User(signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getEmail(),
                signupRequest.getBirthday(),
                signupRequest.getRole(),
                passwordEncoder.encode(signupRequest.getPassword()));

//        Set<String> reqRoles = signupRequest.getRoles();
//        Set<Role> roles = new HashSet<>();
//
//        if (reqRoles == null) {
//            Role userRole = roleRepository
//                    .findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
//            roles.add(userRole);
//        } else {
//            reqRoles.forEach(r -> {
//                switch (r) {
//                    case "admin":
//                        Role adminRole = roleRepository
//                                .findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository
//                                .findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
//                        roles.add(modRole);
//
//                        break;
//
//                    default:
//                        Role userRole = roleRepository
//                                .findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
//                        roles.add(userRole);
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}

