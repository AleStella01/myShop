package Model.composite;

import Model.Articolo;
import Model.Collocazione;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto {

    private Collocazione collocazione;

    private final List<IProdotto> sottoprodotti = new ArrayList<IProdotto>();

    public void add(IProdotto prodotto) {
        // if ...
        sottoprodotti.add(prodotto);
    }

    public void add(List<IProdotto> prodotti) {
        sottoprodotti.addAll(prodotti);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Float getPrezzo() {

        Float p = 0F;

        for(IProdotto prodotto : sottoprodotti)
            p+=prodotto.getPrezzo();

        p = 0F;

        Iterator<IProdotto> i = sottoprodotti.iterator();
        while(i.hasNext()) {
            IProdotto prodotto = i.next();
            p+=prodotto.getPrezzo();
        }

        return p;
    }
}
