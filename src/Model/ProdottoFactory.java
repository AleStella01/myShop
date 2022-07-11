package Model;

public class ProdottoFactory implements ArticoloAbstractFactory {
    @Override
    public IArticolo crea() {
        return new Prodotto();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
