package com.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.context.annotation.Profile;

@Entity //Comunicagte to JPA into the database
@Table(name="pet")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor //Not null
@NoArgsConstructor
@Getter
@Setter
@Profile("prod")
public class JPAPet {

    public enum PetType {
        CAT,
        DOG,
        TURTLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_id_seq")
    int petId;

    @NotNull
    String name;

    @NotNull
    Pet.PetType type;

    String breed;
}
