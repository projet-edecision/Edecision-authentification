package fr.clam.edecision.controller;

import fr.clam.edecision.entity.User;
import fr.clam.edecision.entity.UserType;
import fr.clam.edecision.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private  final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    String test() {
        return "Hello World!";
    }

    @PostMapping("/login")
    String login(@RequestBody Map<String, String> body) {
        Optional<User> user;
        if (body.containsKey("username")) {
            user = repository.loginUsername(body.get("username"));
        } else {
            user = repository.loginEmail(body.get("email"));
        }
        String password = body.get("password");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user.isPresent() && encoder.matches(password, user.get().getPassword())) {
            return user.get().getUuid().toString();
        }
        return "connexion failed";
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

}
