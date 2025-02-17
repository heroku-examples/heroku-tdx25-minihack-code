package com.heroku.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Finance Agreement Calculation", description = "Calculates finance agreements for car purchases based on valuation, credit status, and business margins.")
@RestController
@RequestMapping("/api/")
public class FinanceAgreementService {

    private static final Logger logger = LoggerFactory.getLogger(FinanceAgreementService.class);

    @Operation(summary = "Calculate a Finance Agreement", description = "Processes a finance agreement based on car valuation, customer credit profile, business margin constraints, and competitor pricing.")
    @PostMapping("/calculateFinanceAgreement")
    public FinanceCalculationResponse calculateFinanceAgreement(
            @RequestBody FinanceCalculationRequest request, HttpServletRequest httpServletRequest) {
        logger.info("Processing finance calculation for Customer ID: {}", request.customerId);
        // Mocked Response Data
        FinanceCalculationResponse response = new FinanceCalculationResponse();
        response.customerId = request.customerId;        
        response.carDetails = new CarDetails();
        response.carDetails.carId = request.carId;
        response.carDetails.make = "Tesla";
        response.carDetails.model = "Model 3";
        response.carDetails.year = 2022;
        response.carDetails.mileage = 15000;
        response.carDetails.marketValue = 42000;
        response.carDetails.tradeInValue = 35000;
        response.recommendedFinanceOffer = new FinanceOffer();
        response.recommendedFinanceOffer.finalCarPrice = 41800;
        response.recommendedFinanceOffer.adjustedInterestRate = 3.4;
        response.recommendedFinanceOffer.monthlyPayment = 690.50;
        response.recommendedFinanceOffer.loanTermMonths = 60;
        response.recommendedFinanceOffer.totalFinancingCost = 41430.00;
        response.profitAnalysis = new ProfitAnalysis();
        response.profitAnalysis.profitMargin = 2150;
        response.profitAnalysis.meetsMinProfitRequirement = true;
        response.counterOffer = new CounterOffer();
        response.counterOffer.adjustedCarPrice = 41400;
        response.counterOffer.newInterestRate = 3.3;
        response.counterOffer.monthlyPayment = 680.75;
        response.counterOffer.rationale = "Reduced price slightly while maintaining minimum profit margin of $2000 to stay competitive.";
        response.decisionRationale = new DecisionRationale();
        response.decisionRationale.pricingStrategy = "Slight undercut on Carvana, matched AutoTrader with better loan rate";
        response.decisionRationale.customerAffordabilityScore = 85;
        response.decisionRationale.approvalStatus = "Approved";
        response.retrievedCustomerCreditProfile = new CustomerCreditProfile();
        response.retrievedCustomerCreditProfile.creditProfileId = "a1A5g00000PjQhPEAV";
        response.retrievedCustomerCreditProfile.creditScore = 720;
        response.retrievedCustomerCreditProfile.loanApprovalStatus = "pre-approved";
        response.retrievedCustomerCreditProfile.downPayment = 5000;
        response.retrievedCustomerCreditProfile.loanTermMonths = 60;
        response.retrievedCustomerCreditProfile.interestRate = 3.4;
        return response;
    }

    @Schema(description = "Request to compute a finance agreement for a car purchase, including the Salesforce record ID of both the customer applying for financing and the car being financed.")
    public static class FinanceCalculationRequest {
        @Schema(example = "0035g00000XyZbHAZ", description = "The Salesforce record ID of the customer applying for financing.")
        public String customerId;
        @Schema(example = "a0B5g00000LkVnWEAV", description = "The Salesforce record ID of the car being financed.")
        public String carId;
    }

    @Schema(description = "Response containing the calculated finance agreement, car details, profitability analysis, and counter-offer.")
    public static class FinanceCalculationResponse {
        public String customerId;
        public CarDetails carDetails;
        public FinanceOffer recommendedFinanceOffer;
        public ProfitAnalysis profitAnalysis;
        public CounterOffer counterOffer;
        public DecisionRationale decisionRationale;
        public CustomerCreditProfile retrievedCustomerCreditProfile;
    }

    @Schema(description = "Details of the car being financed.")
    public static class CarDetails {
        public String carId;
        public String make;
        public String model;
        public int year;
        public int mileage;
        public double marketValue;
        public double tradeInValue;
    }

    @Schema(description = "Recommended finance offer based on business rules and customer affordability.")
    public static class FinanceOffer {
        public double finalCarPrice;
        public double adjustedInterestRate;
        public double monthlyPayment;
        public int loanTermMonths;
        public double totalFinancingCost;
    }

    @Schema(description = "Analysis of profit margins based on business constraints.")
    public static class ProfitAnalysis {
        public double profitMargin;
        public boolean meetsMinProfitRequirement;
    }

    @Schema(description = "Counter-offer suggested if the recommended finance offer does not meet customer expectations.")
    public static class CounterOffer {
        public double adjustedCarPrice;
        public double newInterestRate;
        public double monthlyPayment;
        public String rationale;
    }

    @Schema(description = "Rationale for the finance offer decision.")
    public static class DecisionRationale {
        public String pricingStrategy;
        public int customerAffordabilityScore;
        public String approvalStatus;
    }

    @Schema(description = "Credit profile retrieved for the customer.")
    public static class CustomerCreditProfile {
        public String creditProfileId;
        public int creditScore;
        public String loanApprovalStatus;
        public double downPayment;
        public int loanTermMonths;
        public double interestRate;
    }
}
