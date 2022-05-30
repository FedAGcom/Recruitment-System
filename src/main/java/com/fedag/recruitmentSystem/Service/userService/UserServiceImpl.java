package com.fedag.recruitmentSystem.Service.userService;

import com.fedag.recruitmentSystem.Dao.UserRepository;
import com.fedag.recruitmentSystem.Service.userService.UserService;
import com.fedag.recruitmentSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        User user = null;
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
