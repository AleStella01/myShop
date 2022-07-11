package Model;

public class ServizioFactory implements ArticoloAbstractFactory {
    @Override
    public IArticolo crea() {
        return new Servizio();
    }

    @Override
    public ICategoria creaCategoria() {
        return new CategoriaServizio();
    }
}
