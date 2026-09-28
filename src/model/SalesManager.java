package model;

/**
 * SalesManager — a concrete SalesPerson type.
 *
 * Demonstrates:
 *  - Inheritance (extends SalesPerson)
 *  - Method overriding (calculateCommission)
 *
 * Commission rule: 10% of the sales amount.
 */
public class SalesManager extends SalesPerson {

    private static final double COMMISSION_RATE = 0.10; // 10%

    public SalesManager(String employeeId, String employeeName) {
        super(employeeId, employeeName);
    }

    @Override
    public double calculateCommission(double salesAmount) {
        return salesAmount * COMMISSION_RATE;
    }

    @Override
    public String getRole() {
        return "Sales Manager";
    }
}
