package View;

import Business.SessionManager;
import Model.Admin;
import Model.Cliente;
import Model.Manager;
import Model.Utente;
import View.Decorator.AmministratoreMenuDecorator;
import View.Decorator.ClienteMenuDecorator;
import View.Decorator.GuestMenu;
import View.Decorator.ManagerMenuDecorator;
import View.Listener.LoginListener;
import View.Listener.LogoutListener;
import View.Listener.RegistrazioneListener;

import javax.swing.*;
import java.awt.*;

public class SchiedaIniziale extends JFrame {

    private JPanel utenteLoggato = new JPanel();
    private JPanel nord = new JPanel();

    private JPanel west = new JPanel();

    private JPanel centro = new JPanel();
    private JPanel sud = new JPanel();

    private JPanel east = new JPanel();

    public SchiedaIniziale() {
        super("MyShop");

        this.setSize(1280,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());



        nord.setLayout(new FlowLayout());
        centro.setLayout(new GridLayout(2,1));
        sud.setLayout(new FlowLayout());

        utenteLoggato.setLayout(new FlowLayout());

        west.setLayout(new GridLayout(10,1));
        c.add(east,BorderLayout.EAST);
        east.setLayout(new GridLayout(10,1));


        this.setVisible(true);
    }

    public void mostraPannelloUtenteLoggato(String message) {
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        //1. togliere il pannello utente non loggato
        remove(nord);
        centro.removeAll();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        //2. inserire il pannello utente loggato
        utenteLoggato.removeAll();
        utenteLoggato.add(new JLabel(message));
        JButton uscita=new JButton("Logout");
        LogoutListener logout=new LogoutListener(this,nord,west,centro,sud,utenteLoggato,u);
        uscita.addActionListener(logout);

        JMenu menuAccount = new JMenu("Il mio Account");
        menuBar.add(menuAccount);
        JMenuItem informazioni = new JMenuItem("Informazioni Account");
        menuAccount.add(informazioni);
        JMenuItem logout2 = new JMenuItem("Logout");
        logout2.addActionListener(new LogoutListener(this,nord,west,centro,sud,utenteLoggato,u));
        menuAccount.add(logout2);

        utenteLoggato.add(uscita);
        add(utenteLoggato,BorderLayout.NORTH);



        repaint();
        validate();

    }

    public void aggiornaMenuPulsanti() {
        west.removeAll();


        //..
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        System.out.println(u.getClass().toString());
        if(u instanceof Cliente) {
            //decoriamo il menu usando il ClienteMenuDecorator
           GuestMenu guestMenu = new GuestMenu(this);
            ClienteMenuDecorator clienteMenu = new ClienteMenuDecorator(guestMenu,centro);
            for(JButton btn : clienteMenu.getPulsanti())
                west.add(btn);

        }
        else if(u instanceof Manager){
            GuestMenu guestMenu = new GuestMenu(this);
            ClienteMenuDecorator clienteMenu=new ClienteMenuDecorator(guestMenu,centro);
            ManagerMenuDecorator managerMenu = new ManagerMenuDecorator(guestMenu,clienteMenu);
            for(JButton btn : managerMenu.getPulsanti())
                west.add(btn);
        }else if(u instanceof Admin){
            GuestMenu guestMenu = new GuestMenu(this);
            ClienteMenuDecorator clienteMenu=new ClienteMenuDecorator(guestMenu,centro);
            AmministratoreMenuDecorator amministratoreMenu = new AmministratoreMenuDecorator(guestMenu,clienteMenu);
            for(JButton btn : amministratoreMenu.getPulsanti())
                west.add(btn);

        }

        repaint();
        validate();


    }

    public void mostraCatalogo() {
        centro.removeAll();

        centro.add(CatalogoPanel.getInstance());
        repaint();
        validate();
    }



    public void mostraPannelloUtenteNonLoggato(){
        JTextField username = new JTextField(20);
        JPasswordField password = new JPasswordField(20);
        JButton login = new JButton("Login");
        login.setActionCommand(LoginListener.LOGIN_BTN);
        nord.add(username);
        nord.add(password);
        nord.add(login);
        LoginListener loginListener = new LoginListener(username, password);
        loginListener.setFrame(this);
        login.addActionListener(loginListener);


        centro.add(new JLabel("Benvenuto, effettua il login"));

        sud.add(new JLabel("Progetto realizzato per l'esame di PIS"));

        JButton registrati=new JButton("Registrati");
        RegistrazioneListener registrazioneListener=new RegistrazioneListener();
        registrati.addActionListener(registrazioneListener);



        getContentPane().add(nord, BorderLayout.NORTH);
        getContentPane().add(centro, BorderLayout.CENTER);
        getContentPane().add(sud, BorderLayout.SOUTH);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem apri = new JMenuItem("Apri...");
        apri.addActionListener(loginListener);
        apri.setActionCommand(LoginListener.APRIFILE_MENU);
        menuFile.add(apri);

        JMenu menuRecenti = new JMenu("Recenti");
        menuFile.add(menuRecenti);
        menuRecenti.add(new JMenuItem("/file1"));
        menuRecenti.add(new JMenuItem("/file2"));
        menuRecenti.add(new JMenuItem("/file3"));

        GuestMenu guestMenu = new GuestMenu(this);

        for(JButton btn : guestMenu.getPulsanti()) {
            west.add(btn);
        }
        west.add(registrati);
        getContentPane().add(west, BorderLayout.WEST);



        repaint();
        revalidate();

    }
}
