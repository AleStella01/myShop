package Model;

public class ProdottoCompositoFactory implements ArticoloAbstractFactory {
    @Override
    public IArticolo crea() {
        return new ProdottoComposito();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaProdotto();
    }
}
