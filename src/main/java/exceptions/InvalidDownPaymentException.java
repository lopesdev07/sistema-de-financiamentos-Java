package exceptions;

public class InvalidDownPaymentException extends Exception {
    public InvalidDownPaymentException() {
        super("The down payment cannot be greater than the property value.");
    }
}