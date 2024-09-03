package org.example.appexamen;

import org.example.appexamen.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication

public class AppExamenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppExamenApplication.class, args);
    }


}
