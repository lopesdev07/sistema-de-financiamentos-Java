package repository;

import model.Terreno;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class TerrenoRepository implements FinanciamentoRepository<Terreno> {

    private static final String arquivo = "terrenos.txt";

    @Override
    public void escreverDados(Terreno terreno) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(terreno.toFileString());
            bw.newLine();
        }
    }

    @Override
    public List<Terreno> lerDados() throws IOException {
        File file = new File(arquivo);
        List<Terreno> terrenos = new ArrayList<>();

        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                terrenos.add(Terreno.fromString(linha));
            }
        }
        return terrenos;
    }
}
