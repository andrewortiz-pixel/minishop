package com.tecsup.minishop.controller;

import com.tecsup.minishop.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductRequestTest {

    @Test
    void toEntity_copiaNombrePrecioYStock() {
        // Arrange
        ProductRequest request = new ProductRequest();
        request.setName("Teclado");
        request.setPrice(50.0);
        request.setStock(10);

        // Act
        Product product = request.toEntity();

        // Assert
        assertEquals("Teclado", product.getName());
        assertEquals(50.0, product.getPrice());
        assertEquals(10, product.getStock());
        assertNull(product.getId());
    }
}