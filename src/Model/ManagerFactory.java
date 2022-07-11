package Model;

public class ManagerFactory implements UtenteAbstractFactory{
    @Override
    public IUtente creaUtente() {
        return new Manager();
    }
}
