package fr.clam.edecision.controller;

import fr.clam.edecision.entity.User;
import fr.clam.edecision.entity.UserType;
import fr.clam.edecision.repository.UserRepository;
import fr.clam.edecision.service.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private  final UserRepository repository;

    private final JwtService jwtService;

    UserController(UserRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    Object login(@RequestBody Map<String, String> body) {
        List<User> users = repository.findByUsername(body.get("username"));

        if(users.size() == 0) {
            users = repository.findByEmail(body.get("email"));
        }

        if(users.size() == 1) {
            User user = users.get(0);
            String password = body.get("password");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                user.setToken(token);
                return user;
            }
        }else if (users.size() == 0) {
            return "user not found";
        }

        return "password incorrect";
    }

    @PostMapping("/register")
    User newUser(@RequestBody User newUser) {
     String encodingPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
     newUser.setPassword(encodingPassword);
    return repository.save(newUser);
    }

    @GetMapping("/user/{uuid}")
    Optional<User> getUserById(@PathVariable UUID uuid) {
        return repository.findById(uuid);
    }

    @GetMapping("/findUserByType/{type}")
    Iterable<User> getUserByType(@PathVariable String type) {
        return repository.findByType(UserType.valueOf(type));
    }

    @GetMapping("/users")
    Iterable<User> getAllUsers() {
        return repository.findAll();
    }

}
