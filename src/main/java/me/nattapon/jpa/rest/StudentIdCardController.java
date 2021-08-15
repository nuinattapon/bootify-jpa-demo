package me.nattapon.jpa.rest;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import me.nattapon.jpa.model.StudentIdCardDTO;
import me.nattapon.jpa.service.StudentIdCardService;
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
@RequestMapping(value = "/api/studentIdCards", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentIdCardController {

    private final StudentIdCardService studentIdCardService;

    public StudentIdCardController(final StudentIdCardService studentIdCardService) {
        this.studentIdCardService = studentIdCardService;
    }

    @GetMapping
    public ResponseEntity<List<StudentIdCardDTO>> getAllStudentIdCards() {
        return ResponseEntity.ok(studentIdCardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentIdCardDTO> getStudentIdCard(@PathVariable final UUID id) {
        return ResponseEntity.ok(studentIdCardService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createStudentIdCard(
            @RequestBody @Valid final StudentIdCardDTO studentIdCardDTO) {
        return new ResponseEntity<>(studentIdCardService.create(studentIdCardDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudentIdCard(@PathVariable final UUID id,
            @RequestBody @Valid final StudentIdCardDTO studentIdCardDTO) {
        studentIdCardService.update(id, studentIdCardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentIdCard(@PathVariable final UUID id) {
        studentIdCardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
