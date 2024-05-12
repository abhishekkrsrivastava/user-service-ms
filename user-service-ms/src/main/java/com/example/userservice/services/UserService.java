package com.example.userservice.services;

import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;

import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User addNewUser(User user){
       User userSaved = (userRepository.save(user));

        return userSaved;
    }

    public User getUser(int userId)  {
        System.out.println("get user");
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found with id "+ userId));
    }

    public User updateAccountStatus(int userId, double usedAmount)  {
        User userDetailsFromDb= getUser(userId);
        userDetailsFromDb.setAvailableAmount(userDetailsFromDb.getAvailableAmount() - usedAmount);

        return userRepository.save(userDetailsFromDb);
    }
}
