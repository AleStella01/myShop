package Model;

public class Prodotto extends Articolo implements IProdotto{
    private CollocazioneProdotto collocazione;
    private ProduttoreMobili produttore;
    private int quantita;


    @Override
    public Float getPrezzo() {
        return this.prezzo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }
}
