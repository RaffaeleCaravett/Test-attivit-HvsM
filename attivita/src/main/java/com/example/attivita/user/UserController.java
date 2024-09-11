package com.example.attivita.user;

import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.payloads.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PutMapping("/me")
    public User putById (@AuthenticationPrincipal User user, @RequestBody @Validated UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return userService.putById(user.getId(),userDTO);
    }
    @DeleteMapping("/me")
    public boolean putById (@AuthenticationPrincipal User user){
        return userService.deleteById(user.getId());
    }
}
