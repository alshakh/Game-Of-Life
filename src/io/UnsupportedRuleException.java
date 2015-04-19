package io;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class UnsupportedRuleException extends Exception {

    /**
     * Creates a new instance of <code>UnsupportedRuleException</code> without
     * detail message.
     */
    public UnsupportedRuleException() {
    }

    /**
     * Constructs an instance of <code>UnsupportedRuleException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnsupportedRuleException(String msg) {
        super(msg);
    }
}
