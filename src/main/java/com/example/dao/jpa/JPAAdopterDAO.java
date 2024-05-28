package com.example.dao.jpa;

import com.example.dao.AdopterDAO;
import com.example.domain.Adopter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//
@Repository
@Profile("prod")
public class JPAAdopterDAO implements AdopterDAO {

   private Map<Integer, Adopter> adopters = new HashMap<>();
   private AtomicInteger nextId = new AtomicInteger(1);
   @Override
   public Adopter insert(Adopter adopter) {
      adopter.setId(nextId.getAndIncrement());
      adopter.setName("Jpa:" + adopter.getName());
      adopters.put(adopter.getId(), adopter);
      return adopter;
   }
   @Override
   public boolean update(Adopter adopter) {
      return adopters.replace(adopter.getId(), adopter) != null;
   }
   @Override
   public boolean delete(int id) {
      return adopters.remove(id) != null;
   }
   @Override

   public Adopter findById(int id) {
      return adopters.get(id);
   }
   @Override

   public List<Adopter> findAll() {
      return new ArrayList<>(adopters.values());
   }
}
