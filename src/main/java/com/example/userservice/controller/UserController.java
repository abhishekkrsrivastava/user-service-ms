package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotCreatedException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private int port;


    @PostMapping("/save")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {

        System.out.println("user called");
        Optional<User> reguser = Optional.ofNullable(userService.addNewUser(user));

        if (reguser.isEmpty()) {
            throw new UserNotCreatedException("User Not Created with the id: " + user.getName());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        //  log.info("UserController::getUser request fetching user by id  {} ", userId);
        System.out.println("request is landed on port : " + port);
        User user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException("User not Found with Id: " + userId);
        }


        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/{userId}/{amount}")
    public ResponseEntity<User> updateUserBalance(@PathVariable int userId, @PathVariable double amount) {
        User user = userService.updateAccountStatus(userId, amount);
        if(user == null){
            throw new UserNotUpdatedException("User Not Updated with id: "+userId);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}