package root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.service;

import org.springframework.stereotype.Service;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.entity.Product;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.exception.ProductNotFoundException;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.repository.ProductRepository;

@Service
public class ProductManagerService {

    private final ProductRepository productRepository;

    public ProductManagerService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with SKU: " + sku));
    }

    public Product restockProduct(String sku, int quantityToAdd) {
        Product product = findProductBySku(sku);
        product.setQuantity(product.getQuantity() + quantityToAdd);
        return productRepository.save(product);
    }
}
