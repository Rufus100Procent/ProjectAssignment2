package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.service.CartService;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerWebMvcTest extends Tests {
    @Autowired
    MockMvc mockMvc;
    String baseUrl = "http://localhost";
    @MockBean
    CartService service;

    @BeforeEach
    void setUp() {
        baseUrl = baseUrl.concat(":").concat(8020 + "").concat("/api").concat("/v1");
    }

    @Test
    public void getItems() throws Exception {

        when(service.getItems(prod1_Id)).thenReturn(cartItems);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(baseUrl + "/carts/{id}", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(cartItems))).
                andExpect(status().isOk());

        verify(service).getItems(prod1_Id);

    }

    @Test
    public void addItem() throws Exception {

        String action = "add";
        when(service.addItem(prod1_Id, action, cartItem1)).thenReturn(1);

        mockMvc.perform(
                        MockMvcRequestBuilders.patch(baseUrl + "/carts/{id}", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                param("action",action).
                                content(OM.writeValueAsString(cartItem1))).
                andExpect(status().isOk());

        verify(service).addItem(prod1_Id, action, cartItem1);

    }

    @Test
    public void removeItem() throws Exception {

        String action = "remove";
        when(service.removeItem(prod1_Id, action, cartItem1)).thenReturn(0);

        mockMvc.perform(
                        MockMvcRequestBuilders.patch(baseUrl + "/carts/{id}", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                param("action",action).
                                content(OM.writeValueAsString(cartItem1))).
                andExpect(status().isOk());

        verify(service).removeItem(prod1_Id, action, cartItem1);

    }

}