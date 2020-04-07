package il.ac.hit.CashFlowManagement.exception;

/**
 * Exception for FormCasting
 */
public class FormCastingException extends Exception {

    /**
     * Parameterless C'tor
     */
    public FormCastingException() {
        super();
    }

    /**
     * C'tor
     * @param message message that represent the exception
     * @param cause warp the throwable
     */
    public FormCastingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * C'tor
     * @param message message that represent the exception
     */
    public FormCastingException(String message) {
        super(message);
    }
}