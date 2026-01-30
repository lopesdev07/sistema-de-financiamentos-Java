package main.java.model;

import java.model.FinanciamentoStatus;

public class FinanciamentoImobiliario extends ModeloFinanciamento {

    private final double valorImovel;
    private final int vagasGaragem; // Casa e apartamento
    private final int quartos;
    private final double areaTerreno;
    private final int andar;
    private final boolean elevador;
    private final double valorCondominio;
    private final String zoneamento;



    public FinanciamentoImobiliario(
            double valorFinanciado,
            int prazoEmMeses,
            double taxaJurosAnual,
            TipoAmortizacao tipoAmortizacao,
            TipoImovel tipoImovel,
            double valorImovel,
            int vagasGaragem,
            int quartos,
            double areaTerreno,
            int andar,
            boolean elevador,
            double valorCondominio,
            String zoneamento,
            FinanciamentoStatus status

    ) {
        super(valorFinanciado, prazoEmMeses, taxaJurosAnual, tipoAmortizacao, tipoImovel, status);
        this.valorImovel = valorImovel;
        this.vagasGaragem = vagasGaragem;
        this.quartos = quartos;
        this.areaTerreno = areaTerreno;
        this.andar = andar;
        this.elevador = elevador;
        this.valorCondominio = valorCondominio;
        this.zoneamento = zoneamento;
    }

    public double getValorImovel() {
        return this.valorImovel;
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




        @Override
        public String toString() { // toString pra cada imóvel será decidio nas views
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
                Valor da Parcela: R$ %.2f
                Valor Total Pago: R$ %.2f

                --- Dados do Imóvel ---
                Quartos: %d
                Vagas: %d
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
                calcularValorParcela(),
                calcularValorTotalPago(),
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
    public double calcularValorParcela() {
        return 0;
    }

    @Override
    public double calcularValorTotalPago() {
        return 0;
    }
}




