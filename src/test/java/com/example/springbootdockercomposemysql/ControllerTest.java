package com.example.springbootdockercomposemysql;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

//  @Autowired
//  private MockMvc mockMvc;
//
//  @Test
//  void givenADisabledIndicator_whenSendingRequest_thenReturns404() throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health/random"))
//        .andExpect(status().isNotFound());
//  }

}
