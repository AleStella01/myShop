package Model;

public abstract class Commento {
    protected int numeroStelle;
    protected String testo;
    private boolean risposto;
    private String testoRisposta;

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
}
