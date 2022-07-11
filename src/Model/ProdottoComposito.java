package Model;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto{
    private final List<IProdotto> sottoprodotti = new ArrayList<>();
    CollocazioneProdotto collocazione = new CollocazioneProdotto();

    public void add(IProdotto prodotto){
        sottoprodotti.add(prodotto);
    }

    public void add(List<IProdotto> prodotti){
        sottoprodotti.addAll(sottoprodotti);
    }

    @Override
    public Float getPrezzo() {
        return null;
    }

    @Override
    public String getNome() {
        return this.nome;
    }
}
