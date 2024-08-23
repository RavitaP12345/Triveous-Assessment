package com.Triveous.E_Commerce_API.services;

import com.Triveous.E_Commerce_API.entities.User;
import com.Triveous.E_Commerce_API.models.UserModel;
import com.Triveous.E_Commerce_API.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    EntityManager em;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found !"));

    }

    public ResponseEntity<?> saveUserDetails(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        userRepository.save(user);
        return new ResponseEntity<>("Saved successfully.", HttpStatus.OK);
    }

}
