package Model;

public class ProduttoreMobiliFactory implements ProduttoreAbstractFactory{
    @Override
    public IProduttore crea() {
        return new ProduttoreMobili();
    }
}
