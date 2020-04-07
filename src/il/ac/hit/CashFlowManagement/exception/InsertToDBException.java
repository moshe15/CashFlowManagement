package il.ac.hit.CashFlowManagement.exception;

/**
 * Exception for InsertToDB query
 */
public class InsertToDBException extends Exception {

    /**
     * Parameterless C'tor
     */
    public InsertToDBException() {
        super();
    }

    /**
     * C'tor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public InsertToDBException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * C'tor
     * @param message message that represent the exception
     */
    public InsertToDBException(String message) {
        super(message);
    }
}