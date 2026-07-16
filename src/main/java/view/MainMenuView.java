package view;

import repository.RealEstateFinancingRepository;
import service.RealEstateFinancingService;
import util.ScannerUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenuView {

    public void displayMainMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {

            try {
                RealEstateFinancingView menu = new RealEstateFinancingView(new RealEstateFinancingService(new RealEstateFinancingRepository()));
                System.out.println("--- Main Menu ---");
                System.out.println("Welcome to the Financing Management System.");
                System.out.println("Choose the option that determines which financing type you want to manage: ");
                System.out.println("1. Real estate financing");
                System.out.println("2. Other types of financing (WIP)");
                System.out.println("3. Exit the system");
                System.out.print("Choose an option: ");

                switch(ScannerUtil.intScanner(scanner)) {
                    case 1:
                        menu.realEstateFinancingMenu(scanner);
                        break;
                    case 2:
                        System.out.println("No other financing types are available yet.");
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Error: a database error occurred. Please try again.");
            }
        }
    }
}