package model;

/**
 * SeniorSalesPerson — a concrete SalesPerson type.
 *
 * Demonstrates:
 *  - Inheritance (extends SalesPerson)
 *  - Method overriding (calculateCommission)
 *
 * Commission rule: 8% of the sales amount.
 */
public class SeniorSalesPerson extends SalesPerson {

    private static final double COMMISSION_RATE = 0.08; // 8%

    public SeniorSalesPerson(String employeeId, String employeeName) {
        super(employeeId, employeeName);
    }

    @Override
    public double calculateCommission(double salesAmount) {
        return salesAmount * COMMISSION_RATE;
    }

    @Override
    public String getRole() {
        return "Senior Salesperson";
    }
}
