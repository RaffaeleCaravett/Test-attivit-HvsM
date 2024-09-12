package com.example.attivita.auth;

import com.example.attivita.enums.Role;
import com.example.attivita.exceptions.*;
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


    public User register(UserDTO userDTO){
        if(userRepository.findByEmail(userDTO.email()).isPresent()){
            throw new EmailAlreadyExistsException("Email " + userDTO.email() + " già in uso.");
        }
        User user = new User();
        user.setNome(userDTO.nome());
        user.setCognome(userDTO.cognome());
        user.setEmail(userDTO.email());
        user.setPassword(bcrypt.encode(userDTO.password()));
        user.setRole(Role.user);
        return userRepository.save(user);
    }

    public Tokens login (UserLoginDTO userLoginDTO){
        User user = userRepository.findByEmail(userLoginDTO.email()).orElseThrow(()-> new UserNotFoundException("User con email "+ userLoginDTO.email() + " non presente in db."));
        if(bcrypt.matches(userLoginDTO.password(), user.getPassword())){
            return jwtTools.createTokens(user);
        }
        throw new PasswordMismatchException("La password che hai inserito non coincide con quella che abbiamo in Database.");
    }

    public User verifyToken(String accessToken){
        try{
           return jwtTools.verifyToken(accessToken);
        }catch (Exception e){
            throw new AccessTokenInvalidException("Il token non è valido.");
        }
    }
    public Tokens verifyRefreshToken(String refreshToken){
        try{
            User user = jwtTools.verifyToken(refreshToken);
            return user.getTokens();
        }catch (Exception e){
            throw new AccessTokenInvalidException("Il token non è valido.");
        }
    }
}
