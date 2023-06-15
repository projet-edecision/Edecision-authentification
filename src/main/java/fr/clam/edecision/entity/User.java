package fr.clam.edecision.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class User {

    @Id
    private UUID uuid;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    private UserType type;

    private String token;

    public User() {
        this.uuid = UUID.randomUUID();
    }

    public User(String username, String password, String email, UserType type) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.token = "";
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
