package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.service.CartService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3010")
@RequestMapping("/api/v1")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/carts/{id}")
    public ResponseEntity<List<CartItem>> getItems(@PathVariable("id") int id){

        return new ResponseEntity<>(cartService.getItems(id),
                HttpStatus.OK);
    }

    @PatchMapping("/carts/{id}")
    public ResponseEntity<Integer> addOrRemoveItem(@PathVariable("id") int id,
                                           @RequestParam String action,
                                           @RequestBody CartItem item){

        if ("add".equals(action))
            return new ResponseEntity<>(cartService.addItem(id, action, item),
                    HttpStatus.OK);
        else if ("remove".equals(action))
            return new ResponseEntity<>(cartService.removeItem(id, action, item),
                    HttpStatus.OK);
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("carts/{id}")
    public void clearCartOrClearCartAndRestock(@PathVariable("id") int id,
                                    @RequestParam boolean buyout){

        if (buyout)
            cartService.clearCart(true);
        else {
            cartService.clearCartAndRestock(id);
        }
    }
}