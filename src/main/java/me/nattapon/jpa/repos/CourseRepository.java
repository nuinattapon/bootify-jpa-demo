package me.nattapon.jpa.repos;

import java.util.UUID;
import me.nattapon.jpa.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, UUID> {
}
