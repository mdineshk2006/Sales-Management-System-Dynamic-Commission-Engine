package model;

/**
 * JuniorSalesPerson — a concrete SalesPerson type.
 *
 * Demonstrates:
 *  - Inheritance (extends SalesPerson)
 *  - Method overriding (calculateCommission)
 *
 * Commission rule: 5% of the sales amount.
 */
public class JuniorSalesPerson extends SalesPerson {

    private static final double COMMISSION_RATE = 0.05; // 5%

    public JuniorSalesPerson(String employeeId, String employeeName) {
        super(employeeId, employeeName);
    }

    @Override
    public double calculateCommission(double salesAmount) {
        return salesAmount * COMMISSION_RATE;
    }

    @Override
    public String getRole() {
        return "Junior Salesperson";
    }
}
