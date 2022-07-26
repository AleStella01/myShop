package Model;

public class Servizio extends Articolo{
    ICategoria categoria;

    public Servizio() {
        categoria = null;
    }

    @Override
    public String toString() {
        return "Servizio{" +
                "prezzo=" + prezzo +
                ", nome='" + nome + '\'' +
                ", id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    @Override
    public void setCategoria(ICategoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public ICategoria getCategoria() {
        return categoria;
    }
}
