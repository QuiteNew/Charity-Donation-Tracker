package com.uacs.charity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // <--- Make sure to import this!
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uacs.charity.model.Donor;
import com.uacs.charity.repository.DonorRepository;
import com.uacs.charity.service.DonorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorRepository donorRepository;
    private final DonorService donorService; // 1. Declare the field

    
    public DonorController(DonorRepository donorRepository, DonorService donorService) {
        this.donorRepository = donorRepository;
        this.donorService = donorService;
    }

    @GetMapping
    public List<Donor> getAllActiveDonors() {
        return donorRepository.findByDeletedAtIsNull();
    }

    
    @GetMapping("/search")
    public List<Donor> searchDonors(@RequestParam String name) {
        return donorRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(name);
    }

    
    @PostMapping
    public Donor createDonor(@RequestBody @Valid Donor donor) {
        return donorRepository.save(donor);
    }

    
    @PutMapping("/{id}")
    public Donor updateDonor(@PathVariable Long id, @RequestBody @Valid Donor donor) {
        return donorService.updateDonor(id, donor);
    }

   
    @DeleteMapping("/{id}")
    public void deleteDonor(@PathVariable Long id) {
        donorService.softDeleteDonor(id);
    }
}