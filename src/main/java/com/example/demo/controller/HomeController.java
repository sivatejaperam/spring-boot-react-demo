package com.example.demo.controller;

import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
public class HomeController {

    private UserRepository userRepository;
    private UserService userService;
    private CacheManager cacheManager;

    @GetMapping("/")
    public String hello() {
        return ("Hello user");
    }

    @GetMapping("/normal")
    public String normalMethod() {
        return ("Hello Normal");
    }

    @GetMapping("/admin")
    public String adminMethod() {
        return ("Hello Admin");
    }

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam(required = false) Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo != null ? pageNo -1  : 0, 2, Sort.by("id"));
        return userRepository.findAll(pageable).getContent();
    }

    @GetMapping("/find/{userId}")
    @Cacheable(value = "userCache", key = "#userId")
    public User findBYid(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @GetMapping("/update/{userId}/{name}")
    @CacheEvict(value = "userCache", key = "#userId")
    public User updateUser(@PathVariable Long userId, @PathVariable String name){
        User user = userRepository.findById(userId).orElse(null);
        user.setName(name);
        return userRepository.save(user);
    }

  
    @GetMapping("/user/save")
    public User saveUser(@RequestBody(required = false) User user) {
        return userService.saveUser();
    }

  

}
