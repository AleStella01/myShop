package Model;

public interface IArticolo {
    String getNome();
    void setNome(String nome);
    int getId();
    void setId(int id);
    Float getPrezzo();
    void setPrezzo(Float prezzo);
    String getDescrizione();
    void setDescrizione(String descrizione);
    ICategoria getCategoria();
}
