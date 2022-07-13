package Model;

public abstract class Notifica {
    protected String msg;
    protected IUtente cliente;
    protected IUtente manager;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public IUtente getCliente() {
        return cliente;
    }

    public void setCliente(IUtente cliente) {
        this.cliente = cliente;
    }

    public IUtente getManager() {
        return manager;
    }

    public void setManager(IUtente manager) {
        this.manager = manager;
    }

    public abstract boolean inviaNotifica();
}
