package Test;

import Business.FactoryMethod.Notifica;
import Business.FactoryMethod.NotificationFactory;
import Model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class NotificationFactoryTest {

    @Test
    public void getCanaleNotificaTest() {

        Cliente c=new Cliente();
        c.setCanalePreferito(NotificationFactory.TipoNotifica.SMS);
        String msg = "Messaggio di test";

        NotificationFactory factory = new NotificationFactory();
        Notifica n = factory.getCanaleNotifica(c.getCanalePreferito());

        Assert.assertNotNull(n);

        if(n!=null) {
            n.setCliente(c);
            n.setMsg(msg);
            boolean esito = n.inviaNotifica();
            Assert.assertTrue(esito);
        }

    }
}
