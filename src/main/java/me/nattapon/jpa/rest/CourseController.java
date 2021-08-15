package me.nattapon.jpa.rest;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import me.nattapon.jpa.model.CourseDTO;
import me.nattapon.jpa.service.CourseService;
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
@RequestMapping(value = "/api/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;

    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable final UUID id) {
        return ResponseEntity.ok(courseService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createCourse(@RequestBody @Valid final CourseDTO courseDTO) {
        return new ResponseEntity<>(courseService.create(courseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable final UUID id,
            @RequestBody @Valid final CourseDTO courseDTO) {
        courseService.update(id, courseDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable final UUID id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
