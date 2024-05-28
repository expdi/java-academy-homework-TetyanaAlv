package com.example.service;

import com.example.dao.AdopterDAO;
import com.example.domain.Adopter;
import com.example.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdopterService {

   @Autowired
   private AdopterDAO adopterDAO;

   public AdopterService() {
   }

   public Adopter createAdopter(Adopter adopter) {

      Adopter insertedAdopter = adopterDAO.insert(adopter);

      return insertedAdopter;
   }

   public boolean deleteAdopter(int id) {
      return adopterDAO.delete(id);
   }

   public boolean updateAdopter(Adopter adopter) {
      return adopterDAO.update(adopter);
   }

   public Adopter getAdopter(int id) {
      return adopterDAO.findById(id);
   }

   public List<Adopter> getAdopters() {
      return adopterDAO.findAll();
   }

   public AdopterDAO getAdopterDAO() {
      return adopterDAO;
   }

   public Adopter addAdopter(Adopter adopter) {
      return adopterDAO.insert(adopter);
   }

   public void setAdopterDAO(AdopterDAO adopterDAO) {
      this.adopterDAO = adopterDAO;
   }

   public List<Adopter> getAdoptersByPetType(Pet.PetType type) {
      List<Adopter> result = adopterDAO
              .findAll().stream()
              .filter(a -> a.getPet().getType() == type)
              .toList();
      return result;
   }
}