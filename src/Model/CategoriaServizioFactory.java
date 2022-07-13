package Model;

public class CategoriaServizioFactory implements CategoriaAbstractFactory{
    @Override
    public ICategoria crea() {
        return new CategoriaServizio();
    }
}
