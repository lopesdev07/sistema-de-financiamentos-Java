package main.java.model;

import java.model.FinanciamentoStatus;

/**
     * Representa o modelo inicial do contrato financeiro de um financiamento.
     * Define um contrato com os atributos e comportamentos mínimos que todo
     * financiamento deve possuir.
     */
    public abstract class ModeloFinanciamento {
        ;
        private final double valorFinanciado;
        private final int prazoEmMeses;
        private final double taxaJurosAnual; // Conceito anual, utilização em classes será mensal.
        private final TipoAmortizacao tipoAmortizacao;
        private final TipoImovel tipoImovel;
        private FinanciamentoStatus status;

        public ModeloFinanciamento(
                double valorFinanciado,
                int prazoEmMeses,
                double taxaJurosAnual,
                TipoAmortizacao tipoAmortizacao,
                TipoImovel tipoImovel,
                FinanciamentoStatus status
        ) {
            this.valorFinanciado = valorFinanciado;
            this.prazoEmMeses = prazoEmMeses;
            this.taxaJurosAnual = taxaJurosAnual;
            this.tipoAmortizacao = tipoAmortizacao;
            this.tipoImovel = tipoImovel;
            this.status = FinanciamentoStatus.SOLICITADO;
        }

        public double getValorImovel() {
            return this.valorImovel;
        }

        public double getValorFinanciado() {
            return this.valorFinanciado;
        }

        public int getPrazoEmMeses() {
            return this.prazoEmMeses;
        }

        public double getTaxaJurosAnual() {
            return this.taxaJurosAnual;
        }

        public TipoAmortizacao getTipoAmortizacao() {
            return this.tipoAmortizacao;
        }

        public TipoImovel getTipoImovel() {
            return this.tipoImovel;
        }

        public FinanciamentoStatus getFinanciamentoStatus() {
            return this.status;
        }

        public void setFinanciamentoStatus(FinanciamentoStatus status) {
            this.status = status;
        }

        /**
         * Calcula o valor da parcela mensal do financiamento.
         */
        public abstract double calcularValorParcela();

        /**
         * Calcula o valor total pago ao final do financiamento.
         */
        public abstract double calcularValorTotalPago();

}



