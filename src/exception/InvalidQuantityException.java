package exception;

/**
 * Custom (user-defined) checked exception.
 *
 * Thrown when a quantity entered is invalid — zero, negative,
 * or greater than the available stock.
 */
public class InvalidQuantityException extends Exception {

    public InvalidQuantityException(String message) {
        super(message);
    }
}
