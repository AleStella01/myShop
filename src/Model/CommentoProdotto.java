package Model;

public class CommentoProdotto extends Commento {
    private IUtente cliente;
    private ListaAcquisto listaAcquisto;

    public CommentoProdotto() {
    }

    public CommentoProdotto(int numeroStelle, String testo, Cliente cliente, ListaAcquistoCliente listaAcquisto) {
        super(numeroStelle, testo);
        this.cliente = cliente;
        this.listaAcquisto = listaAcquisto;
    }

    @Override
    public IUtente getCliente() {
        return cliente;
    }

    @Override
    public void setCliente(IUtente cliente) {
        this.cliente = cliente;
    }

    @Override
    public ListaAcquisto getListaAcquisto() {
        return listaAcquisto;
    }

    @Override
    public void setListaAcquisto(ListaAcquisto listaAcquisto) {
        this.listaAcquisto = listaAcquisto;
    }
}
