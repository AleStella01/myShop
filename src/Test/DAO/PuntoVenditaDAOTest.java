package Test.DAO;

import DAO.ProdottoDAO;
import DAO.PuntoVenditaDAO;
import DAO.UtenteDAO;
import Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PuntoVenditaDAOTest {
    @Test
    public void findByIdTest(){
        IPuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(1);
        System.out.println(puntoVendita);
        Assert.assertTrue(puntoVendita instanceof PuntoVendita);
    }

    @Test
    public void findByNomeTest(){
        IPuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByName("cavour");
        System.out.println(puntoVendita);
        Assert.assertTrue(puntoVendita instanceof PuntoVendita);
    }

    @Test
    public void findByManagerTest(){
        IUtente manager = UtenteDAO.getInstance().findById(32);
        IPuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findByManager(manager);
        System.out.println(puntoVendita);
        Assert.assertTrue(puntoVendita instanceof PuntoVendita);
    }

    @Test
    public void findAllTest(){
        ArrayList<IPuntoVendita> puntiVendita = PuntoVenditaDAO.getInstance().findAll();
        Assert.assertTrue(puntiVendita.size() >= 1);
        for (IPuntoVendita p : puntiVendita)
            System.out.println(p);
    }

    @Test
    public void addTest(){
        PuntoVenditaFactoryProvider.PuntoVenditaType type = PuntoVenditaFactoryProvider.PuntoVenditaType.MOBILI;
        PuntoVenditaAbstractFactory factory = PuntoVenditaFactoryProvider.getFactory(type);
        IPuntoVendita puntoVendita = factory.crea();

        puntoVendita.setNome("garibaldi");
        puntoVendita.setIndirizzo("via giacomo leopardi 19");
        puntoVendita.setCitta("martano");
        puntoVendita.setManager(UtenteDAO.getInstance().findById(32));

        PuntoVenditaDAO.getInstance().add(puntoVendita);
    }

    @Test
    public void updateTest(){
        PuntoVenditaFactoryProvider.PuntoVenditaType type = PuntoVenditaFactoryProvider.PuntoVenditaType.MOBILI;
        PuntoVenditaAbstractFactory factory = PuntoVenditaFactoryProvider.getFactory(type);
        IPuntoVendita puntoVendita = factory.crea();

        puntoVendita.setNome("garibaldi");
        puntoVendita.setIndirizzo("via san giovanni bosco 76");
        puntoVendita.setCitta("martano");
        puntoVendita.setManager(UtenteDAO.getInstance().findById(32));
        puntoVendita.setId(5);

        PuntoVenditaDAO.getInstance().update(puntoVendita);
    }

    @Test
    public void addProdottoVendutoTest(){
        IPuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(5);
        IProdotto prodotto = (IProdotto) ProdottoDAO.getInstance().findById(2);
        int quantita = 9;

        PuntoVenditaDAO.getInstance().addProdottoVenduto(puntoVendita, prodotto, quantita);
    }

    @Test
    public void removeProdottoVendutoTest(){
        IPuntoVendita puntoVendita = PuntoVenditaDAO.getInstance().findById(5);
        IProdotto prodotto = (IProdotto) ProdottoDAO.getInstance().findById(2);

        PuntoVenditaDAO.getInstance().removeProdottoVenduto(puntoVendita, prodotto);
    }

    @Test
    public void removeByIdTest(){
        PuntoVenditaDAO.getInstance().removeById(5);
    }
}
