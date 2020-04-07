package il.ac.hit.CashFlowManagement.exception;

/**
 * Exception for JDBCDataBaseLogic
 */
public class JDBCDataBaseLogicException extends Exception{

    /**
     * Parameterless C'tor
     */
    public JDBCDataBaseLogicException() {
        super();
    }

    /**
     * C'tor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public JDBCDataBaseLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * C'tor
     * @param message message that represent the exception
     */
    public JDBCDataBaseLogicException(String message) {
        super(message);
    }
}
