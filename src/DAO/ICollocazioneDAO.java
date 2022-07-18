package DAO;

import Model.Collocazione;

import java.util.ArrayList;

public interface ICollocazioneDAO {
    Collocazione findById(int id);
    ArrayList<Collocazione> findAll();
    int add(Collocazione collocazione);
    int removeById(int id);
    int update(Collocazione collocazione);
    Collocazione findByCorsiaEScaffale(String corsia, int scaffale);
}
