package model;

/**
 * Sale.java
 *
 * Represents a single sales transaction linking a salesperson,
 * a product, the quantity sold, the total amount, and the
 * commission earned on that sale.
 */
public class Sale {

    private static int saleCounter = 1000;

    private final int saleId;
    private final SalesPerson salesperson;
    private final Product product;
    private final int quantity;
    private final double totalAmount;
    private final double commission;
    private final boolean discountApplied;

    public Sale(SalesPerson salesperson, Product product, int quantity,
                double totalAmount, double commission, boolean discountApplied) {
        this.saleId = ++saleCounter;
        this.salesperson = salesperson;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.commission = commission;
        this.discountApplied = discountApplied;
    }

    public int getSaleId() {
        return saleId;
    }

    public SalesPerson getSalesperson() {
        return salesperson;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getCommission() {
        return commission;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n"
                + "Sale ID          : " + saleId + "\n"
                + "Salesperson      : " + salesperson.getEmployeeName()
                + " (" + salesperson.getRole() + ")\n"
                + "Product          : " + product.getProductName() + "\n"
                + "Quantity         : " + quantity + "\n"
                + "Discount Applied : " + (discountApplied ? "Yes (10%)" : "No") + "\n"
                + "Total Amount     : Rs. " + totalAmount + "\n"
                + "Commission       : Rs. " + commission + "\n"
                + "----------------------------------------";
    }
}
