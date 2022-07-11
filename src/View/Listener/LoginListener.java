package View.Listener;

import Business.LoginResult;
import Business.UtenteBusiness;
import View.EsempioGerarchiaLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    public final static String LOGIN_BTN = "login_btn";
    public final static String APRIFILE_MENU = "aprifile_menu";

    private JTextField username;
    private JPasswordField password;
    private EsempioGerarchiaLayout frame;

    public LoginListener(JTextField username, JPasswordField password) {
        this.username = username;
        this.password = password;
    }

    public LoginListener(EsempioGerarchiaLayout frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if(LOGIN_BTN.equals(action)) {
            String user = username.getText();
            String pwd = new String(password.getPassword());

            System.out.println("username: " + user);
            System.out.println("password: " + pwd);
            System.out.println("------------");

            LoginResult result = UtenteBusiness.getInstance().login(user, pwd);

            if(result.getResult() == LoginResult.Result.LOGIN_OK) {
                frame.mostraPannelloUtenteLoggato(result.getMessage());
                //refresh view dei pulsanti
                frame.aggiornaMenuPulsanti();
            }
            else
                JOptionPane.showMessageDialog(null, result.getMessage());
        }
        else if(APRIFILE_MENU.equals(action)) {
            JOptionPane.showMessageDialog(null, "Seleziona file");
        }

        /*
        Object source = e.getSource();

        if(source instanceof JButton) {
            JButton btn = (JButton) source;
            System.out.println(btn.getText());
            String user = username.getText();
            String pwd = new String(password.getPassword());
            System.out.println("username: " + user);
            System.out.println("password: " + pwd);
            System.out.println("------------");
        }
        else if(source instanceof JMenuItem) {
            JOptionPane.showMessageDialog(null, "Seleziona file");
        }*/
    }

    public void setFrame(EsempioGerarchiaLayout frame) {
        this.frame = frame;
    }
}
