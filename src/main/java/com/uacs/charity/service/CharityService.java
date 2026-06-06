package com.uacs.charity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.uacs.charity.exception.BusinessInvariantException;
import com.uacs.charity.model.AuditLog;
import com.uacs.charity.model.Campaign;
import com.uacs.charity.model.Donation;
import com.uacs.charity.repository.AuditLogRepository;
import com.uacs.charity.repository.CampaignRepository;
import com.uacs.charity.repository.DonationRepository;

@Service
public class CharityService {

    private final AuditLogRepository auditRepo;
    private final CampaignRepository campaignRepo;
    private final DonationRepository donationRepo;

    public CharityService(@NonNull final CampaignRepository campaignRepo, @NonNull final DonationRepository donationRepo, AuditLogRepository auditRepo) {
        this.campaignRepo = campaignRepo;
        this.donationRepo = donationRepo;
        this.auditRepo = auditRepo;
    }

    public Campaign createCampaign(@NonNull final Campaign c) {
        return campaignRepo.save(c);
    }

    public Campaign updateCampaign(@NonNull final Long id, @NonNull final Campaign details) {
      
        Campaign c = getCampaign(id);
        c.setTitle(details.getTitle());
        c.setTargetGoal(details.getTargetGoal());
        c.setClosed(details.isClosed());
        
       return campaignRepo.save(c);
    }

    public Page<Campaign> listActiveCampaigns(@NonNull final Pageable pageable) {
        return campaignRepo.findByDeletedAtIsNull(pageable);
    }

    public List<Campaign> searchCampaigns(String title) {
        return campaignRepo.findByTitleContainingIgnoreCaseAndDeletedAtIsNull(title);
    }

    public Campaign getCampaign(@NonNull final Long id) {
    Campaign c = campaignRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Campaign not found with id: " + id
        ));

    if (c.getDeletedAt() != null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign is deleted");
    }
    
    return c;
}

    public void deleteCampaign(@NonNull final Long id) {
        Campaign c = getCampaign(id);
        
        c.setDeletedAt(java.time.LocalDateTime.now());
        
        campaignRepo.save(c);
        
        auditRepo.save(new AuditLog("Campaign soft-deleted with ID: " + id));
    }

    public Donation recordDonation(@NonNull final Donation d) {
    
    final Long cId = d.getCampaignId();
    if (cId == null) {
        // You can leave this, or let a @NotNull in the DTO handle it
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campaign ID must be provided.");
    }

    final Campaign c = getCampaign(cId);

    // THIS IS YOUR 409 CONFLICT TRIGGER
    if (c.isClosed()) {
        throw new BusinessInvariantException("Cannot donate to a closed campaign.");
    }

    // REMOVED the manual amount check here! Validation handles the 400.

    Donation savedDonation = donationRepo.save(d);
    String message = "Donation of " + savedDonation.getAmountEur() + " EUR recorded for Campaign ID: " + cId;
    auditRepo.save(new AuditLog(message));

    return savedDonation;
}
}