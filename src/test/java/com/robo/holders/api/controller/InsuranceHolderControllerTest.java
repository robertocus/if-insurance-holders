package com.robo.holders.api.controller;

import com.robo.holders.domain.InsuranceHolderFinder;
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

@WebMvcTest(controllers = {InsuranceHolderController.class})
class InsuranceHolderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsuranceHolderFinder insuranceHolderFinder;

    @Test
    public void whenGetNoneExistingInsuranceHolder() throws Exception {
        when(this.insuranceHolderFinder.find(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/holders/+6711225-6974"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(this.insuranceHolderFinder, times(1)).find(any());
    }
}