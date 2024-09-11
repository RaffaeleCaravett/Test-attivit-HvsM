package com.example.attivita.auth;

import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.payloads.entities.UserDTO;
import com.example.attivita.payloads.entities.UserLoginDTO;
import com.example.attivita.tokens.Tokens;
import com.example.attivita.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("")
    public User register(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return authService.register(userDTO);
    }
    @PostMapping("/login")
    public Tokens login (@RequestBody @Validated UserLoginDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return authService.login(userDTO);
    }
    @GetMapping("/verifyToken/{token}")
    public User verifyToken(@PathVariable String token){
        return authService.verifyToken(token);
    }
    @GetMapping("/verifyRefreshToken/{token}")
    public Tokens verifyRefreshToken(@PathVariable String token){
        return authService.verifyRefreshToken(token);
    }
}
