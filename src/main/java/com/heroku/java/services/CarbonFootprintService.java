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

@Tag(name = "Carbon Footprint Calculation", description = "Calculates the carbon footprint for a given flight based on external data.")
@RestController
@RequestMapping("/api/")
public class CarbonFootprintService {

    private static final Logger logger = LoggerFactory.getLogger(CarbonFootprintService.class);

    @Operation(summary = "Calculate Carbon Footprint", description = "Calculates the carbon footprint for a given flight based on external data.")
    @PostMapping("/calculateCarbonFootprint")
    public CarbonFootprintResponse calculateCarbonFootprint(
            @RequestBody CarbonFootrintRequest request, HttpServletRequest httpServletRequest) {
        logger.info("Processing carbon calculation for Flight ID: {}", request.flightId);
        // Mocked Response Data
        CarbonFootprintResponse response = new CarbonFootprintResponse();
        return response;
    }

    @Schema(description = "Request to calculate the carbon footprint of a flight, including the Salesforce record ID of the flight being analyzed.")
    public static class CarbonFootrintRequest {
        @Schema(example = "0035g00000XyZbHAZ", description = "The Salesforce record ID of the flight.")
        public String flightId;
    }

    @Schema(description = "Response containing the calculated carbon footprint for the flight.")
    public static class CarbonFootprintResponse {        
        public String result;
    }
}
