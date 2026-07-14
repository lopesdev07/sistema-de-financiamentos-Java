package util;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    public int intScanner(Scanner scanner) {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    public static String stringScanner(Scanner scanner) {
        String value;
        while (true) {
            value = scanner.nextLine();
            if (value.isBlank()) {
                System.out.println("Error: Invalid input. Input cannot be empty.");
            } else {
                return value;
            }
        }
    }

    public BigDecimal bigDecimalScanner(Scanner scanner) {
        BigDecimal value;
        while (true) {
            try {
                value = scanner.nextBigDecimal();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid decimal number.");
                scanner.nextLine();
            }
        }
    }


}
