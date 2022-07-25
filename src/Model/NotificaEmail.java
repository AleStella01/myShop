package Model;

public class NotificaEmail extends Notifica{
    @Override
    public boolean inviaNotifica() {
        System.out.println("Invio tramite email");
        return true;
    }

    @Override
    public String toString() {
        return "NotificaEmail{" +
                "msg='" + msg + '\'' +
                ", cliente=" + cliente +
                ", manager=" + manager +
                ", id=" + id +
                '}';
    }
}
