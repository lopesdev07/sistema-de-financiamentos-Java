package main.java.model;

/**
 * Classe que representa um modelo de Casa,
 * que herda os atributos e métodos da classe Financiamento.
 */
public class Casa extends Imovel  {
    private final int vagasGaragem;
    private final int quartos;
    private final double areaTerreno;


    public Casa(double valorImovel, TipoImovel tipoImovel, double areaM2, CondicaoImovel condicaoImovel, int vagasGaragem, int quartos, double areaTerreno ) { //Construtor
        super(valorImovel, tipoImovel, areaM2, condicaoImovel);
        this.vagasGaragem = vagasGaragem;
        this.quartos = quartos;
        this.areaTerreno = areaTerreno;
    }

    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public int getQuartos() {
        return quartos;
    }

    public double getAreaTerreno() {
        return areaTerreno;
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
        "Casa | Valor: R$ %.2f | Tipo: %s | Área: %.2f m² | Condição: %s | Vagas Garagem: %d | Quartos: %d | Área Terreno: %.2f m²",
        getValorImovel(), getTipoImovel(), getAreaM2(), getCondicaoImovel(), getVagasGaragem(), getQuartos(), getAreaTerreno()

    );
}


}
    


    

