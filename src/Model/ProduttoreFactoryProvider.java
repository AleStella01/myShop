package Model;

public class ProduttoreFactoryProvider {
    public enum ProduttoreType {PRODUTTORE_MOBILI}

    public static ProduttoreAbstractFactory getFactory(ProduttoreType type){
        if (type == null) {
            return null;
        }

        switch (type){
            case PRODUTTORE_MOBILI:
                return new ProduttoreMobiliFactory();
            default:
                return null;
        }
    }
}
