package main.java.model;

public class FinanciamentoImobiliario extends ModeloFinanciamento {

    private double valorImovel;
    private double valorEntrada;
    private TipoImovel tipoImovel;
    private int vagasGaragem; // Casa e apartamento
    private int quartos;
    private double areaTerreno;
    private int andar;
    private boolean elevador;
    private double valorCondominio;
    private String zoneamento;
    private CondicaoImovel condicaoImovel;

    public FinanciamentoImobiliario(double valorFinanciado, int prazoEmMeses, double taxaJurosAnual, TipoAmortizacao tipoAmortizacao, FinanciamentoStatus status) {
        super(valorFinanciado, prazoEmMeses, taxaJurosAnual, tipoAmortizacao, status);
    }

    public FinanciamentoImobiliario() {
        super();
    }

    public double getValorImovel() {
        return this.valorImovel;
    }

    public double getValorEntrada() {
        return this.valorEntrada;
    }

    public TipoImovel getTipoImovel() {
        return this.tipoImovel;
    }

    public int getVagasGaragem() {
        return this.vagasGaragem;
    }

    public int getQuartos() {
        return this.quartos;
    }

    public double getAreaTerreno() {
        return this.areaTerreno;
    }

    public int getAndar() {
        return this.andar;
    }

    public boolean getElevador() {
        return this.elevador;
    }

    public double getValorCondominio() {
        return this.valorCondominio;
    }

    public String getZoneamento() {
        return this.zoneamento;
    }

    public CondicaoImovel getCondicaoImovel() {
        return this.condicaoImovel;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public void setValorEntrada(double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public void setCondicaoImovel(CondicaoImovel condicaoImovel) {
        this.condicaoImovel = condicaoImovel;
    }

    public void setVagasGaragem(int vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public void setQuartos(int quartos) {
        this.quartos = quartos;
    }

    public void setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public void setElevador(boolean elevador) {
        this.elevador = elevador;
    }

    public void setValorCondominio(double valorCondominio) {
        this.valorCondominio = valorCondominio;
    }

    public void setZoneamento(String zoneamento) {
        this.zoneamento = zoneamento;
    }

    @Override
    public String toString() {
        return String.format(
                """
                ===== FINANCIAMENTO IMOBILIÁRIO =====
                Tipo de Imóvel: %s
                Tipo de Amortização: %s
    
                --- Dados do Financiamento ---
                Status do financiamento: %s
                Valor Financiado: R$ %.2f
                Prazo: %d meses
                Taxa de Juros Anual: %.2f%%
    
                --- Dados do Imóvel ---
                Quartos: %d
                Vagas de Garagem: %d
                Área do Terreno: %.2f m²
                Andar: %d
                Elevador: %s
                Valor do Condomínio: R$ %.2f
                Zoneamento: %s
                ===================================
                """,
                getTipoImovel(),
                getTipoAmortizacao(),
                getFinanciamentoStatus(),
                getValorFinanciado(),
                getPrazoEmMeses(),
                getTaxaJurosAnual(),
                getQuartos(),
                getVagasGaragem(),
                getAreaTerreno(),
                getAndar(),
                getElevador() ? "Sim" : "Não",
                getValorCondominio(),
                getZoneamento()
        );
    }

    @Override
    public int getPrazoEmMeses() {
        return super.getPrazoEmMeses();
    }
}
