package com.uacs.charity.repository;

import com.uacs.charity.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    // These automatically filter out soft-deleted records (where deletedAt is NOT null)
    List<Donor> findByDeletedAtIsNull();
    Optional<Donor> findByIdAndDeletedAtIsNull(Long id);
    
    // Custom feature endpoint helper: Search donors by name search
    List<Donor> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String name);
}