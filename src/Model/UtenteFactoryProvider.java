package Model;

public class UtenteFactoryProvider {
    public enum UserType{ADMIN, MANAGER, CLIENTE}

    public static UtenteAbstractFactory getFactory(UserType type){
        if (type == null){
            return null;
        }

        switch (type){
            case ADMIN:
                return new AdminFactory();
            case MANAGER:
                return new ManagerFactory();
            case CLIENTE:
                return new ClienteFactory();
            default:
                return null;
        }
    }
}
