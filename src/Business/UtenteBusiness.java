package Business;

import DAO.UtenteDAO;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;

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

        // 3. che tipo di utente e' (cliente/manager/amministratore)
        boolean isCliente = uDao.isCliente(username);
        boolean isManager = uDao.isManager(username);
        boolean isAmministratore = uDao.isAmministratore(username);

        // 4. caricare oggetto utente (a seconda della tipologia)
        if(isCliente) {
            Cliente c = uDao.caricaCliente(username);
            // ...
            SessionManager.getSession().put(SessionManager.LOGGED_USER, c);
            result.setMessage("Benvenuto "+c.getName() +"!!");

        } else if(isManager) {
            Manager m = uDao.caricaManager(username);
            // ...
            SessionManager.getSession().put(SessionManager.LOGGED_USER, m);
            result.setMessage("Benvenuto "+m.getName() +"!!");

        } else if(isAmministratore) {
            Amministratore a = uDao.caricaAmministratore(username);
            // ...
            // ...
            SessionManager.getSession().put(SessionManager.LOGGED_USER, a);
            result.setMessage("Benvenuto "+a.getName() +"!!");
        }

        result.setResult(LoginResult.Result.LOGIN_OK);

        return result;

    }

}
