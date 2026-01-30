package root.cyb.mhr.ProductInventorySystemComprehensiveTestingSuite.util;

public class ProductCalculator {

    public double calculateDiscountedPrice(double originalPrice, double discountRate) {
        if (discountRate < 0 || discountRate > 100) {
            throw new IllegalArgumentException("Discount rate must be between 0 and 100");
        }
        double discountAmount = originalPrice * (discountRate / 100);
        return originalPrice - discountAmount;
    }

    public boolean isQuantitySufficient(int currentQuantity, int requiredQuantity) {
        return currentQuantity >= requiredQuantity;
    }
}
