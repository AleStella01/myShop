package Model;

public class CategoriaServizio implements ICategoria{
    private String nome;

    public CategoriaServizio() {
        this.nome = null;
    }

    public CategoriaServizio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
