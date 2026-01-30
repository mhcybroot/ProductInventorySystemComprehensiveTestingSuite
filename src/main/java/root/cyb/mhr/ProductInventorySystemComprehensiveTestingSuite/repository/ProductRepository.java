package root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}
