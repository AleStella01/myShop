package Model;

import Business.AbstractFactory.IArticolo;

import java.util.List;

public class Articolo implements IArticolo {

    protected Float prezzo;
    private List<Commento> commenti;
    protected String nome;
    protected String categoria;
    protected int id;

/*    public Float getPrezzo() {
        return prezzo;
    }*/

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
