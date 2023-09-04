package org.example;



public class Cliente {

    private String nome;
    private String cognome;

    private double saldo;
    private Negozio negozio;
    private Product prodotti;

    public Cliente(String nome, String cognome, double saldo) {
        this.nome = nome;
        this.cognome = cognome;
        this.saldo = saldo;

    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public double getSaldo() {
        return saldo;
    }

    public Negozio getNegozio() {
        return negozio;
    }

    public Product getProdotti() {
        return prodotti;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }

    public void setProdotti(Product prodotti) {
        this.prodotti = prodotti;
    }

    // metodo acquista dove il cliente può acquistare a seconda del saldo disponibile con relativo importo rimanente dopo l'acquisto
    public void acquistaProdotto(Negozio negozio, Product prodotto) {
        double price = prodotto.getPrice();
        if (saldo < price) {
            System.out.println("Saldo insufficiente per l'acquisto. Saldo attuale: " + saldo);
            return;
        }

        // Verifica se il prodotto esiste nel negozio
        if (!negozio.contieneProdotto(prodotto)) {
            System.out.println("Il prodotto non è disponibile nel negozio.");
            return;

        }
        saldo -= prodotti.getPrice();
        negozio.vendiProdotto(prodotto, price);
        System.out.printf("Acquisto di %s presso il negozio %s effettuato con successo. Saldo attuale: %f. %n",
                prodotto.getName(), negozio.getName(), saldo);

    }


    @Override
    public String toString() {
        return  String.format("Cliente %s %s %f", nome, cognome, saldo);
    }
}












