package Model;

public class CategoriaProdottoFactory implements CategoriaAbstractFactory{
    @Override
    public ICategoria crea() {
        return new CategoriaProdotto();
    }
}
