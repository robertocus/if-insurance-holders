package com.robo.insurances.api.controller;

import com.robo.insurances.domain.insurance.InsuranceFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = {InsurancesController.class})
class InsuranceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InsuranceFinder insuranceFinder;

    @Test
    public void whenGetNoneExistingInsurance() throws Exception {
        when(this.insuranceFinder.findByHolder(any())).thenReturn(List.of());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/insurances").param("byHolderId", "3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(this.insuranceFinder, times(1)).findByHolder(any());
    }
}