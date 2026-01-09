package view;

import java.util.InputMismatchException;
import java.util.Scanner;
/** Classe responsável por interagir com o usuário para operações relacionadas ao menu principal.
 * Interage com o usuário e o permite escolher o tipo de financiamento que deseja gerenciar.
 */
public class MenuPrincipalView {
    boolean opcaocerta = false;
    CasaView casaView = new CasaView();
    ApartamentoView apartamentoView = new ApartamentoView();
    TerrenoView terrenoView = new TerrenoView();

    public MenuPrincipalView(Scanner scanner) {
    }
    /** Exibe o menu principal para o usuário.
     *
     * @param scanner Scanner para leitura de entradas do usuário.
     */
    public void exibirMenuPrincipal(Scanner scanner) {
        while (!opcaocerta) {

            try {
                System.out.println("=== Menu Principal ===");
                System.out.println("Bem vindo ao menu principal do Sistema de Financiamentos!");
                System.out.println("Escolha a opção que determina qual tipo de financiamento deseja gerenciar: ");
                System.out.println("1. Casas");
                System.out.println("2. Apartamentos");
                System.out.println("3. Terrenos");
                System.out.println("4. Sair do sistema");
                System.out.print("Escolha uma opção: ");

                switch(scanner.nextInt()) {
                    case 1:
                        casaView.menuCasa(scanner);
                        break;
                    case 2:
                        apartamentoView.menuApartamento(scanner);
                        break;
                    case 3:
                        terrenoView.menuTerreno(scanner);
                        break;
                    case 4:
                        System.out.println("Saindo do sistema. Até mais!");
                        opcaocerta = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine();
            }
        }
    }
}
