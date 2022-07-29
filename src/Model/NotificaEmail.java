package Model;

import Business.MailHelper;

public class NotificaEmail extends Notifica{
    @Override
    public boolean inviaNotifica() {
        MailHelper.getInstance().send("nicdepascali@gmail.com", "Lista acquisto MyShop", "Ti inoltriamo la lista d'acquisto di myShop");
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
