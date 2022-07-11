package Model;

public class ClienteFactory implements UtenteAbstractFactory{
    @Override
    public IUtente creaUtente() {
        return new Cliente();
    }
}
