package service;

import exception.InvalidSalesAmountException;

/**
 * CommissionEngine — the "Dynamic Commission Engine".
 *
 * Responsible for calculating commission for a sale. It depends only
 * on the Commissionable interface, not on any concrete SalesPerson
 * subclass. This means the correct overridden calculateCommission()
 * method is picked at runtime (polymorphism / dynamic binding),
 * while the engine itself stays completely generic.
 *
 * Sales amount + Salesperson  →  Commission
 */
public class CommissionEngine {

    /**
     * Computes the commission for the given salesAmount using the
     * commission rule defined by the supplied Commissionable object.
     *
     * @param commissionable the entity capable of calculating a commission
     *                       (in practice, a SalesPerson subclass object)
     * @param salesAmount    the total value of the sale
     * @return the calculated commission
     * @throws InvalidSalesAmountException if salesAmount is zero or negative
     */
    public double computeCommission(Commissionable commissionable, double salesAmount)
            throws InvalidSalesAmountException {

        if (salesAmount <= 0) {
            throw new InvalidSalesAmountException(
                    "Sales amount must be greater than zero to calculate commission.");
        }

        // Dynamic binding: the actual overridden calculateCommission()
        // that executes here depends on the real object type behind
        // the 'commissionable' reference (Junior/Senior/Manager).
        return commissionable.calculateCommission(salesAmount);
    }
}
