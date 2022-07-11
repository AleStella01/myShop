package Model;

public class CommentoFactory {
    public enum CommentoType{PRODOTTO}

    public Commento getCommentoFactory(CommentoType type){
        if (type == null){
            return null;
        }

        switch (type){
            case PRODOTTO:
                return new CommentoProdotto();
            default:
                return null;
        }
    }
}
