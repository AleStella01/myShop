package Model;

public abstract class Commento {
    protected int id;
    protected int numeroStelle;
    protected String testo;
    protected boolean risposto;
    protected String testoRisposta;

    public Commento() {
    }

    public Commento(int numeroStelle, String testo) {
        this.numeroStelle = numeroStelle;
        this.testo = testo;
    }

    public int getNumeroStelle() {
        return numeroStelle;
    }

    public void setNumeroStelle(int numeroStelle) {
        this.numeroStelle = numeroStelle;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public boolean isRisposto() {
        return risposto;
    }

    public void setRisposto(boolean risposto) {
        this.risposto = risposto;
    }

    public String getTestoRisposta() {
        return testoRisposta;
    }

    public void setTestoRisposta(String testoRisposta) {
        this.testoRisposta = testoRisposta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract IUtente getCliente();
    public abstract void setCliente(IUtente cliente);
    public abstract ListaAcquisto getListaAcquisto();
    public abstract void setListaAcquisto(ListaAcquisto listaAcquisto);
}
