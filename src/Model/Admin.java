package Model;

import java.util.Date;

public class Admin extends Utente{
    public Admin() {
        super();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", dataNascita=" + dataNascita +
                ", citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public String getType() {
        return "ADMIN";
    }
}
