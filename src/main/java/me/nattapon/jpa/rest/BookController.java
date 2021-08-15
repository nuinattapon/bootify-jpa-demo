package me.nattapon.jpa.rest;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import me.nattapon.jpa.model.BookDTO;
import me.nattapon.jpa.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable final UUID id) {
        return ResponseEntity.ok(bookService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createBook(@RequestBody @Valid final BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.create(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable final UUID id,
            @RequestBody @Valid final BookDTO bookDTO) {
        bookService.update(id, bookDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable final UUID id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
