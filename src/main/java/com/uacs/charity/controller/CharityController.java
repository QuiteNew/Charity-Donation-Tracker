package com.uacs.charity.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uacs.charity.model.Campaign;
import com.uacs.charity.model.Donation;
import com.uacs.charity.service.CharityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CharityController {

    private final CharityService svc;

    public CharityController(CharityService svc) {
        this.svc = svc;
    }

    @PostMapping("/campaigns")
    public Campaign createCampaign(@RequestBody @Valid Campaign c) {
        return svc.createCampaign(c);
    }

    @GetMapping("/campaigns")
    public Page<Campaign> listCampaigns(Pageable pageable) {
        return svc.listActiveCampaigns(pageable);
    }

    @GetMapping("/campaigns/{id}")
    public Campaign getCampaign(@PathVariable Long id) {
        return svc.getCampaign(id);
    }

    @PutMapping("/campaigns/{id}")
    public Campaign updateCampaign(@PathVariable Long id, @RequestBody @Valid Campaign c) {
    return svc.updateCampaign(id, c);
    }

    @DeleteMapping("/campaigns/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        svc.deleteCampaign(id);
    }

    @PostMapping("/campaigns/{id}/donations")
    public Donation makeDonation(@PathVariable Long id, @RequestBody @Valid Donation d) {
        d.setCampaignId(id);
        return svc.recordDonation(d);
    }
}