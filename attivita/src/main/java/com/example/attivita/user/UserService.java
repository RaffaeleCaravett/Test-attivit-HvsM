package com.example.attivita.user;

import com.example.attivita.enums.Role;
import com.example.attivita.exceptions.BadRequestException;
import com.example.attivita.exceptions.EmailAlreadyExistsException;
import com.example.attivita.exceptions.UserNotFoundException;
import com.example.attivita.payloads.entities.UserDTO;
import com.example.attivita.prenotazione.Prenotazione;
import com.example.attivita.prenotazione.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    public User putById(long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User con id " + id + " non trovato in Database."));
        if (userRepository.findByEmail(userDTO.email()).isPresent() && !userDTO.email().equals(user.getEmail())) {
            throw new EmailAlreadyExistsException("L'email " + userDTO.email() + " è già utilizzata da qualcuno!");
        }
        user.setNome(userDTO.nome());
        user.setCognome(userDTO.cognome());
        user.setEmail(userDTO.email());

        return userRepository.save(user);
    }

    public boolean deleteById(long id){
        try {

            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User con id " + id + " non trovato in Database."));
            for(Prenotazione prenotazione : user.getPrenotazioneList()){
                prenotazioneRepository.deleteById(prenotazione.getId());
            }
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public User findById(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User con id " + id + " non trovato in Database."));
        return user;
    }
}
