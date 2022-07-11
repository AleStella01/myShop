package Business.FactoryMethod;

public class NotificaPush extends Notifica {

    @Override
    public boolean inviaNotifica() {
        System.out.println("Invio tramite push");
        return true;
    }
}
