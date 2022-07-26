package Model;

import java.util.ArrayList;
import java.util.List;

public class ProdottoComposito extends Articolo implements IProdotto{
    private final List<IProdotto> sottoprodotti = new ArrayList<>();
    CollocazioneProdotto collocazione = new CollocazioneProdotto();
    ICategoria categoria;
    IProduttore produttore;

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

    @Override
    public IProduttore getProduttore() {
        return produttore;
    }

    @Override
    public CollocazioneProdotto getCollocazione() {
        return null;
    }

    @Override
    public void setCollocazione(Collocazione c) {

    }

    @Override
    public ICategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ICategoria categoria) {
        this.categoria = categoria;
    }

    public void setProduttore(IProduttore produttore) {
        this.produttore = produttore;
    }

    public List<IProdotto> getSottoprodotti(){
        return sottoprodotti;
    }

    @Override
    public String toString() {
        return "ProdottoComposito{" +
                "prezzo=" + prezzo +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", sottoprodotti=" + sottoprodotti +
                ", collocazione=" + collocazione +
                ", categoria=" + categoria +
                ", produttore=" + produttore +
                '}';
    }
}
