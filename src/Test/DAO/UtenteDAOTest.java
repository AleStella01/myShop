package Test.DAO;

import DAO.UtenteDAO;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UtenteDAOTest {
    IUtente user;

    @Before
    public void inizialize() {
        UtenteFactoryProvider.UserType type = UtenteFactoryProvider.UserType.ADMIN;
        UtenteAbstractFactory factory = UtenteFactoryProvider.getFactory(type);
        user = factory.creaUtente();

        user.setNome("Luca");
        user.setCognome("Mainetti");
        user.setIndirizzo("cahfjdg");
        user.setUsername("lmainetti");
        user.setEmail("lmainetti@gmail.com");
        user.setPassword("JoyaBremer21");
        user.setCitta("Milano");
        String d = "1960-01-01";
        user.setDataNascita(d);
        user.setCodiceFiscale("sdfghj");
    }

    @Test
    public void getUserTest(){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        IUtente u = utenteDAO.findById(1);
        Assert.assertTrue(u instanceof Cliente);
        System.out.println(u);

        IUtente utente2 = UtenteDAO.getInstance().findById(31);
        Assert.assertTrue(utente2 instanceof Admin);
        System.out.println(utente2);

        IUtente utente3 = UtenteDAO.getInstance().findById(32);
        System.out.println(utente3);
        Assert.assertTrue(utente3 instanceof Manager);

        IUtente utente4 = UtenteDAO.getInstance().findById(33);
        Assert.assertTrue(utente4 instanceof Cliente);
        System.out.println(utente4);
    }

    @Test
    public void getUserByNameTest(){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        IUtente u = utenteDAO.findById("astella");
        Assert.assertTrue(u instanceof Cliente);
        System.out.println(u);
    }

    @Test
    public void getAllUserTest(){
        UtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<IUtente> utenti = utenteDAO.findAll();
        Assert.assertTrue(utenti.size() >= 1);
        for (IUtente utente : utenti){
            System.out.println(utente);
        }
    }

    @Test
    public void addUtenteTest(){
        Assert.assertTrue(user instanceof Admin);
        System.out.println(user);

        int result = UtenteDAO.getInstance().add(user);
        System.out.println(result);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void removeByIdTest(){
        UtenteDAO.getInstance().removeById(user.getUsername());
    }

    @Test
    public void updateTest(){
        user.setNome("Luca");
        UtenteDAO.getInstance().update(user);
    }


}
