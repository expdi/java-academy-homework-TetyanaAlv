package com.example.domain;

import java.time.LocalDate;

public class Adopter {

    private int id;
    private String name;
    private long phoneNumber;
    private LocalDate dateOfAdoption;
    private Pet pet;

    public Adopter(String name, long phoneNumber, LocalDate dateOfAdoption,
                   Pet pet){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfAdoption = dateOfAdoption;
        this.pet = pet;
    }

    public Adopter() {
    }

    @Override
    public String toString() {
        return "Adopter ID: " + id + "\n\tName: " + name
                + "\n\tPhone number: " + String.valueOf(phoneNumber).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3")
                + "\n\tDate of Adoption: " + dateOfAdoption + "\n\tPet: " + pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return String.valueOf(phoneNumber).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }
    public LocalDate getDateOfAdoption() {
        return dateOfAdoption;
    }

    public Pet getPet() {
        return pet;
    }

}
