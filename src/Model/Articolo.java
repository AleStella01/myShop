package Model;

import java.util.List;

public class Articolo implements IArticolo{
    protected Float prezzo;
    private List<CommentoProdotto> commenti;
    protected String nome;
    protected int id;
    protected String descrizione;


    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Float getPrezzo() {
        return prezzo;
    }

    @Override
    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public ICategoria getCategoria() {
        return null;
    }

    @Override
    public void setCategoria(ICategoria categoria) {

    }

    @Override
    public String toString() {
        return "Articolo{" +
                "prezzo=" + prezzo +
                ", commenti=" + commenti +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
