package model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Terreno extends Financiamento  {
    private final String zonaLocalizada;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String zonaLocalizada) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.zonaLocalizada = zonaLocalizada;
    }

    public String getZonaLocalizada() {
        return zonaLocalizada;
    }

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


    public String toFileString() {
        return String.format("%f;%d;%f;%s",
                getValorImovel(),
                getPrazoFinanciamento(),
                getTaxaJurosAnual(),
                getZonaLocalizada()
        );
    }

    public static Terreno fromString(String linha) {
        String[] partes = linha.split(";", -1);
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

            double valorImovel = nf.parse(partes[0].trim()).doubleValue();
            int prazoFinanciamento = Integer.parseInt(partes[1].trim());
            double taxaJurosAnual = nf.parse(partes[2].trim()).doubleValue();
            String zonaLocalizada = partes.length > 3 ? partes[3].trim() : "";

            return new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, zonaLocalizada);

        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter linha do arquivo: " + linha, e);
        }
    }
}
