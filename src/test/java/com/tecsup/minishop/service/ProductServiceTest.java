package com.tecsup.minishop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tecsup.minishop.model.Product;
import com.tecsup.minishop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void save_conPrecioValido_guardaElProducto() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1500.0);
        product.setStock(10);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.save(product);

        assertEquals("Laptop", result.getName());
        assertEquals(1500.0, result.getPrice(), 0.001);
        assertEquals(10, result.getStock());
        verify(productRepository).save(product);
    }

    @Test
    void save_conPrecioInvalido_lanzaExcepcion() {
        Product product = new Product();
        product.setName("Mouse");
        product.setPrice(-5.0);
        product.setStock(3);

        assertThrows(RuntimeException.class, () -> productService.save(product));
        verify(productRepository, never()).save(any(Product.class));
    }
}