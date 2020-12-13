package com.example.spring.webflux.controller;

import com.example.spring.webflux.bean.User;
import com.example.spring.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }
}
