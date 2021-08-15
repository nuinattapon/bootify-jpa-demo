package me.nattapon.jpa.repos;

import java.util.UUID;
import me.nattapon.jpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, UUID> {
}
