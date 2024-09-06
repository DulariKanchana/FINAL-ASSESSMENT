package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public int addUser(User user){

        return userRepo.save(user).getId();
    }

    public List<User> getAllUsers() {

        return userRepo.findAll();
    }
}


