package Model;

import java.util.Map;

public interface IPuntoVendita {
    String getNome();
    void setNome(String nome);
    String getCitta();
    void setCitta(String citta);
    String getIndirizzo();
    void setIndirizzo(String indirizzo);
    IUtente getManager();
    void setManager(IUtente manager);
    Map<IProdotto, Integer> getProdottiVenduti();
    void setProdottiVenduti(Map<IProdotto, Integer> prodottiVenduti);
    int getId();
    void setId(int id);
}
