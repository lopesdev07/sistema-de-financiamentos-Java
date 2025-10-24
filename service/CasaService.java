package service;

import model.Casa;
import repository.CasaRepository;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CasaService implements FinanciamentoService<Casa> {
    private final CasaRepository Repository;
    private List<Casa> casas = new ArrayList<>();


    public CasaService(CasaRepository Repository) {
        this.Repository = Repository;
    }

    @Override
    public void cadastrarFinanciamento(Casa casa) {
        try {
            Repository.escreverDados(casa);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
            } else if (e instanceof EOFException) {
                System.err.println("Fim de arquivo inesperado, possivelmente corrompido: " + e.getMessage());
            } else {
                System.err.println("Erro genérico I/O ao escrever os dados: " + e.getMessage());
            }


        }
    }

    @Override
    public List<Casa> listarDados() {
        try {
            casas = Repository.lerDados();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
            } else if (e instanceof EOFException) {
                System.err.println("Fim de arquivo inesperado, possivelmente corrompido: " + e.getMessage());
            } else {
                System.err.println("Erro genérico I/O ao ler os dados: " + e.getMessage());
            }
        }
        return casas;
    }
}
