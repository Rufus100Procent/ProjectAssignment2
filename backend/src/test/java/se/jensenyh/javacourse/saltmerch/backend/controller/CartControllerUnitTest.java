package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.service.CartService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartControllerUnitTest extends Tests {
    @Mock
    CartService service;
    @InjectMocks
    CartController controller;

    @Test
    public void getItems(){

        ResponseEntity<List<CartItem>> responseEntity = new ResponseEntity<>(cartItems, HttpStatus.OK);

        when(service.getItems(prod1_Id)).thenReturn(cartItems);
        assertEquals(responseEntity, controller.getItems(prod1_Id));
        verify(service).getItems(prod1_Id);

    }

    @Test
    public void addItem(){

        String action = "add";
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(1, HttpStatus.OK);

        when(service.addItem(prod1_Id, action, cartItem1)).thenReturn(1);
        assertEquals(responseEntity, controller.addOrRemoveItem(prod1_Id, action, cartItem1));
        verify(service).addItem(prod1_Id, action, cartItem1);

    }

    @Test
    public void removeItem(){

        String action = "remove";
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(0, HttpStatus.OK);

        when(service.removeItem(prod1_Id, action, cartItem1)).thenReturn(0);
        assertEquals(responseEntity, controller.addOrRemoveItem(prod1_Id, action, cartItem1));
        verify(service).removeItem(prod1_Id, action, cartItem1);

    }

}