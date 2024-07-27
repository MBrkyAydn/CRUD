package com.example.personnelbackend.controller;

import com.example.personnelbackend.entity.Personnel;
import com.example.personnelbackend.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnel")
@CrossOrigin(origins = "http://localhost:3000") // CORS ayarını ekleyin
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable Long id) {
        Optional<Personnel> personnel = personnelService.getPersonnelById(id);
        return personnel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Personnel createPersonnel(@RequestBody Personnel personnel) {
        return personnelService.savePersonnel(personnel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable Long id, @RequestBody Personnel personnelDetails) {
        try {
            Personnel updatedPersonnel = personnelService.updatePersonnel(id, personnelDetails);
            return ResponseEntity.ok(updatedPersonnel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        try {
            personnelService.deletePersonnel(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
