package service;

import exceptions.EntradaMaiorQueValorDoImovelException;
import exceptions.NenhumFinanciamentoEncontradoException;
import model.*;
import repository.FinanciamentoImobiliarioRepository;

import java.sql.SQLException;
import java.util.List;

public class FinanciamentoImobiliarioService {

    private FinanciamentoImobiliarioRepository repository;

    private FinanciamentoImobiliario financiamentoAtual;

    public FinanciamentoImobiliarioService(FinanciamentoImobiliarioRepository repository) {
        this.repository = repository;
    }

    private void validarEntrada(double valorEntrada, double valorImovel)
            throws EntradaMaiorQueValorDoImovelException {

        if (valorEntrada > valorImovel) {
            throw new EntradaMaiorQueValorDoImovelException();
        }
    }

    private void validarDados(double valorEntrada, double valorImovel, int prazoEmMeses, CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel) {

        if (valorEntrada < 0 || valorImovel <= 0 || prazoEmMeses <= 0) {
            throw new IllegalArgumentException("Valores devem ser positivos.");
        }

        if (condicaoImovel == null)
            throw new IllegalArgumentException("Condição do imóvel obrigatória.");

        if (tipoAmortizacao == null)
            throw new IllegalArgumentException("Tipo de amortização obrigatório.");

        if (tipoImovel == null)
            throw new IllegalArgumentException("Tipo de imóvel obrigatório.");
    }

    public void simularFinanciamento(double valorImovel, double valorEntrada, int prazoEmMeses, CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel, int vagasGaragem, int quartos, double areaTerreno, int andar, boolean elevador, double valorCondominio, String zoneamento) throws EntradaMaiorQueValorDoImovelException, IllegalArgumentException {

        if (!Sessao.isLogado()) {
            throw new IllegalStateException("Usuário não autenticado.");
        }

        validarDados(valorEntrada, valorImovel, prazoEmMeses, condicaoImovel, tipoAmortizacao, tipoImovel);

        validarEntrada(valorEntrada, valorImovel);

        double valorFinanciado = valorImovel - valorEntrada;

        double taxaJuros = (condicaoImovel == CondicaoImovel.USADO) ? 7.0 : 5.0;

        FinanciamentoStatus status = valorFinanciado > 0 ? FinanciamentoStatus.APROVADO : FinanciamentoStatus.RECUSADO;

        financiamentoAtual = new FinanciamentoImobiliario(valorFinanciado, prazoEmMeses, taxaJuros, tipoAmortizacao, status, 0);

        financiamentoAtual.setUserId(Sessao.getUserId());
        financiamentoAtual.setVagasGaragem(vagasGaragem);
        financiamentoAtual.setQuartos(quartos);
        financiamentoAtual.setAreaTerreno(areaTerreno);
        financiamentoAtual.setAndar(andar);
        financiamentoAtual.setElevador(elevador);
        financiamentoAtual.setValorCondominio(valorCondominio);
        financiamentoAtual.setZoneamento(zoneamento);
        financiamentoAtual.setValorImovel(valorImovel);
        financiamentoAtual.setValorEntrada(valorEntrada);
        financiamentoAtual.setCondicaoImovel(condicaoImovel);
        financiamentoAtual.setTipoImovel(tipoImovel);

             calcularParcelas(financiamentoAtual);
    }

    private void calcularParcelas(FinanciamentoImobiliario financiamento) {

        double saldoDevedor = financiamento.getValorFinanciado();
        int parcelas = financiamento.getPrazoEmMeses();
        double taxaJurosMensal =
                (financiamento.getTaxaJurosAnual() / 100) / 12;

        double valorTotalPago = 0;
        double valorParcela = 0;

        if (financiamento.getTipoAmortizacao() == TipoAmortizacao.SAC) {

            double amortizacao = saldoDevedor / parcelas;

            for (int i = 0; i < parcelas; i++) {
                double juros = saldoDevedor * taxaJurosMensal;
                valorParcela = amortizacao + juros;
                valorTotalPago += valorParcela;
                saldoDevedor -= amortizacao;
            }

        } else if (financiamento.getTipoAmortizacao() == TipoAmortizacao.PRICE) {

            valorParcela = saldoDevedor * (taxaJurosMensal / (1 - Math.pow(1 + taxaJurosMensal, -parcelas)));

            valorTotalPago = valorParcela * parcelas;
        }

        financiamento.setValorParcela(valorParcela);
        financiamento.setValorTotalPago(valorTotalPago);
    }

    public FinanciamentoImobiliario getFinanciamentoAtual() {
        return financiamentoAtual;
    }

    public void salvarFinanciamentoAtual() throws SQLException {

        if (financiamentoAtual == null) {
            throw new IllegalStateException("Nenhuma simulação para salvar.");
        }

        repository.salvarFinanciamento(financiamentoAtual);

        financiamentoAtual = null;
    }
    public List<FinanciamentoImobiliario> visualizarFinanciamentos() throws SQLException, NenhumFinanciamentoEncontradoException {
        List<FinanciamentoImobiliario> financiamentos = repository.buscarFinanciamentosPorUsuario();
        if (financiamentos.isEmpty()) {
            throw new NenhumFinanciamentoEncontradoException("Nenhum financiamento encontrado para este usuário.");
            }

        return financiamentos;
    }
    }




