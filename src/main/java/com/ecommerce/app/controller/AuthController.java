package com.ecommerce.app.controller;

import com.ecommerce.app.dto.AuthRequest;
import com.ecommerce.app.dto.AuthResponse;
import com.ecommerce.app.dto.RegisterRequest;
import com.ecommerce.app.repository.UserRepository;
//import com.ecommerce.app.service.UserDetailsService;
import com.ecommerce.app.service.UserDetailsServiceImpl;
import com.ecommerce.app.util.JwtUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.ecommerce.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Data
public class AuthController {


    private final UserRepository userRepository;

   private final JwtUtil jwtUtil;

   private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;



    @PostMapping("/register")
     public String register(@RequestBody RegisterRequest registerRequest){
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            return "user alredy existed";
        }
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            return  "email already existed";
        }
         User user = new User();
         user.setUsername(registerRequest.getUsername());
         user.setEmail(registerRequest.getEmail());
         user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
         user.setRoles(Collections.singleton("USER"));  // if you're storing roles as Set<String>
         userRepository.save(user);
         return "User registered successfully";
     }

     @PostMapping("/login")
     public AuthResponse Login(@RequestBody AuthRequest request){
         authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         request.getUsername(),
                         request.getPassword()
                 )
         );

         UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
         String token = jwtUtil.generateToken(userDetails);
         return new AuthResponse(token);
     }
}
