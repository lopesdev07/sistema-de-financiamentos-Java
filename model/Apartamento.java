package model;

/**
 * Classe que representa um modelo de Apartamento,
 * que herda os atributos e métodos da classe Financiamento.
 */
public class Apartamento extends Financiamento {
    private final int vagasGaragem;
    private final int numeroAndar;
    
    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public int getNumeroAndar() {
        return numeroAndar;
    }

    /**
     * Retorna uma representação em texto do Apartamento.
     * Combinando os atributos herdados da classe Financiamento
     * com os atributos específicos da classe Apartamento.
     *
     * @return String formatada e detalhada de acordo com seus atributos.
     */
    @Override
    public String toString() {
        return String.format(
            "Apartamento | Valor: R$ %.2f | Prazo: %d anos | Juros: %.2f%% ao ano | Andar: %d | Vagas: %d",
            getValorImovel(),
            getPrazoFinanciamento(),
            getTaxaJurosAnual() * 100,
            getNumeroAndar(),
            getVagasGaragem()
        );
    }

}
