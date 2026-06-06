package com.uacs.charity.repository;

import com.uacs.charity.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    
    // Simplified to only look for the Campaign ID
    List<Donation> findByCampaignId(Long campaignId);
}