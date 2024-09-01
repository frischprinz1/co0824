package com.toolrental.toolrentalproject.rentalactivity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toolrental.toolrentalproject.tool.Tool;
import com.toolrental.toolrentalproject.tool.ToolService;

@RestController
@Validated
public class RentalActivityController {
    @Autowired
    private RentalActivityService rentalActivityService;
    @Autowired
    private ToolService toolService;

    // home page
    // @GetMapping(path = "/v1")
    @GetMapping(path = "/")
    public String home() {
        return "Welcome to Tool Rental";
    }

    // rent tools page
    @GetMapping(path = "/customer/tools")
    public Iterable<Tool> displayRentalPage() {
        return toolService.getAll();
    }

    // rent tools form handler
    @PostMapping(path = "/customer/tools")
    public RentalAgreementFormat submit(@RequestParam(name = "toolCode") String toolCode,
            @RequestParam(name = "rentalDays") Long rentalDays,
            @RequestParam(name = "discountPercent") Long discountPercent,
            @RequestParam(name = "checkoutDate") LocalDate checkoutDate) {

        return rentalActivityService
                .checkout(new ToolRentalRequest(toolCode, checkoutDate, rentalDays, discountPercent));
    }
}
