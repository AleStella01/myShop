package Model;

public class NotificationFactory {
    public enum TipoNotifica { EMAIL}

    public Notifica getCanaleNotifica(TipoNotifica type) {

        if(type == null) type = TipoNotifica.EMAIL; //valore di default

        switch(type) {
            case EMAIL: return new NotificaEmail();
        }

        return null;

    }
}
