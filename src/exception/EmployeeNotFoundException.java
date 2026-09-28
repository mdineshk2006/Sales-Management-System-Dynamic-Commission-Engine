package exception;

/**
 * Custom (user-defined) checked exception.
 *
 * Thrown when a salesperson (employee) ID does not exist in the system.
 */
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
