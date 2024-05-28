package com.example.service;

import com.example.domain.Adopter;
import com.example.domain.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@ActiveProfiles({"dev"})
public class AdopterServiceTest {

   @Autowired
   private AdopterService adopterService;

   @BeforeEach
   public void init() {
   }

   @Test
   public void testGetAll() {

      List<Adopter> adopters = adopterService.getAdopters();
      assertEquals(0, adopters.size());

      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());
      adopterService.addAdopter(adopter);


      adopters = adopterService.getAdopters();
      assertEquals(1, adopters.size());

      adopters.forEach(System.out::println);
   }

   @Test
   public void testGetOneWithGoodId() {

      List<Adopter> adopters = adopterService.getAdopters();
      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());
      adopterService.addAdopter(adopter);

      Adopter a = adopterService.getAdopter(1);
      assertEquals(1, a.getId());
   }

   @Test
   public void testGetOneWithNonExistentId() {
      Adopter adopter = adopterService.getAdopter(1000);
      assertNull(adopter);
   }

   @Test
   public void testDeleteWithExistingAdopter() {

      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());
      Adopter newAdopter = adopterService.addAdopter(adopter);

      boolean result = adopterService.deleteAdopter(newAdopter.getId());
      assertTrue(result);

      newAdopter = adopterService.getAdopter(1);
      assertNull(newAdopter);
   }

   @Test
   public void testDeleteNonExistingAdopter() {

      boolean result = adopterService.deleteAdopter(1000);
      assertFalse(result);
   }

   @Test
   public void testUpdateWithExistingAdopter() {

      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());
      Adopter newAdopter = adopterService.addAdopter(adopter);

      assertTrue(newAdopter.getName().contains("Tetyana"));

      newAdopter.setName("Martha");

      boolean result = adopterService.updateAdopter(newAdopter);
      assertTrue(result);

      newAdopter = adopterService.getAdopter(1);
      assertEquals("Martha", newAdopter.getName());
   }

   @Test
   public void testUpdateNonExistingAdopter() {

      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());
      Adopter newAdopter = adopterService.addAdopter(adopter);

      newAdopter.setId(1000);

      boolean result = adopterService.updateAdopter(newAdopter);
      assertFalse(result);
   }

   @Test
   public void testGetByPetType() {
      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());
      Adopter adopter2 = new Adopter("Cesar", 4162943783L, LocalDate.of(2000, 2, 3),
              Pet.builder(Pet.PetType.CAT).name("Simon").build());

      adopterService.addAdopter(adopter);
      adopterService.addAdopter(adopter2);
      List<Adopter> result = adopterService.getAdoptersByPetType(Pet.PetType.CAT);

      assertEquals(1, result.size());
      assertTrue(result.get(0).getName().contains("Cesar"));

      result = adopterService.getAdoptersByPetType(Pet.PetType.TURTLE);

      assertEquals(0, result.size());
   }

   @Test
   public void testAdopterServiceInsert() {
      Adopter adopter = new Adopter("Tetyana", 6478252292L, LocalDate.of(2004, 1, 10),
              Pet.builder(Pet.PetType.DOG).name("Choncho").build());

      Adopter insertedAdopter = adopterService.createAdopter(adopter);

      System.out.println("adopter: " + insertedAdopter.toString());
      assertNotNull(insertedAdopter);
      assertEquals(1, adopter.getId());
   }

   @Test
   public void testDeleteExistingAdopter() {
      Adopter adopter = new Adopter("Tetyana", 4161234567L,
              LocalDate.of(2024,1,22), Pet.builder(Pet.PetType.CAT).name("Simon").build());

      Adopter insertedAdopter = adopterService.createAdopter(adopter);

      System.out.println("adopter: " + insertedAdopter);
      assertNotNull(insertedAdopter);

      boolean deleted = adopterService.deleteAdopter(insertedAdopter.getId());
      assertTrue(deleted);
   }


}
