package com.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;


@Entity //Comunicagte to JPA into the database
@Table(name="adopter")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor //Not null
@NoArgsConstructor
@Getter
@Setter
@Profile("prod")
public class JPAAdopter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adopter_id_seq")
    int id;

    @NotNull
    String name;

    @NotNull
    long phoneNumber;

    @NotNull
    LocalDate dateOfAdoption;

    @NotNull
    Pet pet;
}
