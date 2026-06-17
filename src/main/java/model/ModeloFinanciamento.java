package model;


import java.math.BigDecimal;

/**
     * Representa o modelo inicial do contrato financeiro de um financiamento.
     * Define um contrato com os atributos e comportamentos mínimos que todo
     * financiamento deve possuir.
     */
    public abstract class ModeloFinanciamento {
        ;
        private Integer finID;
        private BigDecimal valorFinanciado;
        private Integer prazoEmMeses;
        private BigDecimal taxaJurosAnual; // Conceito anual, utilização em classes mensal.
        private TipoAmortizacao tipoAmortizacao;
        private FinanciamentoStatus status;
        protected int userId;

        public ModeloFinanciamento(
                BigDecimal valorFinanciado,
                Integer prazoEmMeses,
                BigDecimal taxaJurosAnual,
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

    public BigDecimal getValorFinanciado() {
            return this.valorFinanciado;
        }

        public void setValorFinanciado(BigDecimal valorFinanciado) {
            this.valorFinanciado = valorFinanciado;
            }

        public Integer getPrazoEmMeses() {
            return this.prazoEmMeses;
        }

        public void setPrazoEmMeses(int prazoEmMeses) {
            this.prazoEmMeses = prazoEmMeses;
        }

        public BigDecimal getTaxaJurosAnual() {
            return this.taxaJurosAnual;
        }

        public void setTaxaJurosAnual(BigDecimal taxaJurosAnual) {
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



