package se.jensenyh.javacourse.saltmerch.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.repository.CartRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceContextUnitTest extends Tests {
    @InjectMocks
    CartService service;
    @Mock
    CartRepository repository;

    @Test
    public void getItems(){

        when(repository.selectAllItems()).thenReturn(cartItems);
        assertEquals(cartItems, service.getItems(prod1_Id));
        verify(repository).selectAllItems();
    }

    @Test
    public void addItem(){

        String action = "add";
        when(repository.insertOrIncrementItem(cartItem1)).thenReturn(1);
        assertEquals(1, service.addItem(prod1_Id, action, cartItem1));
        verify(repository).insertOrIncrementItem(cartItem1);
    }

    @Test
    public void removeItem(){

        String action = "remove";
        when(repository.deleteOrDecrementItem(cartItem1)).thenReturn(0);
        assertEquals(0, service.removeItem(prod1_Id, action, cartItem1));
        verify(repository).deleteOrDecrementItem(cartItem1);
    }
}