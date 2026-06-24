package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public FinanciamentoImobiliario buscarFinanciamentoPorId(int idFinanciamento) throws SQLException {
        FinanciamentoImobiliario financiamento = repository.buscarFinPorId(idFinanciamento);

        if (financiamento == null) {
            throw new IllegalArgumentException("Financiamento não encontrado.");
        }

        if (financiamento.getFinID() == null) {
            throw new IllegalArgumentException("Financiamento inválido.");
        }

        if (financiamento.getUserId() != Sessao.getUserId()) {
            throw new IllegalStateException("Usuário não autorizado a visualizar este financiamento.");
        }

        return financiamento;
    }

    private void validarDados(BigDecimal valorEntrada, BigDecimal valorImovel, int prazoEmMeses,
                              CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel) {

        if (valorEntrada.compareTo(BigDecimal.ZERO) < 0
                || valorImovel.compareTo(BigDecimal.ZERO) <= 0
                || prazoEmMeses <= 0) {
            throw new IllegalArgumentException("Valores devem ser positivos.");
        }
        if (condicaoImovel == null)
            throw new IllegalArgumentException("Condição do imóvel obrigatória.");

        if (tipoAmortizacao == null)
            throw new IllegalArgumentException("Tipo de amortização obrigatório.");

        if (tipoImovel == null)
            throw new IllegalArgumentException("Tipo de imóvel obrigatório.");
    }

    private void validarEntrada(BigDecimal valorEntrada, BigDecimal valorImovel)
            throws EntradaMaiorQueValorDoImovelException {

        if (valorEntrada.compareTo(valorImovel) > 0) {
            throw new EntradaMaiorQueValorDoImovelException();
        }
    }

    public void simularFinanciamento(BigDecimal valorImovel, BigDecimal valorEntrada, int prazoEmMeses,
                                     CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel,
                                     Integer vagasGaragem, Integer quartos, BigDecimal areaTerreno,
                                     Integer andar, Boolean elevador, BigDecimal valorCondominio, String zoneamento)
            throws EntradaMaiorQueValorDoImovelException {

        if (!Sessao.isLogado()) {
            throw new IllegalStateException("Usuário não autenticado.");
        }

        validarDados(valorEntrada, valorImovel, prazoEmMeses, condicaoImovel, tipoAmortizacao, tipoImovel);
        validarEntrada(valorEntrada, valorImovel);

        BigDecimal valorFinanciado = valorImovel.subtract(valorEntrada);

        BigDecimal taxaJuros = (condicaoImovel == CondicaoImovel.USADO)
                ? new BigDecimal("7.0")
                : new BigDecimal("5.0");

        FinanciamentoStatus status = valorFinanciado.compareTo(BigDecimal.ZERO) > 0
                ? FinanciamentoStatus.APROVADO
                : FinanciamentoStatus.RECUSADO;

        financiamentoAtual = new FinanciamentoImobiliario(valorFinanciado, prazoEmMeses, taxaJuros, tipoAmortizacao, tipoImovel, status, 0);

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

        BigDecimal saldoDevedor = financiamento.getValorFinanciado();
        int parcelas = financiamento.getPrazoEmMeses();
        BigDecimal taxaJurosMensal = financiamento.getTaxaJurosAnual()
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        BigDecimal valorTotalPago = BigDecimal.ZERO;
        BigDecimal valorParcela = BigDecimal.ZERO;

        if (financiamento.getTipoAmortizacao() == TipoAmortizacao.SAC) {

            BigDecimal amortizacao = saldoDevedor
                    .divide(BigDecimal.valueOf(parcelas), 10, RoundingMode.HALF_UP);

            for (int i = 0; i < parcelas; i++) {
                BigDecimal juros = saldoDevedor.multiply(taxaJurosMensal);
                valorParcela = amortizacao.add(juros);
                valorTotalPago = valorTotalPago.add(valorParcela);
                saldoDevedor = saldoDevedor.subtract(amortizacao);
            }

        } else if (financiamento.getTipoAmortizacao() == TipoAmortizacao.PRICE) {

            BigDecimal fator = BigDecimal.ONE.add(taxaJurosMensal).pow(parcelas);

            BigDecimal numerador = taxaJurosMensal.multiply(fator);
            BigDecimal denominador = fator.subtract(BigDecimal.ONE);

            valorParcela = saldoDevedor.multiply(
                    numerador.divide(denominador, 10, RoundingMode.HALF_UP));

            valorTotalPago = valorParcela.multiply(BigDecimal.valueOf(parcelas));
        }

        financiamento.setValorParcela(valorParcela);
        financiamento.setValorTotalPago(valorTotalPago);
    }

    public FinanciamentoImobiliario getFinanciamentoAtual() {
        return financiamentoAtual;
    }

    public void salvarFinanciamentoAtual() throws SQLException {
        if (!Sessao.isLogado()) {
            throw new IllegalStateException("Usuário não autenticado.");
        }
        if (financiamentoAtual == null) {
            throw new IllegalStateException("Nenhuma simulação para salvar.");
        }

        repository.salvarFinanciamento(financiamentoAtual);

        financiamentoAtual = null;
    }
    public void editarFinanciamento(FinanciamentoImobiliario financiamento, BigDecimal valorEntrada, BigDecimal valorImovel, int prazoEmMeses, CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel) throws SQLException, EntradaMaiorQueValorDoImovelException {
        if (!Sessao.isLogado()) {
            throw new IllegalStateException("Usuário não autenticado.");
        }
        if (Sessao.getUserId() == null || financiamento == null || financiamento.getFinID() == null || financiamento.getTipoImovel() == null) {
            throw new IllegalArgumentException("Financiamento inválido para edição.");
        }
        if (financiamento.getUserId() != (Sessao.getUserId())) {
            throw new IllegalStateException("Usuário não autorizado a editar este financiamento.");
        }
        // pós criação, antes de salvar
        normalizarObjetoPorTipoImovel(financiamento);
        verificarObjetoPorTipoImovel(financiamento);
        validarDados(valorEntrada, valorImovel, prazoEmMeses, condicaoImovel, tipoAmortizacao, tipoImovel);
        validarEntrada(valorEntrada, valorImovel);

        repository.editarFinanciamento(financiamento);
    }

    public void verificarObjetoPorTipoImovel(FinanciamentoImobiliario financiamento) {
        if (financiamento.getTipoImovel() == TipoImovel.CASA) {
            if (financiamento.getVagasGaragem() == null || financiamento.getAndar() != null ||
                    financiamento.getElevador() != null || financiamento.getValorCondominio() != null) {
                throw new IllegalArgumentException("Dados inválidos para tipo de imóvel CASA.");
            }
        } else if (financiamento.getTipoImovel() == TipoImovel.APARTAMENTO) {
            if (financiamento.getAreaTerreno() != null) {
                throw new IllegalArgumentException("Dados inválidos para tipo de imóvel APARTAMENTO.");
            }
        }
        else if (financiamento.getTipoImovel() == TipoImovel.TERRENO) {
            if (financiamento.getVagasGaragem() != null || financiamento.getAndar() != null ||
                    financiamento.getElevador() != null || financiamento.getValorCondominio() != null ||
                    financiamento.getQuartos() != null) {
                throw new IllegalArgumentException("Dados inválidos para tipo de imóvel TERRENO.");
            }
        }
    }

    public void normalizarObjetoPorTipoImovel(FinanciamentoImobiliario financiamento) {
        if (financiamento.getTipoImovel() == TipoImovel.CASA) {
            financiamento.setAndar(null);
            financiamento.setElevador(null);
            financiamento.setValorCondominio(null);
        } else if (financiamento.getTipoImovel() == TipoImovel.APARTAMENTO) {
            financiamento.setAreaTerreno(null);
        }
        else if (financiamento.getTipoImovel() == TipoImovel.TERRENO) {
            financiamento.setVagasGaragem(null);
            financiamento.setAndar(null);
            financiamento.setElevador(null);
            financiamento.setValorCondominio(null);
            financiamento.setQuartos(null);
    }}
    public List<FinanciamentoImobiliario> visualizarFinanciamentos() throws SQLException, NenhumFinanciamentoEncontradoException {
        List<FinanciamentoImobiliario> financiamentos = repository.buscarFinanciamentosPorUsuario();
        if (financiamentos.isEmpty()) {
            throw new NenhumFinanciamentoEncontradoException("Nenhum financiamento encontrado para este usuário.");
            }

        return financiamentos;
    }
    }




