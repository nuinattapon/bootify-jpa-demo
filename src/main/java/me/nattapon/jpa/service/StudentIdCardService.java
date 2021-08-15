package me.nattapon.jpa.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import me.nattapon.jpa.domain.StudentIdCard;
import me.nattapon.jpa.model.StudentIdCardDTO;
import me.nattapon.jpa.repos.StudentIdCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StudentIdCardService {

    private final StudentIdCardRepository studentIdCardRepository;

    public StudentIdCardService(final StudentIdCardRepository studentIdCardRepository) {
        this.studentIdCardRepository = studentIdCardRepository;
    }

    public List<StudentIdCardDTO> findAll() {
        return studentIdCardRepository.findAll()
                .stream()
                .map(studentIdCard -> mapToDTO(studentIdCard, new StudentIdCardDTO()))
                .collect(Collectors.toList());
    }

    public StudentIdCardDTO get(final UUID id) {
        return studentIdCardRepository.findById(id)
                .map(studentIdCard -> mapToDTO(studentIdCard, new StudentIdCardDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final StudentIdCardDTO studentIdCardDTO) {
        final StudentIdCard studentIdCard = new StudentIdCard();
        mapToEntity(studentIdCardDTO, studentIdCard);
        return studentIdCardRepository.save(studentIdCard).getId();
    }

    public void update(final UUID id, final StudentIdCardDTO studentIdCardDTO) {
        final StudentIdCard studentIdCard = studentIdCardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(studentIdCardDTO, studentIdCard);
        studentIdCardRepository.save(studentIdCard);
    }

    public void delete(final UUID id) {
        studentIdCardRepository.deleteById(id);
    }

    private StudentIdCardDTO mapToDTO(final StudentIdCard studentIdCard,
            final StudentIdCardDTO studentIdCardDTO) {
        studentIdCardDTO.setId(studentIdCard.getId());
        studentIdCardDTO.setCardNumber(studentIdCard.getCardNumber());
        return studentIdCardDTO;
    }

    private StudentIdCard mapToEntity(final StudentIdCardDTO studentIdCardDTO,
            final StudentIdCard studentIdCard) {
        studentIdCard.setCardNumber(studentIdCardDTO.getCardNumber());
        return studentIdCard;
    }

}
