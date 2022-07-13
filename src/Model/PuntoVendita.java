package Model;

import java.util.Map;
import java.util.Set;

public class PuntoVendita implements IPuntoVendita {
    private String nome;
    private String citta;
    private String indirizzo;
    private IUtente manager;
    private Map<IProdotto, Integer> prodottiVenduti;

    public PuntoVendita() {
        this.nome = null;
        this.citta = null;
        this.indirizzo = null;
        this.manager = null;
        this.prodottiVenduti = null;
    }

    public PuntoVendita(String nome, String citta, String indirizzo, IUtente manager, Map prodottiVenduti) {
        this.nome = nome;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.manager = manager;
        this.prodottiVenduti = prodottiVenduti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public IUtente getManager() {
        return manager;
    }

    public void setManager(IUtente manager) {
        this.manager = manager;
    }

    public Map<IProdotto, Integer> getProdottiVenduti() {
        return prodottiVenduti;
    }

    public void setProdottiVenduti(Map<IProdotto, Integer> prodottiVenduti) {
        this.prodottiVenduti = prodottiVenduti;
    }
}
