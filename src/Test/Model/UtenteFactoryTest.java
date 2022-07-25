package Test.Model;

import Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

public class UtenteFactoryTest {
    @Test
    public void getAdminFactoryTest(){
        UtenteFactoryProvider.UserType type = UtenteFactoryProvider.UserType.ADMIN;
        UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof AdminFactory);
        IUtente utente = factory.creaUtente();
        System.out.println(UtenteFactoryProvider.UserType.ADMIN);
        Assert.assertTrue(utente instanceof Admin);
    }

    @Test
    public void getManagerFactoryTest(){
        UtenteFactoryProvider.UserType type = UtenteFactoryProvider.UserType.MANAGER;
        UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ManagerFactory);

        IUtente utente = factory.creaUtente();
        Assert.assertTrue(utente instanceof Manager);
    }

    @Test
    public void getClienteFactoryTest(){
        UtenteFactoryProvider.UserType type = UtenteFactoryProvider.UserType.CLIENTE;
        UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
        Assert.assertTrue(factory instanceof ClienteFactory);

        IUtente utente = factory.creaUtente();
        Assert.assertTrue(utente instanceof Cliente);
    }
}
