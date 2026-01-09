package main;
import view.MenuPrincipalView;
import java.util.Scanner;

/**
 * Classe main que inicia todo o programa.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuPrincipalView menu = new MenuPrincipalView(scanner);
        menu.exibirMenuPrincipal(scanner);




    }

}

    

