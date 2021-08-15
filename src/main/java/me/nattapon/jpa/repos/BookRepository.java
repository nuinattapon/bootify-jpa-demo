package me.nattapon.jpa.repos;

import java.util.UUID;
import me.nattapon.jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, UUID> {
}
