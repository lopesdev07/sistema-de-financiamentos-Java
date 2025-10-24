package model;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


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

    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    public double getTamanhoAreaTerreno() {
        return tamanhoAreaTerreno;
    }


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

    public String toFileString() {
        return String.format("%f;%d;%f;%f;%f",
                getValorImovel(),
                getPrazoFinanciamento(),
                getTaxaJurosAnual(),
                getTamanhoAreaConstruida(),
                getTamanhoAreaTerreno()
        );
    }



    public static Casa fromString(String linha) {
        String[] partes = linha.split(";");
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

            double valorImovel = nf.parse(partes[0].trim()).doubleValue();
            int prazoFinanciamento = Integer.parseInt(partes[1].trim());
            double taxaJurosAnual = nf.parse(partes[2].trim()).doubleValue();
            double tamanhoAreaConstruida = nf.parse(partes[3].trim()).doubleValue();
            double tamanhoAreaTerreno = nf.parse(partes[4].trim()).doubleValue();

            return new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, 80,
                    tamanhoAreaConstruida, tamanhoAreaTerreno);

        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter linha do arquivo: " + linha, e);
        }
    }





}
    


    

