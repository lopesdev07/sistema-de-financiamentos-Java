package model;

/**
 * Classe que representa um modelo de Terreno,
 * que herda os atributos e métodos da classe Financiamento.
 */

public class Terreno extends Financiamento  {
    private final String zonaLocalizada;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String zonaLocalizada) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.zonaLocalizada = zonaLocalizada;
    }

    public String getZonaLocalizada() {
        return zonaLocalizada;
    }

    /**
     * Retorna uma representação em texto da casa.
     * Combinando os atributos herdados da classe Financiamento
     * com os atributos específicos da classe Casa.
     *
     * @return String formatada e detalhada de acordo com seus atributos.
     */
    @Override
    public String toString() {
        return String.format(
            "Terreno | Valor: R$ %.2f | Prazo: %d anos | Juros: %.2f%% ao ano | Zona: %s",
            getValorImovel(),
            getPrazoFinanciamento(),
            getTaxaJurosAnual() * 100,
            getZonaLocalizada()
        );
    }


}
