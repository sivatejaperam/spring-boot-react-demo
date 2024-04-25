package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.repo.AddressRepository;
import com.example.demo.repo.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    AddressRepository addressRepository;

    @Transactional
    public User saveUser() {

        User u1 = new User(null, "test11", "test11@test.com", true, LocalDateTime.now(), LocalDateTime.now(), null);
        User u = userRepository.save(u1);
        this.saveAddress();
        return u;
    }

    public void saveAddress() {
        try {
            Address address = new Address(null, null);
            addressRepository.save(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
