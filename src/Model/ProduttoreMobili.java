package Model;

public class ProduttoreMobili implements IProduttore {
    private String nomeProduttore;
    private String sitoWeb;
    private String nazione;
    private String citta;

    public ProduttoreMobili(){

    }

    public ProduttoreMobili(String nomeProduttore, String sitoWeb, String nazione, String citta) {
        this.nomeProduttore = nomeProduttore;
        this.sitoWeb = sitoWeb;
        this.nazione = nazione;
        this.citta = citta;
    }

    public String getNomeProduttore() {
        return nomeProduttore;
    }

    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
}
