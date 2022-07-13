package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaAcquistoCliente extends ListaAcquisto{
    private Cliente cliente;
    private PuntoVendita puntoVendita;
    private Map<Prodotto, Integer> prodotti;
    private String indirizzoSpedizione;
    private List<Servizio> servizi;

    public ListaAcquistoCliente() {
        prodotti = new HashMap<>();
        servizi = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public Map<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public List<Servizio> getServizi() {
        return servizi;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

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


}
