package model;

/**
 * Classe que representa um modelo de Casa,
 * que herda os atributos e métodos da classe Financiamento.
 */
public class Casa extends Financiamento  {
    private int valorFixo = 80;
    private final double tamanhoAreaConstruida;
    private final double tamanhoAreaTerreno;


    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int valorFixo, double tamanhoAreaConstruida, double tamanhoAreaTerreno) { //Construtor
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.valorFixo = valorFixo;
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoAreaTerreno = tamanhoAreaTerreno;
    }

    public int getValorFixo() {
        return valorFixo;
    }
    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    public double getTamanhoAreaTerreno() {
        return tamanhoAreaTerreno;
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
        "Casa | Valor: R$ %.2f | Prazo: %d anos | Juros: %.2f%% ao ano | Área construída: %.2f m² | Área do terreno: %.2f m²",
        getValorImovel(),
        getPrazoFinanciamento(),
        getTaxaJurosAnual() * 100,
        getTamanhoAreaConstruida(),
        getTamanhoAreaTerreno()
    );
}


}
    


    

