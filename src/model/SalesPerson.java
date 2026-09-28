package model;

import service.Commissionable;

/**
 * Abstract class representing a generic Salesperson.
 *
 * Demonstrates:
 *  - Abstract class
 *  - Abstract method (calculateCommission)
 *  - Interface implementation (Commissionable)
 *  - Common employee fields shared across all salesperson types
 *
 * Concrete subclasses (JuniorSalesPerson, SeniorSalesPerson, SalesManager)
 * MUST provide their own implementation of calculateCommission(),
 * since commission rules differ per salesperson type.
 */
public abstract class SalesPerson implements Commissionable {

    protected String employeeId;
    protected String employeeName;

    public SalesPerson(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Abstract method — every salesperson type calculates commission
     * differently based on the total sales amount.
     *
     * @param salesAmount the total value of the sale
     * @return the commission amount for this salesperson
     */
    @Override
    public abstract double calculateCommission(double salesAmount);

    /**
     * Returns the salesperson's role/type — used for display purposes.
     * Concrete subclasses should override this to identify themselves.
     */
    public abstract String getRole();

    @Override
    public String toString() {
        return "Employee ID: " + employeeId
                + " | Name: " + employeeName
                + " | Role: " + getRole();
    }
}
