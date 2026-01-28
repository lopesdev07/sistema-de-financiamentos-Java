package main.java.model;

public abstract class Imovel {
    private double valorImovel;
    private TipoImovel tipoImovel;
    private double areaM2;
    private CondicaoImovel condicaoImovel;

    protected Imovel(double valorImovel, TipoImovel tipoImovel, double areaM2, CondicaoImovel condicaoImovel) {
        this.valorImovel = valorImovel;
        this.tipoImovel = tipoImovel;
        this.areaM2 = areaM2;
        this.condicaoImovel = condicaoImovel;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public CondicaoImovel getCondicaoImovel() {
        return condicaoImovel;
    }

    protected void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    protected void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    protected void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    protected void setCondicaoImovel(CondicaoImovel condicaoImovel) {
        this.condicaoImovel = condicaoImovel;
    }
}
