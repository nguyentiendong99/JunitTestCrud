package com.example.tester;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {

    @Autowired
    private ProductRepository repository;

    @Test
    @Rollback(value = false)
    public void testCreateProduct(){
        Product product = new Product("Iphone 11" , 553);
        Product productsaved = repository.save(product);
        assertNotNull(productsaved);
    }
    @Test
    public void testFindProductByName(){
        String name = "Iphone 10";
        Product product = repository.findByName(name);
        assertThat(product.getName()).isEqualTo(name);
    }
    @Test
    @Rollback(value = false)
    public void testUpdateProducts(){
        String productName = "Kindle Reader";
        Product product = new Product(productName , 111);
        product.setId(4);
        repository.save(product);
        Product updatedProduct = repository.findByName(productName);
        assertThat(updatedProduct.getName()).isEqualTo(productName);
    }
    @Test
    public void testListProducts(){
        List<Product> products = (List<Product>) repository.findAll();
        assertThat(products).isNotNull();
    }
}
