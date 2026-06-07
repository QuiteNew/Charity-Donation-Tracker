package com.uacs.charity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacs.charity.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    
   
    List<Donation> findByCampaignId(Long campaignId);
}