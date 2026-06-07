package com.uacs.charity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uacs.charity.model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
  
    List<Donor> findByDeletedAtIsNull();
    Optional<Donor> findByIdAndDeletedAtIsNull(Long id);
    
   
    List<Donor> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String name);
}