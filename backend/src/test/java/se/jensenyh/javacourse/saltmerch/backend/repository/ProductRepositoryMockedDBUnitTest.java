package se.jensenyh.javacourse.saltmerch.backend.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import se.jensenyh.javacourse.saltmerch.backend.Tests;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ProductRepositoryMockedDBUnitTest extends Tests {
    ProductRepository repository;


    @BeforeEach
    public void setUp() {

        JdbcTemplate template = Mockito.mock(JdbcTemplate.class);
        repository  = new ProductRepository(template);
        when(template.query(eq("SELECT * FROM products"), any(RowMapper.class))).thenReturn(products);
        Mockito.when(template.query(Mockito.anyString(), any(RowMapper.class))).thenReturn(prod1_Hats);

    }

    /*@Test
    public void selectAll(){

        assertEquals(products, repository.selectAll());
    }

    @Test
    public void selectAllOfCategory(){

        assertEquals(prod1_Hats, repository.selectAllOfCategory(prod1_Category));
    }*/

}