package com.example.dao;

import com.example.domain.Adopter;

import java.util.List;
public interface AdopterDAO {
    Adopter insert(Adopter adopter);

    boolean update(Adopter adopter);

    boolean delete(int id);

    Adopter findById(int id);

    List<Adopter> findAll();
}