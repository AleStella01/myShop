package Model;

public class Prodotto extends Articolo implements IProdotto{
    private CollocazioneProdotto collocazione;
    private ProduttoreMobili produttore;
    private int quantita;
    private CategoriaProdotto categoria;

    @Override
    public Float getPrezzo() {
        return this.prezzo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public ProduttoreMobili getProduttore() {
        return produttore;
    }

    @Override
    public CollocazioneProdotto getCollocazione() {
        return collocazione;
    }

    @Override
    public ICategoria getCategoria() {
        return categoria;
    }
}
