package service;

import model.Product;
import model.Sale;
import model.SalesPerson;
import exception.EmployeeNotFoundException;
import exception.ProductNotFoundException;
import exception.InvalidQuantityException;
import exception.InvalidSalesAmountException;

import java.util.ArrayList;
import java.util.List;

/**
 * SalesService.java
 *
 * Central service class that manages salespersons, products, and sales
 * transactions. Coordinates validation, the CommissionEngine, and
 * stock updates.
 *
 * Demonstrates method overloading via the two createSale() methods.
 */
public class SalesService {

    private final List<SalesPerson> salesPersons = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Sale> sales = new ArrayList<>();
    private final CommissionEngine commissionEngine = new CommissionEngine();

    // ---------------- Salesperson management ----------------

    public void addSalesPerson(SalesPerson salesPerson) {
        salesPersons.add(salesPerson);
    }

    public List<SalesPerson> getSalesPersons() {
        return salesPersons;
    }

    public SalesPerson findSalesPersonById(String employeeId) throws EmployeeNotFoundException {
        for (SalesPerson sp : salesPersons) {
            if (sp.getEmployeeId().equalsIgnoreCase(employeeId)) {
                return sp;
            }
        }
        throw new EmployeeNotFoundException("Employee not found with ID: " + employeeId);
    }

    // ---------------- Product management ----------------

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findProductById(String productId) throws ProductNotFoundException {
        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(productId)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found with ID: " + productId);
    }

    // ---------------- Sales management ----------------

    public List<Sale> getSales() {
        return sales;
    }

    /**
     * Overload 1 - create a sale without a discount.
     * (Method Overloading - Polymorphism)
     */
    public Sale createSale(String employeeId, String productId, int quantity)
            throws EmployeeNotFoundException, ProductNotFoundException,
            InvalidQuantityException, InvalidSalesAmountException {
        return createSale(employeeId, productId, quantity, false);
    }

    /**
     * Overload 2 - create a sale with an optional 10% discount applied.
     * (Method Overloading - Polymorphism)
     *
     * This is the core transaction workflow:
     *   validate quantity -> find employee -> find product ->
     *   check stock -> compute total -> compute commission ->
     *   reduce stock -> record sale.
     */
    public Sale createSale(String employeeId, String productId, int quantity, boolean applyDiscount)
            throws EmployeeNotFoundException, ProductNotFoundException,
            InvalidQuantityException, InvalidSalesAmountException {

        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero.");
        }

        SalesPerson salesperson = findSalesPersonById(employeeId);
        Product product = findProductById(productId);

        if (!product.checkStock(quantity)) {
            throw new InvalidQuantityException(
                    "Insufficient stock for product '" + product.getProductName()
                            + "'. Available stock: " + product.getStock());
        }

        double totalAmount = product.getPrice() * quantity;

        if (applyDiscount) {
            totalAmount = totalAmount * 0.90; // flat 10% discount
        }

        // CommissionEngine works with the Commissionable interface,
        // but 'salesperson' is a concrete subclass object -> dynamic binding.
        double commission = commissionEngine.computeCommission(salesperson, totalAmount);

        product.reduceStock(quantity);

        Sale sale = new Sale(salesperson, product, quantity, totalAmount, commission, applyDiscount);
        sales.add(sale);

        return sale;
    }
}
