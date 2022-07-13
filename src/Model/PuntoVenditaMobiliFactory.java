package Model;

public class PuntoVenditaMobiliFactory implements PuntoVenditaAbstractFactory{
    @Override
    public IPuntoVendita crea() {
        return new PuntoVendita();
    }
}
