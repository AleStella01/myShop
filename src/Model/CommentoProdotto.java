package Model;

public class CommentoProdotto extends Commento {
    private Cliente cliente;
    private ListaAcquistoCliente listaAcquisto;

    public CommentoProdotto() {
    }

    public CommentoProdotto(int numeroStelle, String testo, Cliente cliente, ListaAcquistoCliente listaAcquisto) {
        super(numeroStelle, testo);
        this.cliente = cliente;
        this.listaAcquisto = listaAcquisto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ListaAcquistoCliente getListaAcquisto() {
        return listaAcquisto;
    }

    public void setListaAcquisto(ListaAcquistoCliente listaAcquisto) {
        this.listaAcquisto = listaAcquisto;
    }
}
