package repository;
import model.Casa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class CasaRepository implements FinanciamentoRepository<Casa> {

    private static final String arquivo = "casas.txt";

    @Override
    public void escreverDados(Casa casa) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {

            bw.write(casa.toFileString());
            bw.newLine();
        }
    }

    @Override
    public List<Casa> lerDados() throws IOException {
        File file = new File(arquivo);
        List<Casa> casas = new ArrayList<>();

        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>(); // Retorna lista vazia se o arquivo não existir

        }
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo)))
        {
            String linha;
            while ((linha = br.readLine()) != null) {
                casas.add(Casa.fromString(linha));
            }

        }
        return casas;
    }

}

