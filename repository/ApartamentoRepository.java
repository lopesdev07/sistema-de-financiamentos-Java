package repository;

import model.Apartamento;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class ApartamentoRepository implements FinanciamentoRepository<Apartamento> {

    private static final String arquivo = "apartamentos.txt";

    @Override
    public void escreverDados(Apartamento apartamento) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(apartamento.toFileString());
            bw.newLine();
        }
    }

    @Override
    public List<Apartamento> lerDados() throws IOException {
        File file = new File(arquivo);
        List<Apartamento> apartamentos = new ArrayList<>();

        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                apartamentos.add(Apartamento.fromString(linha));
            }
        }
        return apartamentos;
    }
}
