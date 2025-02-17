package com.heroku.java.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Shipping Calculation", description = "Calculates the carbon footprint for a given flight based on external data.")
@RestController
@RequestMapping("/api/")
public class ShippingOptionsService {

    private static final Logger logger = LoggerFactory.getLogger(ShippingOptionsService.class);

    @Operation(summary = "Calculate Shipping Options", description = "Calculates the shipping options for a product given external data.")
    @PostMapping("/calculateShippingOptions")
    public CalculateShippingOptionsResponse calculateShippingOptions(
            @RequestBody CalculateShippingOptionsRequest request, HttpServletRequest httpServletRequest) {
        logger.info("Processing shipping options calculation for product: {}", request.productId);
        // Mocked Response Data
        CalculateShippingOptionsResponse response = new CalculateShippingOptionsResponse();
        return response;
    }

    @Schema(description = "Request to determine shipping options for a product, including the Salesforce record ID of the product being shipped.")
    public static class CalculateShippingOptionsRequest {
        @Schema(example = "0035g00000XyZbHAZ", description = "The Salesforce record ID of the product.")
        public String productId;
    }

    @Schema(description = "Response containing the calculated shipping options for the given product.")
    public static class CalculateShippingOptionsResponse {        
        public String result;
    }
}
