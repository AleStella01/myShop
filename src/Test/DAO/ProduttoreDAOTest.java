package Test.DAO;

import DAO.ProduttoreDAO;
import Model.IProduttore;
import Model.ProduttoreAbstractFactory;
import Model.ProduttoreFactoryProvider;
import Model.ProduttoreMobili;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProduttoreDAOTest {
    //private IProduttore produttore;
    @Before
    public void setUp(){

    }

    @Test
    public void findByNameTest(){
        IProduttore p = ProduttoreDAO.getInstance().findByName("IKEA");
        Assert.assertTrue(p instanceof ProduttoreMobili);
        System.out.println(p);
    }

    @Test
    public void findAllTest(){
        ArrayList<IProduttore> produttori = ProduttoreDAO.getInstance().findAll();
        Assert.assertTrue(produttori.size() >= 1);
        for (IProduttore p : produttori){
            System.out.println(p);
        }
    }

    @Test
    public void addTest(){
        ProduttoreFactoryProvider.ProduttoreType type = ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
        ProduttoreAbstractFactory factory = ProduttoreFactoryProvider.getFactory(type);
        IProduttore produttore = factory.crea();

        produttore.setNomeProduttore("mondo convenienza");
        produttore.setCitta("Cavallino");
        produttore.setNazione("Italia");
        produttore.setSitoWeb("www.mondoconvenienza.it");
        System.out.println(produttore);
        int result = ProduttoreDAO.getInstance().add(produttore);
        System.out.println(result);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void removeByNameTest(){
        ProduttoreDAO.getInstance().removeByName("mondo convenienza");
    }

    @Test
    public void updateTest(){
        ProduttoreFactoryProvider.ProduttoreType type = ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
        ProduttoreAbstractFactory factory = ProduttoreFactoryProvider.getFactory(type);
        IProduttore produttore = factory.crea();

        produttore.setNomeProduttore("mondo convenienza");
        produttore.setCitta("Maglie");
        produttore.setNazione("Italia");
        produttore.setSitoWeb("www.mondoconvenienza.it");
        produttore.setId(4);

        ProduttoreDAO.getInstance().update(produttore);
    }
}
