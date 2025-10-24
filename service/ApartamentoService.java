package service;

import model.Apartamento;
import repository.ApartamentoRepository;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoService implements FinanciamentoService<Apartamento> {
    private final ApartamentoRepository Repository;
    private List<Apartamento> apartamentos = new ArrayList<>();

    public ApartamentoService(ApartamentoRepository Repository) {
        this.Repository = Repository;
    }

    @Override
    public void cadastrarFinanciamento(Apartamento apartamento) {
        try {
            Repository.escreverDados(apartamento);
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
    public List<Apartamento> listarDados() {
        try {
            apartamentos = Repository.lerDados();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("Arquivo não encontrado: " + e.getMessage());
            } else if (e instanceof EOFException) {
                System.err.println("Fim de arquivo inesperado, possivelmente corrompido: " + e.getMessage());
            } else {
                System.err.println("Erro genérico I/O ao ler os dados: " + e.getMessage());
            }
        }
        return apartamentos;
    }
}
