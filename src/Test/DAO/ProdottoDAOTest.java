package Test.DAO;

import DAO.CategoriaDAO;
import DAO.CollocazioneDAO;
import DAO.ProdottoDAO;
import DAO.ProduttoreDAO;
import Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ProdottoDAOTest {
    @Test
    public void findByNameTest(){
        IArticolo articolo = ProdottoDAO.getInstance().findByName("tavolo");
        System.out.println(articolo);
        Assert.assertTrue(articolo instanceof Prodotto);
    }

    @Test
    public void findAllTest(){
        ArrayList<IArticolo> prodotti = ProdottoDAO.getInstance().findAll();
        Assert.assertTrue(prodotti.size() >= 1);
        for (IArticolo p : prodotti)
            System.out.println(p);
    }

    @Test
    public void addTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        IArticolo articolo = factory.crea();
        IProdotto prodotto = (IProdotto) articolo;

        articolo.setNome("tavolino");
        articolo.setDescrizione("tavolino in acciaio");
        articolo.setPrezzo(81.0F);
        prodotto.setCollocazione(CollocazioneDAO.getInstance().findById(3));
        prodotto.setProduttore(ProduttoreDAO.getInstance().findById(3));
        prodotto.setCategoria(CategoriaDAO.getInstance().findById(2));

        System.out.println(articolo);
        ProdottoDAO.getInstance().add(articolo);
    }

    @Test
    public void removeByIdTest(){
        ProdottoDAO.getInstance().removeById(3);
    }

    @Test
    public void updateTest(){
        ArticoloFactoryProvider.ArticoloType type = ArticoloFactoryProvider.ArticoloType.PRODOTTO;
        ArticoloAbstractFactory factory = ArticoloFactoryProvider.getFactory(type);
        IArticolo articolo = factory.crea();
        IProdotto prodotto = (IProdotto) articolo;

        articolo.setId(4);
        articolo.setNome("tavolino");
        articolo.setDescrizione("tavolino in vetro");
        articolo.setPrezzo(81.0F);
        prodotto.setCollocazione(CollocazioneDAO.getInstance().findById(3));
        prodotto.setProduttore(ProduttoreDAO.getInstance().findById(3));
        prodotto.setCategoria(CategoriaDAO.getInstance().findById(2));

        ProdottoDAO.getInstance().update(articolo);
    }

    @Test
    public void findByIdTest(){
        IArticolo articolo = ProdottoDAO.getInstance().findById(4);
        System.out.println(articolo);
        Assert.assertTrue(articolo instanceof Prodotto);
    }
}
