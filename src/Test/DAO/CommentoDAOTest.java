package Test.DAO;

import DAO.CommentoDAO;
import DAO.ListaAcquistoDAO;
import DAO.UtenteDAO;
import Model.Commento;
import Model.CommentoFactory;
import Model.IUtente;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.stream.events.Comment;
import java.util.List;

public class CommentoDAOTest {
    @Test
    public void findByUserTest(){
        IUtente utente = UtenteDAO.getInstance().findById("ndepascali");
        List<Commento> commenti = CommentoDAO.getInstance().findByUser(utente);
        Assert.assertTrue(commenti.size() >= 1);
        for (Commento c : commenti)
            System.out.println(c);
    }

    @Test
    public void findAllTest(){
        List<Commento> commenti = CommentoDAO.getInstance().findAll();
        Assert.assertTrue(commenti.size() >= 1);
        for (Commento c : commenti)
            System.out.println(c);
    }

    @Test
    public void addTest(){
        CommentoFactory.CommentoType type = CommentoFactory.CommentoType.PRODOTTO;
        CommentoFactory factory = new CommentoFactory();
        Commento commento = factory.getCommentoFactory(type);

        commento.setTesto("stupendo");
        commento.setNumeroStelle(5);
        commento.setCliente(UtenteDAO.getInstance().findById("astella"));
        commento.setListaAcquisto(ListaAcquistoDAO.getInstance().findById(3));

        CommentoDAO.getInstance().add(commento);
    }

    @Test
    public void updateTest(){
        CommentoFactory.CommentoType type = CommentoFactory.CommentoType.PRODOTTO;
        CommentoFactory factory = new CommentoFactory();
        Commento commento = factory.getCommentoFactory(type);

        commento.setTesto("stupendo MA");
        commento.setNumeroStelle(5);
        commento.setCliente(UtenteDAO.getInstance().findById("astella"));
        commento.setListaAcquisto(ListaAcquistoDAO.getInstance().findById(3));
        commento.setId(3);

        CommentoDAO.getInstance().update(commento);
    }

    @Test
    public void revoveByIdTest(){
        CommentoDAO.getInstance().removeById(3);
    }
}
