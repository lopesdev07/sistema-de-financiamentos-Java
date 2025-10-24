package model;


import java.io.Serializable;

public class Financiamento implements Serializable {
    // Atributos
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

public Financiamento(double ValorImovel, int prazoFinanciamento, double taxaJurosAnual) { // Construtor
        this.valorImovel = ValorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

public double getValorImovel() { // Getter
    return valorImovel;
}

public int getPrazoFinanciamento() { 
    return prazoFinanciamento;
}

public double getTaxaJurosAnual() { 
    return taxaJurosAnual;
}

@Override
public String toString() { // Metodo toString para exibir melhor os dados serializados
    return String.format("Valor do Imóvel: R$ %.2f%nPrazo do Financiamento: %d anos%nTaxa de Juros Anual: %.2f%%%n",
                          valorImovel, prazoFinanciamento, taxaJurosAnual);

}
}
