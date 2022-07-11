package Model;

public class Manager extends Utente {

    private Float salario;

    /*
    public Manager(String name, String surname, String username, String pwd, String email, Float salario) {
        super(name, surname, username, pwd, email);
        this.salario = salario;
    }

     */


    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

}
