package me.nattapon.jpa.repos;

import java.util.UUID;
import me.nattapon.jpa.domain.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, UUID> {
}
