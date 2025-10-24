package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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


    public String toFileString() {
        return String.format("%f;%d;%f;%d;%d",
                getValorImovel(),
                getPrazoFinanciamento(),
                getTaxaJurosAnual(),
                getNumeroAndar(),
                getVagasGaragem()
        );
    }

    public static Apartamento fromString(String linha) {
        String[] partes = linha.split(";");
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

            double valorImovel = nf.parse(partes[0].trim()).doubleValue();
            int prazoFinanciamento = Integer.parseInt(partes[1].trim());
            double taxaJurosAnual = nf.parse(partes[2].trim()).doubleValue();
            int numeroAndar = Integer.parseInt(partes[3].trim());
            int vagasGaragem = Integer.parseInt(partes[4].trim());

            return new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, vagasGaragem, numeroAndar);

        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter linha do arquivo: " + linha, e);
        }
    }

}
