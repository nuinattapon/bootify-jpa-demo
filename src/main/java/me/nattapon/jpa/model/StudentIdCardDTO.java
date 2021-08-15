package me.nattapon.jpa.model;

import java.util.UUID;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentIdCardDTO {

    private UUID id;

    @Size(max = 255)
    private String cardNumber;

}
