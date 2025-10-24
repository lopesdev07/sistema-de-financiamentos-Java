package service;

import model.Terreno;
import repository.TerrenoRepository;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TerrenoService implements FinanciamentoService<Terreno> {
    private final TerrenoRepository Repository;
    private List<Terreno> terrenos = new ArrayList<>();

    public TerrenoService(TerrenoRepository Repository) {
        this.Repository = Repository;
    }

    @Override
    public void cadastrarFinanciamento(Terreno terreno) {
        try {
            Repository.escreverDados(terreno);
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
    public List<Terreno> listarDados() {
        try {
            terrenos = Repository.lerDados();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
            } else if (e instanceof EOFException) {
                System.err.println("Fim de arquivo inesperado, possivelmente corrompido: " + e.getMessage());
            } else {
                System.err.println("Erro genérico I/O ao ler os dados: " + e.getMessage());
            }
        }
        return terrenos;
    }
}
