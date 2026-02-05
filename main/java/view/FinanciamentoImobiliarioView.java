package main.java.view;

import main.java.model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FinanciamentoImobiliarioView {

    public void menuFinanciamentoImobiliario(Scanner scanner) {
        System.out.println("Este é o menu de Financiamento Imobiliário.");
        System.out.println("Escolha o que deseja fazer: ");
        System.out.println("1. Simular Financiamento");
        System.out.println("2. Gerenciar Financiamentos");
        System.out.println("3. Voltar ao Menu Principal");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1 -> menuSimulacao(scanner);
            case 2 -> menuGerenciamento(scanner);
            case 3 -> {}
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void menuGerenciamento(Scanner scanner) {}

    private void menuSimulacao(Scanner scanner) {
        System.out.println("Primeiro, escolha o tipo de imóvel para o qual deseja simular o financiamento:");
        System.out.println("Agora, informe os detalhes do imóvel:");
        criarFinanciamentoImobiliario(scanner);
        }


    public void criarFinanciamentoImobiliario(Scanner scanner) { // usar os dados no service
        try {
            TipoImovel tipoImovel = escolherTipoImovel(scanner);

            System.out.print("Valor do imóvel: ");
            double ValorImovel = scanner.nextDouble();

            System.out.print("Valor de entrada: ");
            double ValorEntrada = scanner.nextDouble();

            scanner.nextLine();
            System.out.print("Zoneamento: ");
            String Zoneamento = scanner.nextLine();

            if (tipoImovel == TipoImovel.CASA) {
                System.out.print("Quantidade de quartos: ");
                int quartos = scanner.nextInt();
                System.out.print("Vagas de garagem: ");
                int vagasGaragem = scanner.nextInt();
                System.out.print("Área do terreno: ");
                double AreaTerreno = scanner.nextDouble();
            }

            if (tipoImovel == TipoImovel.APARTAMENTO) {
                System.out.print("Andar: ");
                int setAndar = scanner.nextInt();
                System.out.print("Tem elevador (true/false): ");
                boolean setElevador = scanner.nextBoolean();
                System.out.print("Valor do condomínio: ");
                double valorCondominio = scanner.nextDouble();
            }

            if (tipoImovel == TipoImovel.TERRENO) {
                System.out.print("Área do terreno: ");
                double AreaTerreno = scanner.nextDouble();
            }

        } catch (InputMismatchException e) {
            System.err.println("Valor inválido. Tente novamente.");
            scanner.nextLine();
        }
    }

    private TipoImovel escolherTipoImovel(Scanner scanner) {
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.println("3 - Terreno");

        int opcao = scanner.nextInt();

        return switch (opcao) {
            case 1 -> TipoImovel.CASA;
            case 2 -> TipoImovel.APARTAMENTO;
            case 3 -> TipoImovel.TERRENO;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    public void mostrarSimulacaoFinanciamentoImobiliario(FinanciamentoImobiliario fin) {
        System.out.println("Aqui estão os detalhes da sua simulação de financiamento imobiliário:");
        System.out.println(fin.toString());

    }
}