package Model;

import java.util.List;

public class CategoriaServizio implements ICategoria{
    private String nome;
    private int id;
    private List<ICategoria> sottocategorie;

    public CategoriaServizio() {
        this.nome = null;
    }

    public CategoriaServizio(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public List<ICategoria> getSottocategorie() {
        return sottocategorie;
    }

    @Override
    public void add(ICategoria categoria) {
        sottocategorie.add(categoria);
    }

    @Override
    public void add(List<ICategoria> categorie) {
        sottocategorie.addAll(categorie);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTipo() {
        return "SERVIZIO";
    }
}
