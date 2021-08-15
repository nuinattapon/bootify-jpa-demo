package me.nattapon.jpa.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String name;

    private UUID studentBook;

}
