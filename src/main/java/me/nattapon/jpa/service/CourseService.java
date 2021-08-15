package me.nattapon.jpa.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import me.nattapon.jpa.domain.Course;
import me.nattapon.jpa.model.CourseDTO;
import me.nattapon.jpa.repos.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(course -> mapToDTO(course, new CourseDTO()))
                .collect(Collectors.toList());
    }

    public CourseDTO get(final UUID id) {
        return courseRepository.findById(id)
                .map(course -> mapToDTO(course, new CourseDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final CourseDTO courseDTO) {
        final Course course = new Course();
        mapToEntity(courseDTO, course);
        return courseRepository.save(course).getId();
    }

    public void update(final UUID id, final CourseDTO courseDTO) {
        final Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(courseDTO, course);
        courseRepository.save(course);
    }

    public void delete(final UUID id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO mapToDTO(final Course course, final CourseDTO courseDTO) {
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDepartment(course.getDepartment());
        return courseDTO;
    }

    private Course mapToEntity(final CourseDTO courseDTO, final Course course) {
        course.setName(courseDTO.getName());
        course.setDepartment(courseDTO.getDepartment());
        return course;
    }

}
