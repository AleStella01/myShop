package Model;

public class Prodotto extends Articolo implements IProdotto{
    private Collocazione collocazione;
    private IProduttore produttore;
    private int quantita;
    private ICategoria categoria;

    @Override
    public Float getPrezzo() {
        return this.prezzo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public IProduttore getProduttore() {
        return produttore;
    }

    @Override
    public Collocazione getCollocazione() {
        return collocazione;
    }

    @Override
    public ICategoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "prezzo=" + prezzo +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", collocazione=" + collocazione +
                ", produttore=" + produttore +
                ", quantita=" + quantita +
                ", categoria=" + categoria +
                '}';
    }

    public void setCollocazione(Collocazione collocazione) {
        this.collocazione = collocazione;
    }

    public void setProduttore(IProduttore produttore) {
        this.produttore = produttore;
    }

    public void setCategoria(ICategoria categoria) {
        this.categoria = categoria;
    }
}
