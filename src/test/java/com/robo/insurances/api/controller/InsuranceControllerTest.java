package com.robo.insurances.api.controller;

import com.robo.insurances.domain.insurance.InsuranceFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = {InsurancesController.class})
class InsurancesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsuranceFinder insuranceFinder;

    @Test
    public void whenGetNoneExistingInsurance() throws Exception {
        when(this.insuranceFinder.find(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/holders/+6711225-6974"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(this.insuranceFinder, times(1)).find(any());
    }
}