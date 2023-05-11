package fr.clam.edecision;

import fr.clam.edecision.entity.User;
import fr.clam.edecision.entity.UserType;
import fr.clam.edecision.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LoadDatabase {

    private static  final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDataBase(UserRepository repository) {
        return args -> {
            User user = new User("test", "test", "test@test.fr", UserType.DEV);
            log.info("Preloading " + repository.save(user));
            log.info(user.getUuid().toString());
            log.info("Preloading " + repository.save(new User("test32", "test", "test@test.fr", UserType.DEV)));
            log.info("Preloading " + repository.save(new User("test2", "test2", "test2@test.fr", UserType.DEV)));
        };
    }


}
