package Model;

public class AdminFactory implements UtenteAbstractFactory{
    @Override
    public IUtente creaUtente() {
        return new Admin();
    }
}
