package Model;

import java.util.List;
import java.util.Map;

public abstract class ListaAcquisto {
    protected int id;
    protected IUtente cliente;
    protected IPuntoVendita puntoVendita;
    protected Map<IProdotto, Integer> prodotti;

    public abstract Map<IProdotto, Integer> getProdotti();
    public abstract void addProdotto(IProdotto prodotto, int quantita);
    public abstract void addServizio(IArticolo servizio);
    public abstract void addSpedizione(String indirizzo);
    public abstract String getIndirizzoSpedizione();
    public abstract List<IArticolo> getServizi();

    public IUtente getCliente() {
        return cliente;
    }

    public void setCliente(IUtente cliente) {
        this.cliente = cliente;
    }

    public IPuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(IPuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
