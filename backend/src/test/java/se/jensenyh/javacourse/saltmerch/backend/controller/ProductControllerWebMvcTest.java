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
import se.jensenyh.javacourse.saltmerch.backend.service.ProductService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerWebMvcTest extends Tests {
    @Autowired
    MockMvc mockMvc;
    String baseUrl = "http://localhost";
    @MockBean
    ProductService service;

    @BeforeEach
    void setUp() {
        baseUrl = baseUrl.concat(":").concat(8020 + "").concat("/api").concat("/v1");
    }


    @Test
    public void getProducts() throws Exception {

        when(service.getProducts()).thenReturn(products);

        mockMvc.perform(
                MockMvcRequestBuilders.get(baseUrl + "/products").
                        contentType(MediaType.APPLICATION_JSON).
                        content(OM.writeValueAsString(products))).
                andExpect(status().isOk());

        verify(service).getProducts();

    }

    @Test
    public void createProduct() throws Exception {

        when(service.addProduct(prod3, prod3_Category)).thenReturn(prod3);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(baseUrl + "/products/{category}", prod3_Category).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(prod3))).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.category").value(prod3_Category)).
                andExpect(jsonPath("$.id").value(prod3_Id)).
                andExpect(jsonPath("$.title").value(prod3_Title)).
                andExpect(jsonPath("$.description").value(prod3_Description));

        verify(service).addProduct(prod3, prod3_Category);

    }

    @Test
    public void createColorVariant() throws Exception {

        when(service.addColorVariant(prod2_Id, colV1)).thenReturn(colV1);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(baseUrl + "/products/{id}/variants", prod2_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(colV1))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.colorName").value(colV1_Color));

        verify(service).addColorVariant(prod2_Id, colV1);
    }

    @Test
    public void getProductCategoryByName() throws Exception {

        when(service.getProductByName(prod1_Category)).thenReturn(prod1_Hats);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(baseUrl + "/products/{var}", prod1_Category).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(prod1_Hats))).
                andExpect(status().isOk());

        verify(service).getProductByName(prod1_Category);

    }

    @Test
    public void getProductCategoryById() throws Exception {

        when(service.getProductById(prod1_Id)).thenReturn(prod1_Hats);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(baseUrl + "/products/{var}", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(prod1_Hats))).
                andExpect(status().isOk());

        verify(service).getProductById(prod1_Id);

    }

    @Test
    public void updating_Product_That_Is_Not_Fetched_From_The_Db() throws Exception {

        when(service.updateProduct(prod2_Id, prod2)).thenReturn(-9);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(baseUrl + "/products/{id}", prod2_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(prod2))).
                andExpect(status().isBadRequest()).
                andExpect(content().string("Product wasn't fetched from the db"));

        verify(service).updateProduct(prod2_Id, prod2);

    }

    @Test
    public void updating_Product_With_Empty_Title_Or_Description() throws Exception {

        when(service.updateProduct(prod3_Id, prod3)).thenReturn(0);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(baseUrl + "/products/{id}", prod3_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(prod3))).
                andExpect(status().isBadRequest()).
                andExpect(content().string("Cannot set product with empty title/description"));

        verify(service).updateProduct(prod3_Id, prod3);

    }

    @Test
    public void updateProductById() throws Exception {

        when(service.updateProduct(prod1_Id, prod1)).thenReturn(1);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(baseUrl + "/products/{id}", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(prod1))).
                andExpect(status().isOk());

        verify(service).updateProduct(prod1_Id, prod1);

    }

    @Test
    public void restocking_Size_Variant_That_Sizes_Or_Colors_Do_Not_Match_Or_Params_Are_null_Or_Empty
            () throws Exception {

        int quantity = 0;
        when(service.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, quantity)).thenReturn(-2);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(baseUrl + "/products/{id}/variants/stock", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                param("size", sizC1_Size).
                                param("color", colV1_Color).
                                param("quantity", String.valueOf(quantity)).
                                content(OM.writeValueAsString(prod1_ColorVariants))).
                andExpect(status().isBadRequest()).
                andExpect(content().string("The sizes/colors don't match or Params can't be null or empty!"));

        verify(service).restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, quantity);

    }

    @Test
    public void restocking_Size_Variant_That_Is_Not_Fetched_From_The_Db() throws Exception {

        int quantity = 3;
        when(service.restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, quantity)).thenReturn(-1);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(baseUrl + "/products/{id}/variants/stock", prod1_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                param("size", sizC1_Size).
                                param("color", colV1_Color).
                                param("quantity", String.valueOf(quantity)).
                                content(OM.writeValueAsString(prod1_ColorVariants))).
                andExpect(status().isBadRequest()).
                andExpect(content().string("Product wasn't fetched from the db!"));

        verify(service).restockSizeVariant(prod1_Id, sizC1_Size, colV1_Color, quantity);

    }

    @Test
    public void restock_Size_Variant() throws Exception {

        int quantity = 5;
        when(service.restockSizeVariant(prod3_Id, sizC1_Size, colV1_Color, quantity)).thenReturn(1);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(baseUrl + "/products/{id}/variants/stock", prod3_Id).
                                contentType(MediaType.APPLICATION_JSON).
                                param("size", sizC1_Size).
                                param("color", colV1_Color).
                                param("quantity", String.valueOf(quantity)).
                                content(OM.writeValueAsString(prod3_ColorVariants))).
                andExpect(status().isOk());

        verify(service).restockSizeVariant(prod3_Id, sizC1_Size, colV1_Color, quantity);

    }

    @Test
    public void deleteColorVariant() throws Exception {

        when(service.deleteColorVariant(prod2_Id, colV2_Color)).thenReturn(1);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(baseUrl +
                                        "/products/{productId}/variants/{colorV}",
                                        prod2_Id, colV2_Color).
                                contentType(MediaType.APPLICATION_JSON).
                                content(OM.writeValueAsString(colV2))).
                andExpect(status().isOk());

        verify(service).deleteColorVariant(prod2_Id, colV2_Color);

    }

}