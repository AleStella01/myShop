package Model;

import java.util.List;

public interface ICategoria {
    int getId();
    void setId(int id);
    String getTipo();
    String getNome();
    void setNome(String nome);
    public List<ICategoria> getSottocategorie();
    public void add(ICategoria categoria);
    public void add(List<ICategoria> categorie);
}
