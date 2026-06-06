package com.uacs.charity.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;
    private Long donorId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Donation amounts must be strictly positive")
    private Double amountEur;
    
    private LocalDate donatedOn;
    private boolean anonymous;

    public Donation() {}

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }

    public Long getDonorId() { return donorId; }
    public void setDonorId(Long donorId) { this.donorId = donorId; }

    public Double getAmountEur() { return amountEur; }
    public void setAmountEur(Double amountEur) { this.amountEur = amountEur; }

    public LocalDate getDonatedOn() { return donatedOn; }
    public void setDonatedOn(LocalDate donatedOn) { this.donatedOn = donatedOn; }

    public boolean isAnonymous() { return anonymous; }
    public void setAnonymous(boolean anonymous) { this.anonymous = anonymous; }
}