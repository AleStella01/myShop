package Business.FactoryMethod;

public class NotificaEmail extends Notifica {

    @Override
    public boolean inviaNotifica() {
        System.out.println("Invio tramite email");
        return true;
    }
}
