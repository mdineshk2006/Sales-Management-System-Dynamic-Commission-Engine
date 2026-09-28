package service;

/**
 * Commissionable interface.
 *
 * Represents the general behaviour of "something that can calculate
 * a commission based on a sales amount".
 *
 * SalesPerson (and its subclasses) implement this interface, and
 * CommissionEngine depends on this interface rather than any
 * concrete class — demonstrating loose coupling through interfaces.
 */
public interface Commissionable {

    /**
     * Calculates the commission for a given sales amount.
     *
     * @param salesAmount the total value of the sale
     * @return the commission amount
     */
    double calculateCommission(double salesAmount);
}
