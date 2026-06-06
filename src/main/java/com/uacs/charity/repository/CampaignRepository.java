package com.uacs.charity.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uacs.charity.model.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
    

    List<Campaign> findByDeletedAtIsNull(); 

   
    List<Campaign> findByTitleContainingIgnoreCaseAndDeletedAtIsNull(String title);

   
    Page<Campaign> findByDeletedAtIsNull(Pageable pageable);

   
    List<Campaign> findByIsClosedFalseAndDeletedAtIsNull();
}