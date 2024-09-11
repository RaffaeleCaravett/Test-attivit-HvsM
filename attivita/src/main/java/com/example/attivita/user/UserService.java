package com.example.attivita.user;

import com.example.attivita.payloads.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User putById(long id, UserDTO userDTO){

    }
}
