package Model;

public class ProduttoreMobili implements IProduttore {
    private int id;
    private String nomeProduttore;
    private String sitoWeb;
    private String nazione;
    private String citta;

    public ProduttoreMobili(){

    }

    public ProduttoreMobili(int id, String nomeProduttore, String sitoWeb, String nazione, String citta) {
        this.id = id;
        this.nomeProduttore = nomeProduttore;
        this.sitoWeb = sitoWeb;
        this.nazione = nazione;
        this.citta = citta;
    }

    @Override
    public String getNomeProduttore() {
        return nomeProduttore;
    }

    @Override
    public void setNomeProduttore(String nomeProduttore) {
        this.nomeProduttore = nomeProduttore;
    }

    @Override
    public String getSitoWeb() {
        return sitoWeb;
    }

    @Override
    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    @Override
    public String getNazione() {
        return nazione;
    }

    @Override
    public void setNazione(String nazione) {
        this.nazione = nazione;
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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}

