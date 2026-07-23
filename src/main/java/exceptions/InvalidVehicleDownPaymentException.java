package exceptions;

public class InvalidVehicleDownPaymentException extends Exception {
    public InvalidVehicleDownPaymentException() {
        super("The down payment cannot be greater than the vehicle value.");
    }
}
