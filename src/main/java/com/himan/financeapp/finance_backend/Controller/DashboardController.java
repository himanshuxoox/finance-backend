package com.himan.financeapp.finance_backend.Controller;

import com.himan.financeapp.finance_backend.DTOs.DashboardResponse;
import com.himan.financeapp.finance_backend.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000/")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardData(Authentication auth) {
        String email = auth.getName(); // Spring Security will give the email from JWT
        DashboardResponse response = dashboardService.getDashboardData(email);
        return ResponseEntity.ok(response);
    }
}
