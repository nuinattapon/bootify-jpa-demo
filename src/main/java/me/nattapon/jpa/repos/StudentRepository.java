package me.nattapon.jpa.repos;

import java.util.List;
import java.util.UUID;
import me.nattapon.jpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("select s from Student s where s.firstName like ?1 or s.lastName like ?2 order by s.firstName, s.lastName")
    List<Student> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

    void deleteById(String id);

    @Query("select (count(s) > 0) from Student s where s.firstName = ?1 and s.lastName = ?2")
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByIdEquals(UUID id);


}
