package root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.entity.Product;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.exception.ProductNotFoundException;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductManagerService productManagerService;

    @Test
    void findProductBySku_Success() {
        String sku = "SKU123";
        Product product = new Product(sku, "Test Product", 100.0, 10);
        when(productRepository.findBySku(sku)).thenReturn(Optional.of(product));

        Product result = productManagerService.findProductBySku(sku);

        assertNotNull(result);
        assertEquals(sku, result.getSku());
        verify(productRepository).findBySku(sku);
    }

    @Test
    void findProductBySku_NotFound() {
        String sku = "NON_EXISTENT";
        when(productRepository.findBySku(sku)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productManagerService.findProductBySku(sku));
        verify(productRepository).findBySku(sku);
    }

    @Test
    void restockProduct_Success() {
        String sku = "SKU123";
        int initialQuantity = 10;
        int quantityToAdd = 5;
        Product product = new Product(sku, "Test Product", 100.0, initialQuantity);

        when(productRepository.findBySku(sku)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productManagerService.restockProduct(sku, quantityToAdd);

        assertEquals(initialQuantity + quantityToAdd, result.getQuantity());
        verify(productRepository).findBySku(sku);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void restockProduct_NotFound() {
        String sku = "NON_EXISTENT";
        when(productRepository.findBySku(sku)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productManagerService.restockProduct(sku, 5));

        verify(productRepository).findBySku(sku);
        verify(productRepository, never()).save(any(Product.class));
    }
}
