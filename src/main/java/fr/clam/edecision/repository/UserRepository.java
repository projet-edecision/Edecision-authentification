package fr.clam.edecision.repository;

import fr.clam.edecision.entity.User;
import fr.clam.edecision.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);

    List<User> findByType(UserType type);
}
