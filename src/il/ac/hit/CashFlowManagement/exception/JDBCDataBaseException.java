package il.ac.hit.CashFlowManagement.exception;

/**
 * Exception for JDBCDataBase
 */
public class JDBCDataBaseException extends Exception {
    /**
     * Parameterless C'tor
     */
    public JDBCDataBaseException() {
        super();
    }

    /**
     * C'tor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public JDBCDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * C'tor
     * @param message message that represent the exception
     */
    public JDBCDataBaseException(String message) {
        super(message);
    }
}