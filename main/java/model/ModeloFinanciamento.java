package main.java.model;



/**
     * Representa o modelo inicial do contrato financeiro de um financiamento.
     * Define um contrato com os atributos e comportamentos mínimos que todo
     * financiamento deve possuir.
     */
    public abstract class ModeloFinanciamento {
        ;
        private double valorFinanciado;
        private  int prazoEmMeses;
        private double taxaJurosAnual; // Conceito anual, utilização em classes será mensal.
        private TipoAmortizacao tipoAmortizacao;
        private FinanciamentoStatus status;

        public ModeloFinanciamento(
                double valorFinanciado,
                int prazoEmMeses,
                double taxaJurosAnual,
                TipoAmortizacao tipoAmortizacao,
                FinanciamentoStatus status
        ) {
            this.valorFinanciado = valorFinanciado;
            this.prazoEmMeses = prazoEmMeses;
            this.taxaJurosAnual = taxaJurosAnual;
            this.tipoAmortizacao = tipoAmortizacao;
            this.status = status;
        }

    public ModeloFinanciamento() {

    }


    public double getValorFinanciado() {
            return this.valorFinanciado;
        }

        public void setValorFinanciado(double valorFinanciado) {
            this.valorFinanciado = valorFinanciado;
            }

        public int getPrazoEmMeses() {
            return this.prazoEmMeses;
        }

        public void setPrazoEmMeses(int prazoEmMeses) {
            this.prazoEmMeses = prazoEmMeses;
        }

        public double getTaxaJurosAnual() {
            return this.taxaJurosAnual;
        }

        public void setTaxaJurosAnual(double taxaJurosAnual) {
            this.taxaJurosAnual = taxaJurosAnual;
        }

        public TipoAmortizacao getTipoAmortizacao() {
            return this.tipoAmortizacao;
        }

        public void setTipoAmortizacao(TipoAmortizacao tipoAmortizacao) {
            this.tipoAmortizacao = tipoAmortizacao;
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



