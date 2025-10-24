package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipalView {
    boolean opcaocerta = false;
    Scanner scanner = new Scanner(System.in);
    CasaView casaView = new CasaView();
    ApartamentoView apartamentoView = new ApartamentoView();
    TerrenoView terrenoView = new TerrenoView();

    public void exibirMenuPrincipal() {
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
                        casaView.menuCasa();
                        break;
                    case 2:
                        apartamentoView.menuApartamento();
                        break;
                    case 3:
                        terrenoView.menuTerreno();
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
