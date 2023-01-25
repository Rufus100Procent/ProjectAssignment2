package se.jensenyh.javacourse.saltmerch.backend.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerUnitTest extends Tests {
    @Mock
    ProductService service;
    @InjectMocks
    ProductController controller;

    @Test
    public void getProducts(){

        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(products, HttpStatus.OK);

        when(service.getProducts()).thenReturn(products);
        assertEquals(responseEntity, controller.getProducts());
        verify(service).getProducts();

    }

    @Test
    public void getProductCategoryByName(){

        ResponseEntity<Object> responseEntity = new ResponseEntity<>(prod1_Hats, HttpStatus.OK);

        when(service.getProductByName(prod1_Category)).thenReturn(prod1_Hats);
        assertEquals(responseEntity, controller.getProductByCategory(prod1_Category));
        verify(service).getProductByName(prod1_Category);

    }

    @Test
    public void getProductCategoryById(){

        ResponseEntity<Object> responseEntity = new ResponseEntity<>(prod1_Hats, HttpStatus.OK);

        when(service.getProductById(prod1_Id)).thenReturn(prod1_Hats);
        assertEquals(responseEntity, controller.getProductByCategory(String.valueOf(prod1_Id)));
        verify(service).getProductById(prod1_Id);

    }

    @Test
    public void updating_Product_That_Is_Not_Fetched_From_The_Db(){

        ResponseEntity<String> responseEntity = ResponseEntity.badRequest().
                body("Product wasn't fetched from the db");

        when(service.updateProduct(prod2_Id, prod2)).thenReturn(-9);
        assertEquals(responseEntity, controller.updateProduct(prod2_Id, prod2));
        verify(service).updateProduct(prod2_Id, prod2);

    }

    @Test
    public void updating_Product_With_Empty_Title_Or_Description(){

        ResponseEntity<String> responseEntity = ResponseEntity.badRequest().
                body("Cannot set product with empty title/description");

        when(service.updateProduct(prod2_Id, prod2)).thenReturn(0);
        assertEquals(responseEntity, controller.updateProduct(prod2_Id, prod2));
        verify(service).updateProduct(prod2_Id, prod2);

    }

    @Test
    public void updateProductById(){

        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();

        when(service.updateProduct(prod2_Id, prod2)).thenReturn(1);
        assertEquals(responseEntity, controller.updateProduct(prod2_Id, prod2));
        verify(service).updateProduct(prod2_Id, prod2);

    }

    @Test
    public void restocking_Size_Variant_That_Sizes_Or_Colors_Do_Not_Match_Or_Params_Are_null_Or_Empty(){

        ResponseEntity<String> responseEntity = ResponseEntity.badRequest().
                body("The sizes/colors don't match or Params can't be null or empty!");

        when(service.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 2)).thenReturn(-2);
        assertEquals(responseEntity, controller.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 2));
        verify(service).restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 2);

    }

    @Test
    public void restocking_Size_Variant_That_Is_Not_Fetched_From_The_Db(){

        ResponseEntity<String> responseEntity = ResponseEntity.badRequest().
                body("Product wasn't fetched from the db!");

        when(service.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 3)).thenReturn(-1);
        assertEquals(responseEntity, controller.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 3));
        verify(service).restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 3);

    }

    @Test
    public void restock_Size_Variant(){

        ResponseEntity<String> responseEntity = ResponseEntity.ok().build();

        when(service.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 5)).thenReturn(1);
        assertEquals(responseEntity, controller.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 5));
        verify(service).restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, 5);

    }

    @Test
    public void deleting_Product_That_Do_Not_Exist(){

        ResponseEntity<String> responseEntity = ResponseEntity.status(404).body("No product with that id!");

        when(service.deleteProduct(prod1_Id)).thenReturn(-2);
        assertEquals(responseEntity, controller.deleteProduct(prod1_Id));
        verify(service).deleteProduct(prod1_Id);

    }

    @Test
    public void deleteProductById(){

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().build();

        when(service.deleteProduct(prod1_Id)).thenReturn(1);
        assertEquals(responseEntity, controller.deleteProduct(prod1_Id));
        verify(service).deleteProduct(prod1_Id);

    }

    @Test
    public void createProduct(){

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(prod3, HttpStatus.CREATED);

        when(service.addProduct(prod3, prod3_Category)).thenReturn(prod3);
        assertEquals(responseEntity, controller.createProduct(prod3, prod3_Category));
        verify(service).addProduct(prod3, prod3_Category);

    }
}