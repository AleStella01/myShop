package Model;

import Business.FactoryMethod.NotificationFactory;

import java.util.List;

public class Cliente extends Utente {

    private List<ListaAcquisto> listeAcquisto;
    private List<Prenotazione> prenotazioni;
    private PuntoVendita puntoVenditaDiRegistrazione;
    private NotificationFactory.TipoNotifica canalePreferito;

    public List<ListaAcquisto> getListeAcquisto() {
        return listeAcquisto;
    }

    public void setListeAcquisto(List<ListaAcquisto> listeAcquisto) {
        this.listeAcquisto = listeAcquisto;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public PuntoVendita getPuntoVenditaDiRegistrazione() {
        return puntoVenditaDiRegistrazione;
    }

    public void setPuntoVenditaDiRegistrazione(PuntoVendita puntoVenditaDiRegistrazione) {
        this.puntoVenditaDiRegistrazione = puntoVenditaDiRegistrazione;
    }

    public NotificationFactory.TipoNotifica getCanalePreferito() {
        return canalePreferito;
    }

    public void setCanalePreferito(NotificationFactory.TipoNotifica canalePreferito) {
        this.canalePreferito = canalePreferito;
    }
}
