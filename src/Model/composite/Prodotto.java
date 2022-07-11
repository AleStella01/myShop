package Model.composite;

import Model.Articolo;
import Model.CategoriaProdotto;
import Model.Collocazione;
import Model.Produttore;

public class Prodotto extends Articolo implements IProdotto {

    private Collocazione collocazione;
    private Produttore produttore;
    private int quantita;
    private CategoriaProdotto categoria;



    /*viene avvalorata in maniera diversa a seconda se prendiamo l'immagine dal db
          (campo BLOB), oppure da un path locale, oppure dal Cloud (es. Amazon S3)
         */
    private byte[] immagine;

    @Override
    public Float getPrezzo() {
        return this.prezzo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

}
