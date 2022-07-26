package Test.DAO;

import DAO.CategoriaDAO;
import DAO.ServizioDAO;
import Model.ArticoloAbstractFactory;
import Model.ArticoloFactoryProvider;
import Model.IArticolo;
import Model.Servizio;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ServizioDAOTest {
    @Test
    public void findByNameTest(){
        IArticolo servizio = ServizioDAO.getInstance().findByName("montaggio");
        System.out.println(servizio);
        Assert.assertTrue(servizio instanceof Servizio);
    }

    @Test
    public void findAllTest(){
        ArrayList<IArticolo> servizi = ServizioDAO.getInstance().findAll();
        Assert.assertTrue(servizi.size() >= 1);
        for (IArticolo s : servizi)
            System.out.println(s);
    }

    @Test
    public void addTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.SERVIZIO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        IArticolo servizio = factory.crea();

        servizio.setPrezzo(11F);
        servizio.setDescrizione("ti pitturiamo casa");
        servizio.setNome("imbiancaggio");
        servizio.setCategoria(CategoriaDAO.getInstance().findById(8));

        ServizioDAO.getInstance().add(servizio);
    }

    @Test
    public void updateTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.SERVIZIO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        IArticolo servizio = factory.crea();

        servizio.setPrezzo(11F);
        servizio.setDescrizione("ti imbianchiamo casa");
        servizio.setNome("imbiancaggio");
        servizio.setCategoria(CategoriaDAO.getInstance().findById(8));
        servizio.setId(3);

        ServizioDAO.getInstance().update(servizio);
    }

    @Test
    public void removeByIdTest(){
        ServizioDAO.getInstance().removeById(3);
    }

    @Test
    public void findByIdTest(){
        IArticolo servizio = ServizioDAO.getInstance().findById(1);
        System.out.println(servizio);
        Assert.assertTrue(servizio instanceof Servizio);
    }
}
