package exceptions;

public class FinancingNotFoundException extends Exception {
    public FinancingNotFoundException() {
        super("No financing records were found for this user.");
    }
}