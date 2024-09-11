package com.example.attivita.auth;

import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.exceptions.EmailAlreadyExistsException;
import com.example.attivita.exceptions.PasswordMismatchException;
import com.example.attivita.exceptions.UserNotFoundException;
import com.example.attivita.payloads.entities.UserDTO;
import com.example.attivita.payloads.entities.UserLoginDTO;
import com.example.attivita.security.JWTTools;
import com.example.attivita.tokens.Tokens;
import com.example.attivita.user.User;
import com.example.attivita.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder bcrypt;
    @Autowired
    JWTTools jwtTools;


    public User register(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        if(userRepository.findByEmail(userDTO.email()).isPresent()){
            throw new EmailAlreadyExistsException("Email " + userDTO.email() + " già in uso.");
        }
        User user = new User();
        user.setNome(userDTO.nome());
        user.setCognome(userDTO.cognome());
        user.setEmail(userDTO.email());
        user.setPassword(bcrypt.encode(userDTO.password()));
        return userRepository.save(user);
    }

    public Tokens login (@RequestBody @Validated UserLoginDTO userLoginDTO,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        User user = userRepository.findByEmail(userLoginDTO.email()).orElseThrow(()-> new UserNotFoundException("User con email "+ userLoginDTO.email() + " non presente in db."));
        if(bcrypt.matches(userLoginDTO.password(), user.getPassword())){
            return jwtTools.generateTokens(user.getTokens());
        }
        throw new PasswordMismatchException("La password che hai inserito non coincide con quella che abbiamo in Database.");
    }
}
