package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.dtos.UpdateUser;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://angular-app-frontend-sdfgsfdgsfgs.s3-website-us-west-1.amazonaws.com:4200"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId) {
        Optional<User> optional = userService.findById(userId);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(
                0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstname(),
                registerRequest.getLastname(),
                registerRequest.getAddress(),
                registerRequest.getPhone());

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody UpdateUser updateRequest) {
        System.out.println(updateRequest.toString());
        Optional<User> optional = userService.findById(updateRequest.getId());

        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(updateRequest));
    }
}