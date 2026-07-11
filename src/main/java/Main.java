import view.AuthView;
import view.MainMenuView;
import repository.AuthRepository;
import service.AuthService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenuView mainMenu = new MainMenuView();
        AuthRepository authRepository = new AuthRepository();
        AuthService authService = new AuthService(authRepository);
        AuthView authView = new AuthView(authService);


        authView.loginOrRegisterMenu(scanner);
        mainMenu.displayMainMenu(scanner);

        scanner.close();
    }
}