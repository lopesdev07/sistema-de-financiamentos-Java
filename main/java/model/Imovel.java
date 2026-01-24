package main.java.model;

public abstract class Imovel {
    protected double valorImovel;
    protected TipoImovel tipoImovel;
    protected double areaM2;
    protected double condicaoImovel;

    protected Imovel(double valorImovel, TipoImovel tipoImovel, double areaM2, double condicaoImovel) {
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

    public double getCondicaoImovel() {
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

    protected void setCondicaoImovel(double condicaoImovel) {
        this.condicaoImovel = condicaoImovel;
    }
}
