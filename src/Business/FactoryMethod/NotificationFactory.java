package Business.FactoryMethod;

public class NotificationFactory {

    public enum TipoNotifica { EMAIL, SMS, PUSH}

    public Notifica getCanaleNotifica(TipoNotifica type) {

        if(type == null) type = TipoNotifica.EMAIL; //valore di default

        switch(type) {
            case EMAIL: return new NotificaEmail();
            case SMS: return new NotificaSMS();
            case PUSH: return new NotificaPush();
        }

        return null;

    }
}
