package me.nattapon.jpa;

import me.nattapon.jpa.model.StudentDTO;
import me.nattapon.jpa.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;


@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentService service) {
        return args -> {
            List<StudentDTO> all = service.findAll();

            for(StudentDTO studentDTO : all) {
                service.delete(studentDTO.getId());
            }
            StudentDTO s = new StudentDTO("Somsri", "Dansri", "somsri@test.com", 48);
            System.out.println("s = " + s);
            UUID id = service.create(s);
            System.out.println("id = " + id);

            s = new StudentDTO("Somboon", "Dansirichaisawat", "danso01@test.com", 48);
            System.out.println("s = " + s);
            id = service.create(s);
            System.out.println("id = " + id);

            s = new StudentDTO("Sam", "Dansirichai", "sam@test.com", 48);
            System.out.println("s = " + s);
            id = service.create(s);
            System.out.println("id = " + id);

            s = new StudentDTO("Nui", "Sub-Anake", "nui@test.com", 49);
            System.out.println("s = " + s);
            id = service.create(s);
            System.out.println("id = " + id);

            List<StudentDTO> entities = service.findByFirstNameLikeOrLastNameLike("S%", "Dansiri%");
//            entities.forEach(e-> {
//                System.out.println("e.getFirstName() = " + e.getFirstName());
//                System.out.println("e.getLastName() = " + e.getLastName());
//            });
            for(StudentDTO e : entities) {
                System.out.println("e.getFirstName() = " + e.getFirstName());
                System.out.println("e.getLastName() = " + e.getLastName());
            }
        };
    }
}
