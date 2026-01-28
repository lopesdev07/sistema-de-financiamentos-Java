/* package main.java.model;

/**
 * Classe que representa um modelo de Financiamento.
 * É usada de base por todas as classes de tipos de imóveis,
 * existindo para que seus atributos e métodos sejam herdados.
 */
/* public class Financiamento {

    private final double valorImovel;
    private final int prazoFinanciamento;
    private final double taxaJurosAnual;

public Financiamento(double ValorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = ValorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

public double getValorImovel() {
    return valorImovel;
}

public int getPrazoFinanciamento() { 
    return prazoFinanciamento;
}

public double getTaxaJurosAnual() { 
    return taxaJurosAnual;
}

/**
 * Retorna uma representação em texto do financiamento.
 * Este método serve como base para as classes filhas,
 * contendo os atributos comuns a todos os tipos de imóveis.
 * Elas normalmente o sobrescrevem para incluir informações específicas.
 *
 * @return String formatada e detalhada de acordo com seus atributos.
 */
/*@Override
public String toString() {
    return String.format("Valor do Imóvel: R$ %.2f%nPrazo do Financiamento: %d anos%nTaxa de Juros Anual: %.2f%%%n",
                          valorImovel, prazoFinanciamento, taxaJurosAnual);

}
}*/