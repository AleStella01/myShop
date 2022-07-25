package Test.Model;

import Model.Commento;
import Model.CommentoFactory;
import Model.CommentoProdotto;
import org.junit.Assert;
import org.junit.Test;

public class CommentoFactoryTest {
    @Test
    public void getCommentoProdottoFactoryTest(){
        CommentoFactory.CommentoType type = CommentoFactory.CommentoType.PRODOTTO;
        CommentoFactory factory = new CommentoFactory();
        Commento commento = factory.getCommentoFactory(type);

        Assert.assertNotNull(commento);
        Assert.assertTrue(commento instanceof CommentoProdotto);
    }
}
