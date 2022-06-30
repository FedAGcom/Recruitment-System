package com.fedag.recruitmentSystem.security;

import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.model.EmailCode;
import com.fedag.recruitmentSystem.repository.EmailCodeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(UrlConstants.SECURITY_URL)
@Tag(name = "Контроллер безопасности", description = "Работа с безопасностью")
@RequiredArgsConstructor
public class AuthenticationRestControllerV1 {

    @Schema(name = "Менеджер аутентефикации")
    private final AuthenticationManager authenticationManager;

    @Schema(name = "Сервис безопасности", description = "Содержит методы проверки данных")
    private final SecurityService securityService;

    private final UserDetailsServiceImpl userDetailsService;
    private final EmailCodeRepository emailCodeRepository;

    @Operation(summary = "Ввод и проверка данных для аутентификации и авторизации пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "403", description = "Ошибка ввода данных",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        if(!securityService.isUserInActiveState(request.getEmail())
                && !securityService.isCompanyInActiveState(request.getEmail())) {
            return securityService.responseToReactivateAccount(request.getEmail());
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
            String token = securityService.definitionToken(request.getEmail());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login/code")
    public ResponseEntity<?> getLoginCode(@RequestBody AuthenticationRequestCode request) {
        if(!securityService.isUserInActiveState(request.getEmail())
                && !securityService.isCompanyInActiveState(request.getEmail())) {
            return securityService.responseToReactivateAccount(request.getEmail());
        }
        return securityService.responseToLoginCode(request.getEmail());
    }

    @PostMapping("/login/otp")
    public ResponseEntity<?> authenticateByLoginCode(@RequestBody AuthenticationRequestOTP request) {
        EmailCode emailCode = emailCodeRepository.findLoginCodeByCode(request.getCode())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "No login code found"));

        if(!emailCode.getEmail().equals(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong email for provided code");
        }

        if(!securityService.isUserInActiveState(emailCode.getEmail())
        && !securityService.isCompanyInActiveState(emailCode.getEmail())) {
            return securityService.responseToReactivateAccount(emailCode.getEmail());
        }

        String token = securityService.definitionToken(emailCode.getEmail());
        Map<Object, Object> response = new HashMap<>();
        response.put("email", emailCode.getEmail());
        response.put("token", token);
        emailCodeRepository.delete(emailCode);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Выход из учетной записи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выход выполнен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler =
                new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
