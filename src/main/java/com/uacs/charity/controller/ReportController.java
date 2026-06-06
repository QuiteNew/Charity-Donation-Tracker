package com.uacs.charity.controller;

import com.uacs.charity.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired private ReportService reportService;

    // GET /api/reports/campaign-progress
    @GetMapping("/campaign-progress")
    public List<Map<String, Object>> getCampaignProgressReport() {
        return reportService.getCampaignProgressReport();
    }
}