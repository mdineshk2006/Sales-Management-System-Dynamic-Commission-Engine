package exception;

/**
 * Custom (user-defined) checked exception.
 *
 * Thrown when a sales amount used in a calculation is invalid
 * (e.g. zero or negative).
 */
public class InvalidSalesAmountException extends Exception {

    public InvalidSalesAmountException(String message) {
        super(message);
    }
}
