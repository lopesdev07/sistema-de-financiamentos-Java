package main.java.view;

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
            case 1:
                menuSimulacao(scanner);
                break;
            case 2:
                menuGerenciamento(scanner);
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }


    private void menuSimulacao(Scanner scanner) {
        System.out.println("Ao fazer uma simulação, você poderá ver diferentes cenários de financiamento com base nos seus dados.");
        System.out.println("E caso decida prosseguir, poderá salvar a simulação como um financiamento real.");
        System.out.println("Agora, decida o tipo de imóvel para o qual deseja simular o financiamento:");
        System.out.println("1. Casa");
        System.out.println("2. Apartamento");
        System.out.println("3. Terreno");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Iniciando simulação para Casa...");
                simularFinanciamentoCasa(scanner);
                break;
            case 2:
                System.out.println("Iniciando simulação para Apartamento...");
                simularFinanciamentoApartamento(scanner);
                break;
            case 3: 
                System.out.println("Iniciando simulação para Terreno...");
                simularFinanciamentoTerreno(scanner);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
    }

}

    public FinanciamentoImobiliario criarFinanciamentoImobiliario(Scanner scanner) {
        FinanciamentoImobiliario fin = new FinanciamentoImobiliario();
        

        fin.setValorImovel(scanner.nextDouble());
        fin.setValorEntrada(scanner.nextDouble());
        fin.setAreaTerreno(scanner.nextDouble());
        fin.setZoneamento(scanner.nextLine());

        if (tipoImovel == TipoImovel.CASA) {
            fin.setQuartos(scanner.nextInt());
            fin.setVagasGaragem(scanner.nextInt());}

        if (tipoImovel == TipoImovel.APARTAMENTO) {
            fin.setAndar(scanner.nextInt());
            fin.setElevador(scanner.nextBoolean());
            fin.setValorCondominio(scanner.nextDouble());
        }

        if (tipoImovel == TipoImovel.TERRENO) {
            fin.setAreaTerreno(scanner.nextDouble());
        }
        return fin;
}

    public void mostrarSimulacaoFinanciamentoImobiliario(FinanciamentoImobiliario fin) {
        System.out.println("Aqui estão os detalhes da sua simulação de financiamento imobiliário:");
        System.out.println(fin.toString());
        
    }
}