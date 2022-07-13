package Model;

public class CategoriaFactoryProvider {
    public enum CategoriaFactoryType {PRODOTTO, SERVIZIO};

    public static CategoriaAbstractFactory getFactory(CategoriaFactoryType type) {
        if (type == null) {
            return null;
        }

        switch (type) {
            case PRODOTTO:
                return new CategoriaProdottoFactory();
            case SERVIZIO:
                return new CategoriaServizioFactory();
            default:
                return null;
        }
    }
}
