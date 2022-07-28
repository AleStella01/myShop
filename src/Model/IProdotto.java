package Model;

public interface IProdotto {
    Float getPrezzo();
    String getNome();
    IProduttore getProduttore();
    Collocazione getCollocazione();
    ICategoria getCategoria();
    void setCollocazione(Collocazione c);
    void setProduttore(IProduttore produttore);
    void setCategoria(ICategoria categoria);
    int getId();
}
