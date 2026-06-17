package model;

import java.math.BigDecimal;

public class FinanciamentoImobiliario extends ModeloFinanciamento {

    private BigDecimal valorImovel;
    private BigDecimal valorEntrada;
    private TipoImovel tipoImovel;
    private Integer vagasGaragem; // Casa e apartamento
    private Integer quartos;
    private BigDecimal areaTerreno; // agora wrapper
    private Integer andar;
    private Boolean elevador;
    private BigDecimal valorCondominio;
    private String zoneamento;
    private CondicaoImovel condicaoImovel;


    public FinanciamentoImobiliario(
            BigDecimal valorFinanciado,
            Integer prazoEmMeses,
            BigDecimal taxaJurosAnual,
            TipoAmortizacao tipoAmortizacao,
            TipoImovel tipoImovel,
            FinanciamentoStatus status,
            Integer quartos,
            Integer vagasGaragem,
            BigDecimal areaTerreno,
            Integer andar,
            Boolean elevador,
            BigDecimal valorCondominio,
            String zoneamento,
            int userId
    ) {
        super(valorFinanciado, prazoEmMeses, taxaJurosAnual, tipoAmortizacao, status, userId);
        this.tipoImovel = tipoImovel;
        this.quartos = quartos;
        this.vagasGaragem = vagasGaragem;
        this.areaTerreno = areaTerreno;
        this.andar = andar;
        this.elevador = elevador;
        this.valorCondominio = valorCondominio;
        this.zoneamento = zoneamento;
    }

    public FinanciamentoImobiliario(BigDecimal valorFinanciado, int prazoMeses, BigDecimal taxaJurosAnual, TipoAmortizacao tipoAmortizacao, TipoImovel tipoImovel, FinanciamentoStatus status, Integer userId) {
        super(valorFinanciado, prazoMeses, taxaJurosAnual, tipoAmortizacao, status, userId);
    }


    public BigDecimal getValorImovel() {
        return this.valorImovel;
    }

    public BigDecimal getValorEntrada() {
        return this.valorEntrada;
    }

    public TipoImovel getTipoImovel() {
        return this.tipoImovel;
    }

    public Integer getVagasGaragem() {
        return this.vagasGaragem;
    }

    public Integer getQuartos() {
        return this.quartos;
    }

    public BigDecimal getAreaTerreno() {
        return this.areaTerreno;
    }

    public Integer getAndar() {
        return this.andar;
    }

    public Boolean getElevador() {
        return this.elevador;
    }

    public BigDecimal getValorCondominio() {
        return this.valorCondominio;
    }

    public String getZoneamento() {
        return this.zoneamento;
    }

    public CondicaoImovel getCondicaoImovel() {
        return this.condicaoImovel;
    }

    public void setValorImovel(BigDecimal valorImovel) {
        this.valorImovel = valorImovel;
    }

    public void setValorEntrada(BigDecimal valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public void setCondicaoImovel(CondicaoImovel condicaoImovel) {
        this.condicaoImovel = condicaoImovel;
    }

    public void setVagasGaragem(Integer vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public void setQuartos(Integer quartos) {
        this.quartos = quartos;
    }

    public void setAreaTerreno(BigDecimal areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public void setElevador(Boolean elevador) {
        this.elevador = elevador;
    }

    public void setValorCondominio(BigDecimal valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    public void setZoneamento(String zoneamento) {
        this.zoneamento = zoneamento;
    }



    @Override
    public String toString() {
        return String.format(
                "===== FINANCIAMENTO IMOBILIÁRIO =====%n" +
                        "ID do financiamento: %d%n" +
                        "Tipo de Imóvel: %s%n" +
                        "Tipo de Amortização: %s%n%n" +
                        "--- Dados do Financiamento ---%n" +
                        "Status do financiamento: %s%n" +
                        "Valor Financiado: R$ %.2f%n" +
                        "Prazo: %d meses%n" +
                        "Taxa de Juros Anual: %.2f%%%n%n" +
                        "--- Dados do Imóvel ---%n" +
                        "Quartos: %d%n" +
                        "Vagas de Garagem: %d%n" +
                        "Área do Terreno: %.2f m²%n" +
                        "Andar: %d%n" +
                        "Elevador: %s%n" +
                        "Valor do Condomínio: R$ %.2f%n" +
                        "Zoneamento: %s%n" +
                        "===================================%n",
                getFinID(),
                getTipoImovel(),
                getTipoAmortizacao(),
                getFinanciamentoStatus(),
                (getValorFinanciado() != null ? getValorFinanciado().doubleValue() : 0.0),
                getPrazoEmMeses(),
                (getTaxaJurosAnual() != null ? getTaxaJurosAnual().doubleValue() : 0.0),
                (getQuartos() != null ? getQuartos() : 0),
                (getVagasGaragem() != null ? getVagasGaragem() : 0),
                (getAreaTerreno() != null ? getAreaTerreno().doubleValue() : 0.0),
                (getAndar() != null ? getAndar() : 0),
                (getElevador() != null && getElevador()) ? "Sim" : "Não",
                (getValorCondominio() != null ? getValorCondominio().doubleValue() : 0.0),
                getZoneamento()
        );
    }

    @Override
    public Integer getPrazoEmMeses() {
        return super.getPrazoEmMeses();
    }
}
