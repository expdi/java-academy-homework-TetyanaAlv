package com.example.controller;

import com.example.domain.Adopter;
import com.example.service.AdopterService;
import com.example.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/petservice")
public class AdopterController {

    private AdopterService adopterService;
    private UriCreator uriCreator;

    public AdopterController(AdopterService adopterService,
                             UriCreator uriCreator) {
        this.adopterService = adopterService;
        this.uriCreator = uriCreator;
    }
    @GetMapping
    public  ResponseEntity<?> getAll(){
        List<Adopter> adopters = adopterService.getAdopters();
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<?> getAdopter(@PathVariable("id") int id){
        Adopter adopters = adopterService.getAdopter(id);
        if(adopters == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No adopters with id: " + id);
        }
        return ResponseEntity.ok(adopters);
    }

    @PostMapping
    public ResponseEntity<?> addAdopter(@RequestBody Adopter adopter) {
        Adopter newAdopter = adopterService.addAdopter(adopter);

        URI uri = uriCreator.getURI(newAdopter.getId());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<?> deleteAdopter(@PathVariable("id") int id) {
        boolean result = adopterService.deleteAdopter(id);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Adopter with id: " + id);
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAdopter(@RequestBody Adopter adopter) {
        boolean result = adopterService.updateAdopter(adopter);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No adopter with id: " + adopter.getId());
        }

        return ResponseEntity.noContent().build();
    }
}
