package model;

/**
 * Product.java
 *
 * Represents a product available for sale.
 * Stores product information and stock, and provides basic
 * stock-management operations.
 */
public class Product {

    private String productId;
    private String productName;
    private double price;
    private int stock;

    public Product(String productId, String productName, double price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    /**
     * Checks whether the requested quantity is available in stock.
     */
    public boolean checkStock(int quantity) {
        return quantity <= stock;
    }

    /**
     * Reduces the stock after a successful sale.
     * Caller is expected to have already validated availability
     * via checkStock() before calling this method.
     */
    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    public void displayInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Product ID: " + productId
                + " | Name: " + productName
                + " | Price: Rs. " + price
                + " | Stock: " + stock;
    }
}
