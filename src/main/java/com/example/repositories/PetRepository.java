package com.example.repositories;

import com.example.domain.JPAPet;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("Prod")
@Repository
public interface PetRepository extends CrudRepository<JPAPet, Long> {
}
