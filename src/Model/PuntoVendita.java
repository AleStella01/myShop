package Model;

import java.util.Map;
import java.util.Set;

public class PuntoVendita implements IPuntoVendita {
    private int id;
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

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCitta() {
        return citta;
    }

    @Override
    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public IUtente getManager() {
        return manager;
    }

    @Override
    public void setManager(IUtente manager) {
        this.manager = manager;
    }

    @Override
    public Map<IProdotto, Integer> getProdottiVenduti() {
        return prodottiVenduti;
    }

    @Override
    public void setProdottiVenduti(Map<IProdotto, Integer> prodottiVenduti) {
        this.prodottiVenduti = prodottiVenduti;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PuntoVendita{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", manager=" + manager +
                ", prodottiVenduti=" + prodottiVenduti +
                '}';
    }
}
