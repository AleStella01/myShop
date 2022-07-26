package Model;

public interface IProdotto {
    Float getPrezzo();
    String getNome();
    IProduttore getProduttore();
    Collocazione getCollocazione();
    void setCollocazione(Collocazione c);
    void setProduttore(IProduttore produttore);
    void setCategoria(ICategoria categoria);
    int getId();
}
