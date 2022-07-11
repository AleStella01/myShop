package Model;

import java.util.Date;

public interface IUtente {
    void setNome(String nome);
    void setCognome(String cognome);
    void setCodiceFiscale(String codiceFiscale);
    void setDataNascita(Date dataNascita);
    void setCitta(String citta);
    void setIndirizzo(String indirizzo);
    void setUsername(String username);
    void setPassword(String password);
    void setEmail(String email);
    String getNome();
    String getCognome();
    String getCodiceFiscale();
    Date getDataNascita();
    String getCitta();
    String getIndirizzo();
    String getUsername();
    String getEmail();
    String getPassword();
}
