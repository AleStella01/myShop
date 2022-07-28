package Business;

import DAO.UtenteDAO;
import Model.IUtente;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public static synchronized UtenteBusiness getInstance() {
        if(instance == null) {
            instance = new UtenteBusiness();
        }
        return instance;
    }

    public LoginResult login(String username, String password) {

        UtenteDAO uDao = UtenteDAO.getInstance();

        LoginResult result = new LoginResult();

        // 1. controllare se l'utente esiste
        boolean userExists = uDao.userExists(username);
        if(!userExists) {
            result.setResult(LoginResult.Result.USER_DOESNT_EXIST);
            result.setMessage("Lo username inserito non esiste");
            return result;
        }

        // 2. controllare se username e password sono ok
        boolean credentialsOk = uDao.checkCredentials(username, password);
        if(!credentialsOk) {
            result.setResult(LoginResult.Result.WRONG_PASSWORD);
            result.setMessage("La password digitata non è corretta");
            return result;
        }



        IUtente utente=uDao.findById(username);
        SessionManager.getSession().put(SessionManager.LOGGED_USER, utente);
        result.setMessage("Benvenuto "+utente.getNome() +"!!");
        result.setResult(LoginResult.Result.LOGIN_OK);

        return result;

    }

}
