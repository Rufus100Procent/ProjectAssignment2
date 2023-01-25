package se.jensenyh.javacourse.saltmerch.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.repository.CartRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartServiceNoContextUnitTest extends Tests {
    CartService service;
    CartRepository repository;

    @BeforeEach
    public void setUp(){

        repository = Mockito.mock(CartRepository.class);
        service = new CartService(repository);
    }

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