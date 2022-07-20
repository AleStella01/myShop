package Model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProdotto implements ICategoria{
    private String nome;
    private List<ICategoria> sottocategorie;
    private int id;

    public CategoriaProdotto() {
    }

    public CategoriaProdotto(String nome) {
        this.nome = nome;
        sottocategorie = new ArrayList<>();
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public List<ICategoria> getSottocategorie() {
        return sottocategorie;
    }

    @Override
    public void add(ICategoria categoria){
        sottocategorie.add(categoria);
    }

    @Override
    public void add(List<ICategoria> categorie){
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
        return "PRODOTTO";
    }

}
