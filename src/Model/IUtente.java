package Model;

import java.util.Date;

public interface IUtente {
    void setNome(String nome);
    void setCognome(String cognome);
    void setCodiceFiscale(String codiceFiscale);
    void setDataNascita(String dataNascita);
    void setCitta(String citta);
    void setIndirizzo(String indirizzo);
    void setUsername(String username);
    void setPassword(String password);
    void setEmail(String email);
    String getNome();
    String getCognome();
    String getCodiceFiscale();
    String getDataNascita();
    String getCitta();
    String getIndirizzo();
    String getUsername();
    String getEmail();
    String getPassword();
    int getId();
    void setId(int id);
    String getType();
}
