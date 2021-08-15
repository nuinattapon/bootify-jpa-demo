package me.nattapon.jpa.service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import me.nattapon.jpa.domain.Course;
import me.nattapon.jpa.domain.Student;
import me.nattapon.jpa.domain.StudentIdCard;
import me.nattapon.jpa.model.StudentDTO;
import me.nattapon.jpa.repos.CourseRepository;
import me.nattapon.jpa.repos.StudentIdCardRepository;
import me.nattapon.jpa.repos.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentIdCardRepository studentIdCardRepository;

    public StudentService(final StudentRepository studentRepository,
            final CourseRepository courseRepository,
            final StudentIdCardRepository studentIdCardRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentIdCardRepository = studentIdCardRepository;
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> mapToDTO(student, new StudentDTO()))
                .collect(Collectors.toList());
    }

    public StudentDTO get(final UUID id) {
        return studentRepository.findById(id)
                .map(student -> mapToDTO(student, new StudentDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final StudentDTO studentDTO) {
        final Student student = new Student();
        mapToEntity(studentDTO, student);
        return studentRepository.save(student).getId();
    }

    public void update(final UUID id, final StudentDTO studentDTO) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(studentDTO, student);
        studentRepository.save(student);
    }

    public void delete(final UUID id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(final Student student, final StudentDTO studentDTO) {
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAge(student.getAge());
        studentDTO.setStudentCourses(student.getStudentCourseCourses() == null ? null : student.getStudentCourseCourses().stream()
                .map(Course::getId)
                .collect(Collectors.toList()));
        studentDTO.setStudentIdCard(student.getStudentIdCard() == null ? null : student.getStudentIdCard().getId());
        return studentDTO;
    }

    private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        if (studentDTO.getStudentCourses() != null) {
            final List<Course> studentCourses = courseRepository.findAllById(studentDTO.getStudentCourses());
            if (studentCourses.size() != studentDTO.getStudentCourses().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of studentCourses not found");
            }
            student.setStudentCourseCourses(new HashSet<>(studentCourses));
        }
        if (studentDTO.getStudentIdCard() != null && (student.getStudentIdCard() == null || !student.getStudentIdCard().getId().equals(studentDTO.getStudentIdCard()))) {
            final StudentIdCard studentIdCard = studentIdCardRepository.findById(studentDTO.getStudentIdCard())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "studentIdCard not found"));
            student.setStudentIdCard(studentIdCard);
        }
        return student;
    }

}
