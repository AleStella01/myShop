package Model;

import java.net.URL;

public class Produttore {

    private String nome;
    private URL web;
    private String citta;
    private String nazione;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public URL getWeb() {
        return web;
    }

    public void setWeb(URL web) {
        this.web = web;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
}
