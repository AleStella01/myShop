package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaAcquistoCliente extends ListaAcquisto{
    private String indirizzoSpedizione;
    private List<IArticolo> servizi;

    public ListaAcquistoCliente() {
        prodotti = new HashMap<>();
        servizi = new ArrayList<>();
    }

    public Map<IProdotto, Integer> getProdotti() {
        return prodotti;
    }

    @Override
    public void addProdotto(IProdotto prodotto, int quantita) {
        prodotti.put(prodotto, quantita);
    }

    @Override
    public void addServizio(IArticolo servizio) {
        servizi.add(servizio);
    }

    @Override
    public void addSpedizione(String indirizzo) {
        indirizzoSpedizione = indirizzo;
    }

    @Override
    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    @Override
    public List<IArticolo> getServizi() {
        return servizi;
    }



    @Override
    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public void aggiungiServizio(Servizio servizio){
        this.servizi.add(servizio);
    }

    public void aggiungiServizio(List<Servizio> servizi){
        this.servizi.addAll(servizi);
    }

    public void aggiungiProdotto(Prodotto prodotto, int quantita){
        prodotti.put(prodotto, quantita);
    }

    @Override
    public String toString() {
        return "ListaAcquistoCliente{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", puntoVendita=" + puntoVendita +
                ", prodotti=" + prodotti +
                ", indirizzoSpedizione='" + indirizzoSpedizione + '\'' +
                ", servizi=" + servizi +
                '}';
    }
}
