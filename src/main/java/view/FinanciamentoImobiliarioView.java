package view;

import exceptions.EntradaMaiorQueValorDoImovelException;
import exceptions.NenhumFinanciamentoEncontradoException;
import model.*;
import service.FinanciamentoImobiliarioService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FinanciamentoImobiliarioView {
    private final FinanciamentoImobiliarioService service;

    public FinanciamentoImobiliarioView(FinanciamentoImobiliarioService service) {
        this.service = service;
    }

    public void menuFinanciamentoImobiliario(Scanner scanner) throws SQLException {
        System.out.println("Este é o menu de Financiamento Imobiliário.");
        System.out.println("Coloque o número correspondente a ação que deseja realizar:");
        System.out.println("1. Simular Financiamento");
        System.out.println("2. Gerenciar Financiamentos");
        System.out.println("3. Voltar ao Menu Principal");
        System.out.print("Escolha o que deseja fazer: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1 -> menuSimulacao(scanner);
            case 2 -> menuGerenciamento(scanner);
            case 3 -> System.out.println("Voltando ao Menu Principal...");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void menuGerenciamento(Scanner scanner) throws SQLException {
        System.out.println("Este é o menu de gerenciamento de financiamentos imobiliários.");
        System.out.println("Aqui você pode visualizar, editar ou excluir suas simulações de financiamento imobiliário salvas.");
        System.out.println("Coloque o número correspondente à ação que deseja realizar:");
        System.out.println("1. Visualizar financiamentos salvos");
        System.out.println("2. Editar financiamentos salvos");
        System.out.println("3. Detalhar financiamento específico");
        System.out.print("Escolha o que deseja fazer: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1 -> visualizarFinanciamentoImobiliario();
            case 2 -> menuEditarFinanciamentoImobiliario(scanner);
            case 3 -> detalhesFinanciamentoImobiliario(scanner);
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void visualizarFinanciamentoImobiliario() {
        try {
            List<FinanciamentoImobiliario> financiamentos = service.visualizarFinanciamentos();

            for (FinanciamentoImobiliario f : financiamentos) {
                System.out.printf("""
            -- Financiamento ID: %d --
            Valor Financiado: R$ %.2f
            Prazo: %d meses
            Taxa de Juros: %.2f%%
            Tipo de Amortização: %s
            Tipo de Imóvel: %s
            Status: %s
            """,
                        f.getFinID(),
                        f.getValorFinanciado(),   // BigDecimal funciona com %.2f
                        f.getPrazoEmMeses(),
                        f.getTaxaJurosAnual(),
                        f.getTipoAmortizacao(),
                        f.getTipoImovel(),
                        f.getFinanciamentoStatus()
                );
            }

        } catch (NenhumFinanciamentoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro genérico ao acessar o banco de dados. Tente novamente.");
        }
    }

    private void menuEditarFinanciamentoImobiliario(Scanner scanner) {
        try {
            System.out.println("Digite o ID do financiamento que deseja editar:");
            int idFinanciamento = scanner.nextInt();
            scanner.nextLine();

            FinanciamentoImobiliario antigoFin = service.buscarFinanciamentoPorId(idFinanciamento);

            TipoImovel tipoImovel = escolherTipoImovel(scanner);
            TipoAmortizacao tipoAmortizacao = escolherTipoAmortizacao(scanner);
            CondicaoImovel condicaoImovel = definirCondicaoImovel(scanner);

            System.out.printf("(Atual: R$ %.2f) Novo valor do imóvel: ", antigoFin.getValorImovel());
            BigDecimal valorImovel = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.printf("(Atual: R$ %.2f) Novo valor de entrada: ", antigoFin.getValorEntrada());
            BigDecimal valorEntrada = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.printf("(Atual: %d) Novo prazo em meses: ", antigoFin.getPrazoEmMeses());
            int prazoEmMeses = scanner.nextInt();
            scanner.nextLine();

            System.out.printf("(Atual: %s) Novo zoneamento: ", antigoFin.getZoneamento());
            String zoneamento = scanner.nextLine();

            Integer quartos = null;
            Integer vagasGaragem = null;
            BigDecimal areaTerreno = null;
            Integer andar = null;
            Boolean elevador = null;
            BigDecimal valorCondominio = null;

            if (tipoImovel == TipoImovel.CASA) {
                System.out.printf("(Atual: %d) Quartos: ", antigoFin.getQuartos());
                quartos = scanner.nextInt();
                scanner.nextLine();
                System.out.printf("(Atual: %d) Vagas de garagem: ", antigoFin.getVagasGaragem());
                vagasGaragem = scanner.nextInt();
                scanner.nextLine();
                System.out.printf("(Atual: %.2f) Área do terreno: ", antigoFin.getAreaTerreno());
                areaTerreno = scanner.nextBigDecimal();
                scanner.nextLine();
            }

            if (tipoImovel == TipoImovel.APARTAMENTO) {
                System.out.printf("(Atual: %d) Andar: ", antigoFin.getAndar());
                andar = scanner.nextInt();
                scanner.nextLine();
                System.out.printf("(Atual: %s) Tem elevador? (1 para sim, 2 para não): ", antigoFin.getElevador() != null && antigoFin.getElevador() ? "Sim" : "Não");
                int respostaElevador = scanner.nextInt();
                scanner.nextLine();
                elevador = respostaElevador == 1;
                System.out.printf("(Atual: R$ %.2f) Valor do condomínio: ", antigoFin.getValorCondominio());
                valorCondominio = scanner.nextBigDecimal();
                scanner.nextLine();
            }

            if (tipoImovel == TipoImovel.TERRENO) {
                System.out.printf("(Atual: %.2f) Área do terreno: ", antigoFin.getAreaTerreno());
                areaTerreno = scanner.nextBigDecimal();
                scanner.nextLine();
            }

            service.editarFinanciamento(idFinanciamento, valorEntrada, valorImovel, prazoEmMeses,
                    condicaoImovel, tipoAmortizacao, tipoImovel, quartos, vagasGaragem,
                    areaTerreno, andar, elevador, valorCondominio, zoneamento);

            System.out.println("Financiamento editado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, tente novamente.");
            scanner.nextLine();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro genérico ao acessar o banco de dados. Tente novamente.");
        } catch (EntradaMaiorQueValorDoImovelException e) {
            System.out.println(e.getMessage());
        }
    }

    private void detalhesFinanciamentoImobiliario(Scanner scanner) throws SQLException {
            try {
            System.out.print("Digite o ID do financiamento que deseja ver detalhes: ");
            int idFinanciamento = scanner.nextInt();
            scanner.nextLine();
            FinanciamentoImobiliario financiamento = service.buscarFinanciamentoPorId(idFinanciamento);
            System.out.println("===== DETALHES DO FINANCIAMENTO =====");
            System.out.printf("ID do financiamento: %d%n", financiamento.getFinID());
            System.out.printf("Valor Financiado: R$ %.2f%n", financiamento.getValorFinanciado());
            System.out.printf("Prazo: %d meses%n", financiamento.getPrazoEmMeses());
            System.out.printf("Taxa de Juros Anual: %.2f%%%n", financiamento.getTaxaJurosAnual());
            System.out.printf("Tipo de Amortização: %s%n", financiamento.getTipoAmortizacao());
            System.out.printf("Tipo de Imóvel: %s%n", financiamento.getTipoImovel());
            System.out.printf("Status do Financiamento: %s%n", financiamento.getFinanciamentoStatus());

            if (financiamento.getTipoImovel() == TipoImovel.CASA) {
                System.out.printf("Quartos: %d%n", financiamento.getQuartos());
                System.out.printf("Vagas de Garagem: %d%n", financiamento.getVagasGaragem());
                System.out.printf("Área do Terreno: %.2f m²%n", financiamento.getAreaTerreno());
            } else if (financiamento.getTipoImovel() == TipoImovel.APARTAMENTO) {
                System.out.printf("Andar: %d%n", financiamento.getAndar());
                System.out.printf("Elevador: %s%n", (financiamento.getElevador() ? "Sim" : "Não"));
                System.out.printf("Valor do Condomínio: R$ %.2f%n", financiamento.getValorCondominio());
            } else if (financiamento.getTipoImovel() == TipoImovel.TERRENO) {
                System.out.printf("Área do Terreno: %.2f m²%n", financiamento.getAreaTerreno());
            }}
            catch (SQLException e) {
                System.out.println("Erro genérico ao acessar o banco de dados. Tente novamente.");
    }}



    private void menuSimulacao(Scanner scanner) {
        criarFinanciamentoImobiliario(scanner);
        mostrarSimulacaoFinanciamentoImobiliario(service.getFinanciamentoAtual());
        salvarFinanciamentoImobiliario(scanner);
    }

    private void salvarFinanciamentoImobiliario(Scanner scanner) {
        System.out.println("Deseja salvar esta simulação? Caso sim, digite 1, caso não queira salvar digite 2");
        System.out.print("Sua escolha: ");
        int resposta = scanner.nextInt();
        scanner.nextLine();
        try {
            if (resposta == 1) {
                service.salvarFinanciamentoAtual();
                System.out.println("Simulação salva com sucesso!");
            } else if (resposta == 2) {
                System.out.println("Simulação não salva.");
            } else {
                System.out.println("Opção inválida. Tente 1 para salvar ou 2 para não salvar.");
            }
        } catch (SQLException e) {
            System.out.println("Erro genérico ao salvar a simulação no banco de dados.");
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void criarFinanciamentoImobiliario(Scanner scanner) {
        try {
            Integer quartos = null;
            Integer vagasGaragem = null;
            BigDecimal areaTerreno = null;
            Integer andar = null;
            Boolean elevador = null;
            BigDecimal valorCondominio = null;

            TipoImovel tipoImovel = escolherTipoImovel(scanner);
            TipoAmortizacao tipoAmortizacao = escolherTipoAmortizacao(scanner);
            CondicaoImovel condicaoImovel = definirCondicaoImovel(scanner);

            System.out.print("Valor do imóvel: ");
            BigDecimal valorImovel = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Valor de entrada: ");
            BigDecimal valorEntrada = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Zoneamento: ");
            String zoneamento = scanner.nextLine();

            System.out.print("Prazo em meses desejado: ");
            int prazoEmMeses = scanner.nextInt();
            scanner.nextLine();

            if (tipoImovel == TipoImovel.CASA) {
                System.out.print("Quantidade de quartos: ");
                quartos = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Vagas de garagem: ");
                vagasGaragem = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Área do terreno: ");
                areaTerreno = scanner.nextBigDecimal();
                scanner.nextLine();
            }

            if (tipoImovel == TipoImovel.APARTAMENTO) {
                System.out.print("Andar: ");
                andar = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Tem elevador? (1 para sim, 2 para não): ");
                int respostaElevador = scanner.nextInt();
                scanner.nextLine();
                if (respostaElevador == 1) {
                    elevador = true;
                } else if (respostaElevador == 2) {
                    elevador = false;
                } else {
                    System.out.println("Opção inválida para elevador. Considerando como 'não'.");
                    elevador = false;
                }
                System.out.print("Valor do condomínio: ");
                valorCondominio = scanner.nextBigDecimal();
                scanner.nextLine();
            }

            if (tipoImovel == TipoImovel.TERRENO) {
                System.out.print("Área do terreno: ");
                areaTerreno = scanner.nextBigDecimal();
                scanner.nextLine();
            }

            service.simularFinanciamento(valorImovel, valorEntrada, prazoEmMeses, condicaoImovel,
                    tipoAmortizacao, tipoImovel, vagasGaragem, quartos, areaTerreno,
                    andar, elevador, valorCondominio, zoneamento);

            System.out.println("Financiamento criado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Valor inválido. Tente novamente.");
            scanner.nextLine();
        } catch (EntradaMaiorQueValorDoImovelException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private TipoAmortizacao escolherTipoAmortizacao(Scanner scanner) {
        System.out.println("Escolha o tipo de amortização:");
        System.out.println("1 - SAC");
        System.out.println("2 - PRICE");
        System.out.print("Sua escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return switch (opcao) {
            case 1 -> TipoAmortizacao.SAC;
            case 2 -> TipoAmortizacao.PRICE;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    private TipoImovel escolherTipoImovel(Scanner scanner) {
        System.out.println("Escolha o tipo de imóvel:");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.println("3 - Terreno");
        System.out.print("Sua escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return switch (opcao) {
            case 1 -> TipoImovel.CASA;
            case 2 -> TipoImovel.APARTAMENTO;
            case 3 -> TipoImovel.TERRENO;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    private CondicaoImovel definirCondicaoImovel(Scanner scanner) {
        System.out.println("Informe a condição atual do imóvel:");
        System.out.println("1 - Novo");
        System.out.println("2 - Usado");
        System.out.print("Sua escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return switch (opcao) {
            case 1 -> CondicaoImovel.NOVO;
            case 2 -> CondicaoImovel.USADO;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    private void mostrarSimulacaoFinanciamentoImobiliario(FinanciamentoImobiliario fin) {
        System.out.println("Aqui estão os detalhes da sua simulação de financiamento imobiliário:");
        System.out.println(fin.toString());
    }
}