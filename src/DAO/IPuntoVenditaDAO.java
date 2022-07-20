package DAO;

import Model.IProdotto;
import Model.IPuntoVendita;
import Model.IUtente;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {
    IPuntoVendita findById(int id);
    IPuntoVendita findByName(String nome);
    IPuntoVendita findByManager(IUtente manager);
    ArrayList<IPuntoVendita> findAll();
    int add(IPuntoVendita puntoVendita);
    int removeById(int id);
    int update(IPuntoVendita puntoVendita);
    int addProdottoVenduto(IPuntoVendita puntoVendita, IProdotto prodotto, int quantita);
    int removeProdottoVenduto(IPuntoVendita puntoVendita, IProdotto prodotto);
}
