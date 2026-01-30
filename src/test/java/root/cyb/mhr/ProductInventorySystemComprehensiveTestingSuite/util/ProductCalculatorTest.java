package root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductCalculatorTest {

    private final ProductCalculator calculator = new ProductCalculator();

    @Test
    void calculateDiscountedPrice_NoDiscount() {
        double originalPrice = 100.0;
        double discountRate = 0.0;
        double expectedPrice = 100.0;
        assertEquals(expectedPrice, calculator.calculateDiscountedPrice(originalPrice, discountRate));
    }

    @Test
    void calculateDiscountedPrice_HalfPrice() {
        double originalPrice = 100.0;
        double discountRate = 50.0;
        double expectedPrice = 50.0;
        assertEquals(expectedPrice, calculator.calculateDiscountedPrice(originalPrice, discountRate));
    }

    @Test
    void calculateDiscountedPrice_FullDiscount() {
        double originalPrice = 100.0;
        double discountRate = 100.0;
        double expectedPrice = 0.0;
        assertEquals(expectedPrice, calculator.calculateDiscountedPrice(originalPrice, discountRate));
    }

    @Test
    void calculateDiscountedPrice_InvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateDiscountedPrice(100.0, 110.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateDiscountedPrice(100.0, -10.0));
    }

    @Test
    void isQuantitySufficient_Sufficient() {
        assertTrue(calculator.isQuantitySufficient(10, 5));
        assertTrue(calculator.isQuantitySufficient(10, 10));
    }

    @Test
    void isQuantitySufficient_Insufficient() {
        assertFalse(calculator.isQuantitySufficient(5, 10));
    }
}
