package org.project;

public class Negozio {
    private String nome;
    private double venditeTotali;
    private String proprietario;
    private String categoriaPiuVenduta;
    private static final int minCapacity = 0;
    private static final int maxCapacity = 2000;
    private static int numberOfShops;

    private static void keepTrackOfShopNumber() {numberOfShops ++;}

    public Negozio(String nome, double venditeTotali, String categoriaPiuVenduta){
        this.nome = nome;
        this.venditeTotali = venditeTotali;
        this.categoriaPiuVenduta = categoriaPiuVenduta;
    }

    public String getNome(){
        return nome;
    }

    public String getCategoriaPiuVenduta(){
        return categoriaPiuVenduta;
    }

    public double getVenditeTotali() {
        return venditeTotali;
    }
}
