package me.nattapon.jpa;

import me.nattapon.jpa.model.StudentDTO;
import me.nattapon.jpa.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;


@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentService service) {
        return args -> {
            StudentDTO s = new StudentDTO("Nui", "Sub-Anake", "nui@test.com", 49);
            System.out.println(s);

            UUID id = service.create(s);

            System.out.println(id.toString());

            s = new StudentDTO("Somboon", "Dansirichaiwasat", "danso01@test.com", 48);
            service.update(id, s);

            StudentDTO entity = service.get(id);

            System.out.println(entity);

        };
    }
}
