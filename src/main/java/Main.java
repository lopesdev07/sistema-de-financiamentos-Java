import view.AuthView;
import view.MenuPrincipalView;
import repository.AuthRepository;
import service.AuthService;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuPrincipalView menu = new MenuPrincipalView();
        AuthRepository authRepo = new AuthRepository();
        AuthService authService = new AuthService(authRepo);
        AuthView authView = new AuthView(authService);



        // Fluxo principal (autenticação / menu)
        authView.menuLoginOuRegistro(scanner);
        menu.exibirMenuPrincipal(scanner);

        scanner.close();
    }
}