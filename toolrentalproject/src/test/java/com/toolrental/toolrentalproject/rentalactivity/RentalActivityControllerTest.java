package com.toolrental.toolrentalproject.rentalactivity;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.toolrental.toolrentalproject.tool.ToolService;

@WebMvcTest(RentalActivityController.class)
class RentalActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalActivityService rentalActivityService;

    @MockBean
    private ToolService toolService;

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Tool Rental"));
    }

    @Test
    void testDisplayRentalPage() throws Exception {
        mockMvc.perform(get("/customer/tools"))
                .andExpect(status().isOk());

        verify(toolService, times(1)).getAll();
    }

    @Test
    void testSubmit() throws Exception {
        String toolCode = "CHNQ";
        Long rentalDays = 5L;
        Long discountPercent = 10L;
        LocalDate checkoutDate = LocalDate.of(2023, 5, 1);

        mockMvc.perform(post("/customer/tools")
                .param("toolCode", toolCode)
                .param("rentalDays", rentalDays.toString())
                .param("discountPercent", discountPercent.toString())
                .param("checkoutDate", checkoutDate.toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }
}