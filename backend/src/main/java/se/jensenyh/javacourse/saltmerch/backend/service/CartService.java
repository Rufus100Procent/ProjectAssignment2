package se.jensenyh.javacourse.saltmerch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.repository.CartRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public CartService(CartRepository repository) {
        this.cartRepository = repository;
    }


    public List<CartItem> getItems(int id) {

        return cartRepository.selectAllItems();
    }

    public int addItem(int id, String action, CartItem item) {

        return cartRepository.insertOrIncrementItem(item);
    }

    public int removeItem(int id, String action, CartItem item) {

        return cartRepository.deleteOrDecrementItem(item);
    }

    public void clearCartAndRestock(int id) {

        cartRepository.deleteAllItems(true);
    }

    public void clearCart(boolean buyout) {

        cartRepository.deleteAllItems(false);
    }
}