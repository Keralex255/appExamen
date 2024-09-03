package org.example.appexamen.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository repository) {
        return args -> {
            User dan = new User();
            dan.setUsername("dannie");
            dan.setPassword("pwd");
            dan.setActive(false);
            dan.setBuyer(true);
            repository.save(dan);
        };
    }
}
