package com.uacs.charity.service;

import com.uacs.charity.model.Campaign;
import com.uacs.charity.model.Donation;
import com.uacs.charity.repository.CampaignRepository;
import com.uacs.charity.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ReportService {

    @Autowired 
    private CampaignRepository campaignRepository;
    
    @Autowired 
    private DonationRepository donationRepository;

    public List<Map<String, Object>> getCampaignProgressReport() {
        List<Map<String, Object>> report = new ArrayList<>();
        
        // 1. Fetch campaigns safely
        List<Campaign> openCampaigns = campaignRepository.findByIsClosedFalseAndDeletedAtIsNull();
        
        if (openCampaigns == null || openCampaigns.isEmpty()) {
            return report;
        }

        for (Campaign campaign : openCampaigns) {
            BigDecimal targetGoal = (campaign.getTargetGoal() != null) 
                ? BigDecimal.valueOf(campaign.getTargetGoal()) 
                : BigDecimal.ZERO;

            // 2. UPDATED: Call the new, simplified repository method
            List<Donation> donations = donationRepository.findByCampaignId(campaign.getId());
            
            BigDecimal totalRaised = BigDecimal.ZERO;
            if (donations != null) {
                for (Donation d : donations) {
                    if (d.getAmountEur() != null) {
                        totalRaised = totalRaised.add(BigDecimal.valueOf(d.getAmountEur()));
                    }
                }
            }

            BigDecimal percentageReached = BigDecimal.ZERO;
            if (targetGoal.compareTo(BigDecimal.ZERO) > 0) {
                percentageReached = totalRaised.multiply(new BigDecimal("100"))
                        .divide(targetGoal, 2, RoundingMode.HALF_UP);
            }

            Map<String, Object> stats = new HashMap<>();
            stats.put("campaignId", (campaign.getId() != null) ? campaign.getId() : "N/A");
            stats.put("campaignTitle", (campaign.getTitle() != null) ? campaign.getTitle() : "Untitled");
            stats.put("targetGoal", targetGoal);
            stats.put("totalRaised", totalRaised);
            stats.put("progressPercentage", percentageReached.toString() + "%");

            report.add(stats);
        }
        return report;
    }
}