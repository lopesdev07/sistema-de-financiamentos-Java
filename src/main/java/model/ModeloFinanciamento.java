package model;



/**
     * Representa o modelo inicial do contrato financeiro de um financiamento.
     * Define um contrato com os atributos e comportamentos mínimos que todo
     * financiamento deve possuir.
     */
    public abstract class ModeloFinanciamento {
        ;
        private Integer finID;
        private double valorFinanciado;
        private  int prazoEmMeses;
        private double taxaJurosAnual; // Conceito anual, utilização em classes mensal.
        private TipoAmortizacao tipoAmortizacao;
        private FinanciamentoStatus status;
        protected int userId;

        public ModeloFinanciamento(
                double valorFinanciado,
                int prazoEmMeses,
                double taxaJurosAnual,
                TipoAmortizacao tipoAmortizacao,
                FinanciamentoStatus status,
                int userId
        ) {
            this.valorFinanciado = valorFinanciado;
            this.prazoEmMeses = prazoEmMeses;
            this.taxaJurosAnual = taxaJurosAnual;
            this.tipoAmortizacao = tipoAmortizacao;
            this.status = status;
            this.userId = userId;
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

        public int getUserId() {
            return this.userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Integer getFinID() {
            return this.finID;
        }

        public void setFinID(Integer finID) {
            this.finID = finID;
        }
}



