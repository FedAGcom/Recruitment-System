package com.fedag.recruitmentSystem.Controllers;

import com.fedag.recruitmentSystem.Service.userService.UserService;
import com.fedag.recruitmentSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> allUsers = userService.findAllUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        User user = userService.findUserById(id);
        return user;
    }

    @PostMapping("/users")
    public void addNewUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
       userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
      userService.deleteUserById(id);
    }


}
