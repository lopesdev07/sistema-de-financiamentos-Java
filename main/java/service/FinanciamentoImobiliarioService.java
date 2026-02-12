package main.java.service;

import exceptions.EntradaMaiorQueValorDoImovelException;
import main.java.model.*;
import main.java.repository.FinanciamentoImobiliarioRepository;


public class FinanciamentoImobiliarioService {
    FinanciamentoImobiliarioRepository repository;

    public FinanciamentoImobiliarioService(FinanciamentoImobiliarioRepository repository) {
        this.repository = repository;
    }

    private void validarEntrada(double valorEntrada, double valorImovel) throws EntradaMaiorQueValorDoImovelException {
        if (valorEntrada > valorImovel) {
            throw new EntradaMaiorQueValorDoImovelException();
        }
    }

    private void validarDados (double valorEntrada, double valorImovel, int prazoEmMeses, CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel) {
        if (valorEntrada < 0 || valorImovel <= 0 || prazoEmMeses <= 0) {
            throw new IllegalArgumentException("Valores de entrada, imóvel e prazo devem ser positivos.");
        }
        if (condicaoImovel == null) {
            throw new IllegalArgumentException("A condição do imóvel deve ser especificada.");
        }
        if (tipoAmortizacao == null) {
            throw new IllegalArgumentException("O tipo de amortização deve ser especificado.");
        }
        if (tipoImovel == null) {
            throw new IllegalArgumentException("O tipo de imóvel deve ser especificado.");
        }

    }
    public void calcularParcelas(FinanciamentoImobiliario financiamento) { // pedir pro usuario na view qual ele uqer

        double saldoDevedor = financiamento.getValorFinanciado();
        int parcelas = financiamento.getPrazoEmMeses();
        double taxaJurosMensal = (financiamento.getTaxaJurosAnual() / 100) / 12;
        String tipo = financiamento.getTipoAmortizacao().toString();

        if (financiamento.getTipoAmortizacao() == TipoAmortizacao.SAC) {

            double amortizacao = saldoDevedor / parcelas;

            for (int i = 0; i < parcelas; i++) {

                double juros = saldoDevedor * taxaJurosMensal;
                double parcela = amortizacao + juros;

                saldoDevedor -= amortizacao;

                System.out.println("Parcela " + (i+1) + ": " + parcela);
            }

        } else if (financiamento.getTipoAmortizacao() == TipoAmortizacao.PRICE) {

            double parcela = saldoDevedor *
                    (taxaJurosMensal /
                            (1 - Math.pow(1 + taxaJurosMensal, -parcelas)));

            for (int i = 0; i < parcelas; i++) {

                double juros = saldoDevedor * taxaJurosMensal;
                double amortizacao = parcela - juros;

                saldoDevedor -= amortizacao;

                System.out.println("Parcela " + (i+1) + ": " + parcela);
            }

        } else {
            throw new IllegalArgumentException("Tipo de amortização inválido.");
        }
    }

    public FinanciamentoImobiliario simularFinanciamento(double valorImovel, double valorEntrada, int prazoEmMeses, CondicaoImovel condicaoImovel, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel) throws EntradaMaiorQueValorDoImovelException {

        validarDados(valorEntrada, valorImovel, prazoEmMeses, condicaoImovel, tipoAmortizacao, tipoImovel);
        validarEntrada(valorEntrada, valorImovel);

        double valorFinanciado = valorImovel - valorEntrada;


        double taxaJuros;
        if (condicaoImovel == CondicaoImovel.USADO) {
            taxaJuros = 7.0;
        } else {
            taxaJuros = 5.0;
        }

        FinanciamentoStatus status = valorFinanciado > 0 ? FinanciamentoStatus.APROVADO : FinanciamentoStatus.RECUSADO;

        FinanciamentoImobiliario financiamento = new FinanciamentoImobiliario(valorFinanciado, prazoEmMeses, taxaJuros, tipoAmortizacao, status);

        financiamento.setValorImovel(valorImovel);
        financiamento.setValorEntrada(valorEntrada);
        financiamento.setCondicaoImovel(condicaoImovel);
        financiamento.setTipoImovel(tipoImovel);

        return financiamento;
    }



    public void cadastrarFinanciamento() {

        // adicionar no bd pelo repo


    }

    public void listarFinanciamentos() {
        // provavel que chamar o repo, transformar em obj e retornar pra view

    }

    public void alterarFinanciamento() {
        //

    }


}
