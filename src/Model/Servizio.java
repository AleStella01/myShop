package Model;

public class Servizio extends Articolo{
    @Override
    public String toString() {
        return "Servizio{" +
                "prezzo=" + prezzo +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
