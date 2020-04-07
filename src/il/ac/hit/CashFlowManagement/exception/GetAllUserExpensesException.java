package il.ac.hit.CashFlowManagement.exception;

/**
 * Exception for getAllUserExpenses query
 */
public class GetAllUserExpensesException extends Exception {

    /**
     * Parameterless C'tor
     */
    public GetAllUserExpensesException() {
        super();
    }

    /**
     * C'tor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public GetAllUserExpensesException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * C'tor
     * @param message message that represent the exception
     */
    public GetAllUserExpensesException(String message) {
        super(message);
    }
}