package Model;

public class ArticoloFactoryProvider {
    public enum ArticoloType {PRODOTTO, PRODOTTO_COMPOSITO, SERVIZIO}

    public static ArticoloAbstractFactory getFactory(ArticoloType type){
        if (type == null){
            return null;
        }

        switch (type){
            case PRODOTTO:
                return new ProdottoFactory();
            case PRODOTTO_COMPOSITO:
                return new ProdottoCompositoFactory();
            case SERVIZIO:
                return new ServizioFactory();
            default:
                return null;
        }
    }
}
