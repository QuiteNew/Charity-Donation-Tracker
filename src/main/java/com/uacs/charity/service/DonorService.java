package com.uacs.charity.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.uacs.charity.model.Donor;
import com.uacs.charity.repository.DonorRepository;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor updateDonor(Long id, Donor donorDetails) {
        Donor donor = donorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Donor not found with ID: " + id));

        donor.setName(donorDetails.getName());
        donor.setEmail(donorDetails.getEmail());

        return donorRepository.save(donor);
    }

    public void softDeleteDonor(Long id) {
        Donor donor = donorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Donor not found with ID: " + id));
            
        donor.setDeletedAt(LocalDateTime.now());
        donorRepository.save(donor);
    }
}