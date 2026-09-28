package exception;

/**
 * Custom (user-defined) checked exception.
 *
 * Thrown when a product ID does not exist in the system.
 */
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
