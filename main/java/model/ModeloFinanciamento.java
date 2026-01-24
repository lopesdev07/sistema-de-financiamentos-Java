package main.java.model;

/**
     * Representa o modelo inicial do contrato financeiro de um financiamento.
     * Define um contrato com os atributos e comportamentos mínimos que todo
     * financiamento deve possuir.
     */
    public abstract class ModeloFinanciamento {

        protected double valorFinanciado;
        protected int prazoEmMeses;
        protected double taxaJurosAnual; // Conceito anual, utilização em classes será mensal.
        protected TipoAmortizacao tipoAmortizacao;
        protected Imovel imovel;

        public ModeloFinanciamento(
                double valorFinanciado,
                int prazoEmMeses,
                double taxaJurosAnual,
                TipoAmortizacao tipoAmortizacao,
                Imovel imovel
        ) {
            this.valorFinanciado = valorFinanciado;
            this.prazoEmMeses = prazoEmMeses;
            this.taxaJurosAnual = taxaJurosAnual;
            this.tipoAmortizacao = tipoAmortizacao;
            this.imovel = imovel;
        }

        public double getValorFinanciado() {
            return valorFinanciado;
        }

        public int getPrazoEmMeses() {
            return prazoEmMeses;
        }

        public double getTaxaJurosAnual() {
            return taxaJurosAnual;
        }

        public TipoAmortizacao getTipoAmortizacao() {
            return tipoAmortizacao;
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



