package Test.DAO;

import DAO.CollocazioneDAO;
import DAO.UtenteDAO;
import Model.Collocazione;
import Model.CollocazioneFactory;
import Model.CollocazioneProdotto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CollocazioneDAOTest {
    @Test
    public void findByIdTest(){
        Collocazione collocazione = CollocazioneDAO.getInstance().findById(1);
        Assert.assertTrue(collocazione instanceof CollocazioneProdotto);
        System.out.println(collocazione);
    }

    @Test
    public void findAllTest(){
        ArrayList<Collocazione> collocazioni = CollocazioneDAO.getInstance().findAll();
        Assert.assertTrue(collocazioni.size() >= 1);
        for (Collocazione c : collocazioni){
            System.out.println(c);
        }
    }

    @Test
    public void addTest(){
        CollocazioneFactory.CollocazioneType type = CollocazioneFactory.CollocazioneType.MAGAZZINO;
        CollocazioneFactory factory = new CollocazioneFactory();
        Collocazione collocazione = factory.getCollocazione(type);

        collocazione.setCorsia("O");
        collocazione.setScaffale(4);

        System.out.println(collocazione);

        int result = CollocazioneDAO.getInstance().add(collocazione);
        System.out.println(result);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void removeByIdTest(){
        CollocazioneDAO.getInstance().removeById(6);
    }

    @Test
    public void updateTest(){
        CollocazioneFactory.CollocazioneType type = CollocazioneFactory.CollocazioneType.MAGAZZINO;
        CollocazioneFactory factory = new CollocazioneFactory();
        Collocazione collocazione = factory.getCollocazione(type);

        collocazione.setCorsia("O");
        collocazione.setScaffale(5);
        collocazione.setId(7);

        CollocazioneDAO.getInstance().update(collocazione);
    }

    @Test
    public void findByCorsiaEScaffaleTest(){
        Collocazione collocazione = CollocazioneDAO.getInstance().findByCorsiaEScaffale("O", 5);
        Assert.assertTrue(collocazione instanceof CollocazioneProdotto);
        System.out.println(collocazione);
    }
}
