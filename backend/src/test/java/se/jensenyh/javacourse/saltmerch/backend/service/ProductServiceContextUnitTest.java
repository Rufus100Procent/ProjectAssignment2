package se.jensenyh.javacourse.saltmerch.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceContextUnitTest extends Tests {

    @InjectMocks
    ProductService service;
    @Mock
    ProductRepository repository;


    @Test
    public void getProducts(){

        when(repository.selectAll()).thenReturn(products);
        assertEquals(products, service.getProducts());
        verify(repository).selectAll();
    }

    @Test
    public void getProductByName(){

        when(repository.selectAllOfCategory(prod1_Category)).thenReturn(prod1_Hats);
        assertEquals(prod1_Hats, service.getProductByName(prod1_Category));
        verify(repository).selectAllOfCategory(prod1_Category);
    }

    @Test
    public void getProductById(){

        when(repository.getEntireProduct(prod2_Id)).thenReturn(prod2);
        assertEquals(prod2, service.getProductById(prod2_Id));
        verify(repository).getEntireProduct(prod2_Id);
    }

    @Test
    public void createProduct(){

        when(repository.insertProductAndProps(prod3, prod3_Category)).thenReturn(prod3);
        assertEquals(prod3, service.addProduct(prod3, prod3_Category));
        verify(repository).insertProductAndProps(prod3, prod3_Category);
    }

    @Test
    public void updateProductById(){

        when(repository.updateProductMeta(prod2_Id, prod2)).thenReturn(1);
        assertEquals(1, service.updateProduct(prod2_Id, prod2));
        verify(repository).updateProductMeta(prod2_Id, prod2);
    }

    @Test
    public void createColorVariantById(){

        when(repository.addVariant(prod3_Id, colV2)).thenReturn(colV2);
        assertEquals(colV2, service.addColorVariant(prod3_Id, colV2));
        verify(repository).addVariant(prod3_Id, colV2);
    }

    @Test
    public void deleteColorVariantById(){

        when(repository.deleteVariant(prod2_Id, colV1_Color)).thenReturn(1);
        assertEquals(1, service.deleteColorVariant(prod2_Id, colV1_Color));
        verify(repository).deleteVariant(prod2_Id, colV1_Color);
    }
}