Module 17 assignment
Title: Product Inventory System: Comprehensive Testing Suite

Problem Overview:

You are tasked with ensuring the stability and reliability of a new Product Inventory Management System API. This inventory system represents a small, crucial part of a larger eCommerce platform. To do this, you must develop a comprehensive, multi-layered testing suite. The goal is to apply Pure JUnit for isolated business logic, Mockito for testing the service layer by isolating it from the repository, and Spring Boot Test to validate the entire application stack, including controller and exception handling.

Task 1: Core Utility Logic Testing (Pure JUnit)

The first step is to test the fundamental, dependency-free business calculations.

1. Create a Utility Class: Implement a class named
ProductCalculator
 with the following methods:

- calculateDiscountedPrice(double originalPrice, double discountRate)
 : Calculates the final price after a percentage discount.

- isQuantitySufficient(int currentQuantity, int requiredQuantity)
 : Checks if the stock is sufficient for an order.

2. Write ProductCalculatorTest : Use only JUnit 5 assertions to test the logic in isolation.

- Verify discount calculations for various rates (0%, 50%, full price).

- Verify the quantity check for both sufficient and insufficient stock scenarios.

Task 2: Service Layer Business Logic Testing (Mockito)

Next, you will test the core service logic, focusing on product management that interacts with the data layer.

1. Develop Service Methods: In your
ProductManagerService, implement two methods that depend on a (mocked) ProductRepository
 :

- findProductBySku(String sku)
 : Retrieve a product. Must throw a custom ProductNotFoundException if the SKU does not exist.

- restockProduct(String sku, int quantityToAdd)
 : Find a product, update its quantity, and save the result.

2. Write ProductManagerServiceTest: Use
@ExtendWith(MockitoExtension.class)
 to test the service in isolation.

- Successful Find: Mock the repository to return a product and verify the correct product is retrieved.

- Product Not Found: Mock the repository to return Optional.empty() and verify that the
ProductNotFoundException is correctly thrown.

- Successful Restock:

Verify that the product's quantity is updated correctly and that repository.save() is called exactly once with the updated object.

- Restock Not Found: Verify that ProductNotFoundException is thrown when attempting to restock a non-existent product.
