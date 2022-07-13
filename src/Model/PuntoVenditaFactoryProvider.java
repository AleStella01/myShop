package Model;

public class PuntoVenditaFactoryProvider {
    public enum PuntoVenditaType{MOBILI}

    public static PuntoVenditaAbstractFactory getFactory(PuntoVenditaType type){
        if (type == null){
            return null;
        }

        switch (type) {
            case MOBILI:
                return new PuntoVenditaMobiliFactory();
            default:
                return null;
        }
    }

}
