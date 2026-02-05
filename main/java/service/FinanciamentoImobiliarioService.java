package main.java.service;

import main.java.model.*;
import main.java.repository.FinanciamentoImobiliarioRepository;

public class FinanciamentoImobiliarioService {
    FinanciamentoImobiliarioRepository repository;

    public FinanciamentoImobiliarioService(FinanciamentoImobiliarioRepository repository) {
        this.repository = repository;
    }

    private void definirTaxaJuros(FinanciamentoImobiliario fin) {
        double taxaJuros;
        if (fin.getCondicaoImovel() == null) {





            taxaJuros = 0.0; // CHECAR ISSO, IMPORTANTE!!






        } else if (fin.getCondicaoImovel() == main.java.model.CondicaoImovel.NOVO) {
            taxaJuros = 0.05; // Exemplo: 5% para imóveis novos
        } else {
            taxaJuros = 0.07; // Exemplo: 7% para imóveis usados
        }
        fin.setTaxaJurosAnual(taxaJuros);
    }

    private void calcularValorFinanciado(FinanciamentoImobiliario fin) {
        double valorFinanciado = fin.getValorEntrada() - fin.getValorImovel();
        fin.setValorFinanciado(valorFinanciado);
    }

    private void calcularParcelas(FinanciamentoImobiliario fin) {

    }

    private void definirStatusFinanciamento(FinanciamentoImobiliario fin) {
        if (fin.getValorFinanciado() > 0) {
            fin.setFinanciamentoStatus(FinanciamentoStatus.APROVADO);
        } else {
            fin.setFinanciamentoStatus(FinanciamentoStatus.RECUSADO);
        }

    }

    public void simularFinanciamento(FinanciamentoImobiliario fin) {
        // SIMULAR COM OS DADOS COLETADOS DA VIEW



    }


    public void cadastrarFinanciamento() {


    }

    public void listarFinanciamentos() {}

    public void alterarFinanciamento() {}


}
