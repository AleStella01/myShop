package Test.DAO;

import DAO.MessaggioDAO;
import DAO.UtenteDAO;
import Model.IUtente;
import Model.Notifica;
import Model.NotificaEmail;
import Model.NotificationFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MessaggioDAOTest {
    @Test
    public void findByIdTest(){
        Notifica notifica = MessaggioDAO.getInstance().findById(1);
        System.out.println(notifica);
        Assert.assertTrue(notifica instanceof NotificaEmail);
    }

    @Test
    public void findAllTest(){
        ArrayList<Notifica> notifiche = MessaggioDAO.getInstance().findAll();
        Assert.assertTrue(notifiche.size() >= 1);
        for (Notifica n : notifiche)
            System.out.println(n);
    }

    @Test
    public void findByClienteTest(){
        ArrayList<Notifica> notifiche = MessaggioDAO.getInstance().findByCliente(UtenteDAO.getInstance().findById(1));
        Assert.assertTrue(notifiche.size() >= 1);
        for (Notifica n : notifiche){
            System.out.println(n);
        }
    }

    @Test
    public void findByManagerTest(){
        ArrayList<Notifica> notifiche = MessaggioDAO.getInstance().findByManager(UtenteDAO.getInstance().findById(32));
        Assert.assertTrue(notifiche.size() >= 1);
        for (Notifica n : notifiche){
            System.out.println(n);
        }
    }

    @Test
    public void addTest(){
        String testo = "good morning";
        IUtente cliente = UtenteDAO.getInstance().findById("ndepascali");
        IUtente manager = UtenteDAO.getInstance().findById("rvergallo");

        NotificationFactory.TipoNotifica type = NotificationFactory.TipoNotifica.EMAIL;
        NotificationFactory factory = new NotificationFactory();
        Notifica n = factory.getCanaleNotifica(type);

        n.setMsg(testo);
        n.setCliente(cliente);
        n.setManager(manager);

        int result = MessaggioDAO.getInstance().add(n);
        System.out.println(n);
        System.out.println(result);

        Assert.assertEquals(1, result);
    }

    @Test
    public void removeByIdTest(){
        MessaggioDAO.getInstance().removeById(5);
    }

    @Test
    public void updateTest(){
        String testo = "good morning !";
        IUtente cliente = UtenteDAO.getInstance().findById("ndepascali");
        IUtente manager = UtenteDAO.getInstance().findById("rvergallo");

        NotificationFactory.TipoNotifica type = NotificationFactory.TipoNotifica.EMAIL;
        NotificationFactory factory = new NotificationFactory();
        Notifica n = factory.getCanaleNotifica(type);

        n.setMsg(testo);
        n.setCliente(cliente);
        n.setManager(manager);
        n.setId(6);

        MessaggioDAO.getInstance().update(n);
    }
}
