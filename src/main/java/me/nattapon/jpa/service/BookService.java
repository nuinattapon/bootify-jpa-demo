package me.nattapon.jpa.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import me.nattapon.jpa.domain.Book;
import me.nattapon.jpa.domain.Student;
import me.nattapon.jpa.model.BookDTO;
import me.nattapon.jpa.repos.BookRepository;
import me.nattapon.jpa.repos.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public BookService(final BookRepository bookRepository,
            final StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapToDTO(book, new BookDTO()))
                .collect(Collectors.toList());
    }

    public BookDTO get(final UUID id) {
        return bookRepository.findById(id)
                .map(book -> mapToDTO(book, new BookDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final BookDTO bookDTO) {
        final Book book = new Book();
        mapToEntity(bookDTO, book);
        return bookRepository.save(book).getId();
    }

    public void update(final UUID id, final BookDTO bookDTO) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(bookDTO, book);
        bookRepository.save(book);
    }

    public void delete(final UUID id) {
        bookRepository.deleteById(id);
    }

    private BookDTO mapToDTO(final Book book, final BookDTO bookDTO) {
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setStudentBook(book.getStudentBook() == null ? null : book.getStudentBook().getId());
        return bookDTO;
    }

    private Book mapToEntity(final BookDTO bookDTO, final Book book) {
        book.setName(bookDTO.getName());
        if (bookDTO.getStudentBook() != null && (book.getStudentBook() == null || !book.getStudentBook().getId().equals(bookDTO.getStudentBook()))) {
            final Student studentBook = studentRepository.findById(bookDTO.getStudentBook())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "studentBook not found"));
            book.setStudentBook(studentBook);
        }
        return book;
    }

}
