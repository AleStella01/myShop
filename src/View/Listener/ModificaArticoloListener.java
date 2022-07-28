package View.Listener;

import DAO.*;

import Model.*;
import View.CatalogoPanel;
import View.SchiedaArticoloTableModel;
import View.ViewModel.RigaCatalogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ModificaArticoloListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        ProduttoreDAO produttoreDAO=ProduttoreDAO.getInstance();
        CategoriaDAO categoriaDAO=CategoriaDAO.getInstance();
        CollocazioneDAO collocazioneDAO=CollocazioneDAO.getInstance();

        String[] optionsToChoose = {"Prodotto","Prodotto Composito", "Servizio"};

        JFrame jFrame = new JFrame();

        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(80, 50, 140, 20);

        JButton jButton = new JButton("Done");
        jButton.setBounds(100, 100, 90, 20);

        JLabel jLabel = new JLabel();
        jLabel.setBounds(90, 100, 400, 100);

        jFrame.add(jButton);
        jFrame.add(jComboBox);
        jFrame.add(jLabel);

        jFrame.setTitle("Seleziona l'articolo che vuoi modificare");

        jFrame.setLayout(null);
        jFrame.setSize(350, 250);
        jFrame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scelta= jComboBox.getItemAt(jComboBox.getSelectedIndex());
                if("Prodotto".equalsIgnoreCase(scelta)){

                    ProdottoDAO prodottoDAO=ProdottoDAO.getInstance();
                    int id=0;
                    try {
                        String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi modificare");
                        id=Integer.parseInt(idS);
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                    }

                    boolean trovato=false;
                    List<IArticolo> prodotti=prodottoDAO.findAll();
                    ListIterator<IArticolo> prodottiIterator=prodotti.listIterator();


                    while (prodottiIterator.hasNext()){
                        Prodotto prodotto = (Prodotto) prodottiIterator.next();

                        if(id == prodotto.getId() ){
                            trovato=true;

                            List<RigaCatalogo> righeCercate= new ArrayList<>();


                            RigaCatalogo riga=new RigaCatalogo();
                            riga.setIdProdotto(prodotto.getId());
                            riga.setNomeProdotto(prodotto.getNome());
                            riga.setNomeProduttore(prodotto.getProduttore().getNomeProduttore());
                            riga.setNomeCategoria(prodotto.getCategoria().getNome());
                            riga.setPrezzo(prodotto.getPrezzo());
                            riga.setProdottoComposito("No");
                            riga.setProdotti("//");

                            righeCercate.add(riga);


                            JFrame finestra=creaFinestra();
                            finestra.setTitle("Riepilogo modifica articolo");
                            finestra.add(new JLabel("Schieda articolo"), BorderLayout.NORTH);
                            SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCercate);
                            JTable tabella = new JTable(tableModel);

                            tabella.setRowHeight(100);



                            JScrollPane scrollPane = new JScrollPane(tabella);
                            finestra.add(scrollPane, BorderLayout.CENTER);

                            finestra.setVisible(true);
                            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            JButton modifica=new JButton("Modifica Manualmente");
                            finestra.add(modifica,BorderLayout.SOUTH);
                            modifica.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    String[] optionsToChoose = {"ID", "Nome", "Produttore", "Categoria", "Prezzo","Immagine","Descrizione","Collocazione"};

                                    JFrame jFrame2 = new JFrame();

                                    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
                                    jComboBox.setBounds(80, 50, 140, 20);

                                    JButton jButton = new JButton("Done");
                                    jButton.setBounds(100, 100, 90, 20);

                                    JLabel jLabel = new JLabel();
                                    jLabel.setBounds(90, 100, 400, 100);

                                    jFrame2.add(jButton);
                                    jFrame2.add(jComboBox);
                                    jFrame2.add(jLabel);

                                    jFrame2.setLayout(null);
                                    jFrame2.setSize(350, 250);
                                    jFrame2.setVisible(true);

                                    jButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String campo =  jComboBox.getItemAt(jComboBox.getSelectedIndex());
                                            if("ID".equals(campo)){
                                                try {
                                                    String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi modificare");
                                                    int id=Integer.parseInt(idS);
                                                    boolean trovato= false;
                                                    List<IArticolo> prodotti=prodottoDAO.findAll();
                                                    ListIterator<IArticolo> prodottiIterator=prodotti.listIterator();
                                                    while (prodottiIterator.hasNext()){
                                                        Prodotto prodotto1=(Prodotto) prodottiIterator.next();
                                                        if (id == prodotto1.getId()){
                                                            trovato=true;
                                                        }
                                                    }
                                                    if(!trovato){
                                                        prodotto.setId(id);
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null,"L'ID inserito esiste già");
                                                        return;
                                                    }
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    prodottoDAO.update(prodotto);
                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                                                    return;
                                                }
                                            }
                                            else if ("Nome".equals(campo)){
                                                String nome= JOptionPane.showInputDialog(null,"Inserisci il nome del prodotto che vuoi modificare");
                                                if(nome == null || "".equals(nome))
                                                    return;
                                                prodotto.setNome(nome);
                                                JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                prodottoDAO.update(prodotto);
                                            }
                                            else if("Produttore".equals(campo)){
                                                String nomeP= JOptionPane.showInputDialog(null,"Inserisci il produttore del prodotto che vuoi modificare");
                                                if(nomeP == null || "".equals(nomeP))
                                                    return;

                                                IProduttore produttoreI=produttoreDAO.findByName(nomeP);
                                                ProduttoreMobili produttore=(ProduttoreMobili) produttoreI;
                                                if(produttoreI==null){
                                                    String message=JOptionPane.showInputDialog(null,"Il produttore non esiste, lo vuoi creare? Inserisici si per continuare oppure qualcos'altro per terminare");
                                                    if("si".equalsIgnoreCase(message)){
                                                        ProduttoreFactoryProvider.ProduttoreType typePr= ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
                                                        ProduttoreAbstractFactory factoryPr=ProduttoreFactoryProvider.getFactory(typePr);
                                                        ProduttoreMobili produttore1=(ProduttoreMobili) factoryPr.crea();
                                                        String nomeProduttore1=JOptionPane.showInputDialog(null,"Inserisci nome produttore");
                                                        if(nomeProduttore1 == null || "".equals(nomeProduttore1))
                                                            return;
                                                        produttore1.setNomeProduttore(nomeProduttore1);
                                                        String nomeCitta=JOptionPane.showInputDialog(null,"Inserisci nome Città");
                                                        if(nomeCitta == null || "".equals(nomeCitta))
                                                            return;
                                                        produttore1.setCitta(nomeCitta);
                                                        String nazione=JOptionPane.showInputDialog(null,"Inserisci nazione");
                                                        if(nazione == null || "".equals(nazione))
                                                            return;
                                                        produttore1.setNazione(nazione);
                                                        String url=JOptionPane.showInputDialog(null,"Inserisci URL");
                                                        if(url == null || "".equals(url))
                                                            return;
                                                        produttore1.setSitoWeb(url);
                                                        prodotto.setProduttore(produttore1);
                                                        produttoreI= produttore1;
                                                        produttoreDAO.add(produttoreI);
                                                        prodottoDAO.update(prodotto);
                                                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    }else {
                                                        return;
                                                    }
                                                }else {
                                                    prodotto.setProduttore(produttore);
                                                    prodottoDAO.update(prodotto);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }

                                            }
                                            else if("Categoria".equals(campo)){
                                                String nomeC= JOptionPane.showInputDialog(null,"Inserisci la categoria del prodotto che vuoi modificare");
                                                if(nomeC == null || "".equals(nomeC))
                                                    return;
                                                ICategoria categoriaI=categoriaDAO.findByName(nomeC);
                                                CategoriaProdotto categoriaProdotto=(CategoriaProdotto) categoriaI;
                                                if(categoriaI==null){
                                                    String message=JOptionPane.showInputDialog(null,"La categoria non esiste, la vuoi creare? Inserisici si per continuare oppure qualcos'altro per terminare");
                                                    if("si".equalsIgnoreCase(message)){
                                                        CategoriaFactoryProvider.CategoriaFactoryType typeC= CategoriaFactoryProvider.CategoriaFactoryType.PRODOTTO;
                                                        CategoriaAbstractFactory factoryC=CategoriaFactoryProvider.getFactory(typeC);
                                                        CategoriaProdotto categoriaProdotto1=(CategoriaProdotto) factoryC.crea();
                                                        String nomeCategoria1=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
                                                        if(nomeCategoria1== null || "".equals(nomeCategoria1))
                                                            return;
                                                        categoriaProdotto1.setNome(nomeCategoria1);
                                                        prodotto.setCategoria(categoriaProdotto1);
                                                        categoriaDAO.add(categoriaProdotto1);
                                                        prodottoDAO.update(prodotto);
                                                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    }else {
                                                        return;
                                                    }
                                                }else {
                                                    prodotto.setCategoria(categoriaProdotto);
                                                    prodottoDAO.update(prodotto);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }



                                            }
                                            else if ("Prezzo".equals(campo)){
                                                try {
                                                    String pS= JOptionPane.showInputDialog(null,"Inserisci il prezzo del prodotto che vuoi modificare");
                                                    float prezzo=Float.parseFloat(pS);
                                                    prodotto.setPrezzo(prezzo);
                                                    prodottoDAO.update(prodotto);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"Il prezzo deve essere un numero");
                                                    return;
                                                }
                                            }
                                            else if ("Immagine".equals(campo)) {
                                                String imageTi=JOptionPane.showInputDialog(null,"Inserisci titolo dell'immagine");
                                                String image2="/"+imageTi+".jpg";
                                                InputStream stream2 = getClass().getResourceAsStream(image2);
                                                System.out.println(image2);
                                                try {
                                                    ImageIcon icon2 = new ImageIcon(ImageIO.read(stream2));
                                                    riga.setImage(icon2);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                } catch (IOException exception) {
                                                    JOptionPane.showMessageDialog(null,"Errore l'immagine non è inserita nella cartella references o il titolo è sbagliato");
                                                    exception.printStackTrace();
                                                    return;

                                                }
                                                finestra.repaint();
                                                finestra.revalidate();
                                            }else if ("Descrizione".equals(campo)){
                                                String descrizione=JOptionPane.showInputDialog(null,"Inserisci la nuova descrizione");
                                                if(descrizione == null || "".equals(descrizione))
                                                    return;
                                                prodotto.setDescrizione(descrizione);
                                                prodottoDAO.update(prodotto);
                                            }else if("Collocazione".equalsIgnoreCase(campo)){
                                                String corsia=JOptionPane.showInputDialog(null,"Inserisci nome corsia");
                                                if(corsia== null || "".equals(corsia))
                                                    return;
                                                int scaffale=0;
                                                try {
                                                    String scaffaleS=JOptionPane.showInputDialog(null,"Inserisci scaffale");
                                                    scaffale=Integer.parseInt(scaffaleS);
                                                    Collocazione collocazione=collocazioneDAO.findByCorsiaEScaffale(corsia,scaffale);
                                                    if(collocazione==null){
                                                        String message=JOptionPane.showInputDialog(null,"La collocazione inserita non esiste non esiste, la vuoi creare? Inserisci si per crearla o qualcos'altro per terminare");
                                                        if("si".equalsIgnoreCase(message)){
                                                            String corsia1=JOptionPane.showInputDialog(null,"Inserisci nome corsia");
                                                            if(corsia1 == null || "".equals(corsia1))
                                                                return;
                                                            try {
                                                                String scaffale1=JOptionPane.showInputDialog(null,"Inserisci scaffale");
                                                                int scaffale2=Integer.parseInt(scaffale1);
                                                                CollocazioneFactory.CollocazioneType type1= CollocazioneFactory.CollocazioneType.MAGAZZINO;
                                                                CollocazioneFactory factory1=new CollocazioneFactory();
                                                                CollocazioneProdotto collocazioneProdotto = (CollocazioneProdotto) factory1.getCollocazione(type1);
                                                                collocazioneProdotto.setCorsia(corsia1);
                                                                collocazioneProdotto.setScaffale(scaffale2);
                                                                CollocazioneDAO.getInstance().add(collocazioneProdotto);
                                                                prodotto.setCollocazione(collocazioneProdotto);
                                                                prodottoDAO.update(prodotto);
                                                            }catch (Exception exception){
                                                                JOptionPane.showMessageDialog(null,"Lo scaffale deve essere un numero intero");
                                                                return;
                                                            }
                                                        }else {
                                                            return;
                                                        }
                                                    }else {
                                                        prodotto.setCollocazione((CollocazioneProdotto) collocazione);
                                                        prodottoDAO.update(prodotto);
                                                    }

                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"Lo scaffale deve essere un numero intero");
                                                    return;
                                                }


                                            }
                                        }
                                    });
                                }
                            });

                            CatalogoPanel.getInstance().revalidate();
                            CatalogoPanel.getInstance().repaint();
                            break;

                        }

                    }
                    if(!trovato){
                        JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");
                    }


                } else if("servizio".equalsIgnoreCase(scelta)){

                    ServizioDAO servizioDAO=ServizioDAO.getInstance();

                    int id=0;
                    try {
                        String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del servizio che vuoi modificare");
                        id=Integer.parseInt(idS);
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                        return;
                    }
                    boolean trovato=false;
                    List<IArticolo> servizi=servizioDAO.findAll();
                    ListIterator<IArticolo> serviziIterator=servizi.listIterator();


                    while (serviziIterator.hasNext()){
                        Servizio servizio=(Servizio) serviziIterator.next();
                        if(servizio.getId() == id ){
                            trovato=true;

                            JFrame finestra=creaFinestra();
                            finestra.setTitle("Riepilogo moodifica articolo");
                            finestra.add(new JLabel("Schieda servizio: " + servizio.getNome()+ " Prezzo: " + servizio.getPrezzo()),BorderLayout.NORTH);

                            JButton mofifica=new JButton("Modifica manualmente");
                            finestra.add(mofifica,BorderLayout.SOUTH);
                            mofifica.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    String[] optionsToChoose = {"ID", "Nome", "Prezzo","Descrizione","Categoria"};

                                    JFrame jFrame2 = new JFrame();

                                    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
                                    jComboBox.setBounds(80, 50, 140, 20);

                                    JButton jButton = new JButton("Done");
                                    jButton.setBounds(100, 100, 90, 20);

                                    JLabel jLabel = new JLabel();
                                    jLabel.setBounds(90, 100, 400, 100);

                                    jFrame2.add(jButton);
                                    jFrame2.add(jComboBox);
                                    jFrame2.add(jLabel);

                                    jFrame2.setLayout(null);
                                    jFrame2.setSize(350, 250);
                                    jFrame2.setVisible(true);

                                    jButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String campo =  jComboBox.getItemAt(jComboBox.getSelectedIndex());

                                            if("ID".equalsIgnoreCase(campo)){
                                                try {
                                                    String idS=JOptionPane.showInputDialog(null,"Inserisci ID servizio");
                                                    int id=Integer.parseInt(idS);
                                                    boolean trovato= false;
                                                    List<IArticolo> servizi=servizioDAO.findAll();
                                                    ListIterator<IArticolo> serviziIterator=servizi.listIterator();
                                                    while (serviziIterator.hasNext()){
                                                        Servizio servizio1=(Servizio) serviziIterator.next();
                                                        if (id == servizio1.getId()){
                                                            trovato=true;
                                                        }
                                                    }
                                                    if(!trovato){
                                                        servizio.setId(id);
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null,"L'ID inserito esiste già");
                                                        return;
                                                    }

                                                }
                                                catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"Errore, prodotto non creato: L'ID del prodotto deve essere un numero intero");
                                                    return;
                                                }
                                                servizioDAO.update(servizio);

                                            }else if("Nome".equalsIgnoreCase(campo)){
                                                String nome=JOptionPane.showInputDialog(null,"Inserisci nome servizio");
                                                if(nome == null || "".equals(nome))
                                                    return;
                                                servizio.setNome(nome);
                                                servizioDAO.update(servizio);

                                            }else if("Prezzo".equalsIgnoreCase(campo)){
                                                try {
                                                    String pS= JOptionPane.showInputDialog(null,"Inserisci il prezzo del prodotto che vuoi modificare");
                                                    float prezzo=Float.parseFloat(pS);
                                                    servizio.setPrezzo(prezzo);
                                                    servizioDAO.update(servizio);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"Il prezzo deve essere un numero");
                                                    return;
                                                }

                                            }else if("Descrizione".equalsIgnoreCase(campo)){
                                                String descrizione=JOptionPane.showInputDialog(null,"Inserisci descrizione");
                                                if(descrizione == null || "".equals(descrizione))
                                                    return;
                                                servizio.setDescrizione(descrizione);
                                                servizioDAO.update(servizio);

                                            }else if("Categoria".equalsIgnoreCase(campo)){
                                                String nomeC= JOptionPane.showInputDialog(null,"Inserisci la categoria delservizio che vuoi modificare");
                                                if(nomeC == null || "".equals(nomeC))
                                                    return;
                                                ICategoria categoriaI=categoriaDAO.findByName(nomeC);
                                                CategoriaServizio categoriaServizio=(CategoriaServizio) categoriaI;
                                                if(categoriaI==null){
                                                    String message=JOptionPane.showInputDialog(null,"La categoria non esiste, la vuoi creare? Inserisici si per continuare oppure qualcos'altro per terminare");
                                                    if("si".equalsIgnoreCase(message)){
                                                        CategoriaFactoryProvider.CategoriaFactoryType typeC= CategoriaFactoryProvider.CategoriaFactoryType.SERVIZIO;
                                                        CategoriaAbstractFactory factoryC=CategoriaFactoryProvider.getFactory(typeC);
                                                        CategoriaServizio categoriaServizio1=(CategoriaServizio) factoryC.crea();
                                                        String nomeCategoria1=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
                                                        if(nomeCategoria1 == null || "".equals(nomeCategoria1))
                                                            return;
                                                        categoriaServizio1.setNome(nomeCategoria1);
                                                        servizio.setCategoria(categoriaServizio1);
                                                        categoriaDAO.add(categoriaServizio1);
                                                        servizioDAO.update(servizio);
                                                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    }else {
                                                        return;
                                                    }
                                                }else {
                                                    servizio.setCategoria(categoriaServizio);
                                                    servizioDAO.update(servizio);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }
                                            }

                                        }
                                    });

                                }
                            });

                            finestra.setVisible(true);
                            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            break;


                        }

                    }
                    if(!trovato){
                        JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");

                    }



                }/*else if ("Prodotto Composito".equals(scelta)){
                    ProdottoCompositoDAO prodottoCompositoDAO=ProdottoCompositoDAO.getInstance();
                    int id=0;
                    try {
                        String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi modificare");
                        id=Integer.parseInt(idS);
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                    }

                    boolean trovato=false;
                    List<IArticolo> prodotti=prodottoCompositoDAO.findAll();
                    ListIterator<IArticolo> prodottiIterator=prodotti.listIterator();


                    while (prodottiIterator.hasNext()){
                        ProdottoComposito prodottoComposito = (ProdottoComposito) prodottiIterator.next();

                        if(id == prodottoComposito.getId() ){
                            trovato=true;

                            List<RigaCatalogo> righeCercate= new ArrayList<>();
                            String visualizzaProdotti="";
                            ListIterator<IProdotto> prodottiInclusi=prodottoComposito.getSottoprodotti().listIterator();
                            while (prodottiInclusi.hasNext()){
                                Prodotto prodotto=(Prodotto) prodottiInclusi.next();
                                visualizzaProdotti=prodotto.getNome()+", ";
                            }


                            RigaCatalogo riga=new RigaCatalogo();
                            riga.setIdProdotto(prodottoComposito.getId());
                            riga.setNomeProdotto(prodottoComposito.getNome());
                            riga.setNomeProduttore(prodottoComposito.getProduttore().getNomeProduttore());
                            riga.setNomeCategoria(prodottoComposito.getCategoria().getNome());
                            riga.setPrezzo(prodottoComposito.getPrezzo());
                            riga.setProdottoComposito("Si");
                            riga.setProdotti(visualizzaProdotti);



                            righeCercate.add(riga);


                            JFrame finestra=creaFinestra();
                            finestra.setTitle("Riepilogo modifica articolo");
                            finestra.add(new JLabel("Schieda articolo"), BorderLayout.NORTH);
                            SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCercate);
                            JTable tabella = new JTable(tableModel);

                            tabella.setRowHeight(100);



                            JScrollPane scrollPane = new JScrollPane(tabella);
                            finestra.add(scrollPane, BorderLayout.CENTER);

                            finestra.setVisible(true);
                            finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            JButton modifica=new JButton("Modifica Manualmente");
                            finestra.add(modifica,BorderLayout.SOUTH);
                            modifica.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    String[] optionsToChoose = {"ID", "Nome", "Produttore", "Categoria", "Prezzo","Immagine","Descrizione","Collocazione"};

                                    JFrame jFrame2 = new JFrame();

                                    JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
                                    jComboBox.setBounds(80, 50, 140, 20);

                                    JButton jButton = new JButton("Done");
                                    jButton.setBounds(100, 100, 90, 20);

                                    JLabel jLabel = new JLabel();
                                    jLabel.setBounds(90, 100, 400, 100);

                                    jFrame2.add(jButton);
                                    jFrame2.add(jComboBox);
                                    jFrame2.add(jLabel);

                                    jFrame2.setLayout(null);
                                    jFrame2.setSize(350, 250);
                                    jFrame2.setVisible(true);

                                    jButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String campo =  jComboBox.getItemAt(jComboBox.getSelectedIndex());
                                            if("ID".equals(campo)){
                                                try {
                                                    String idS= JOptionPane.showInputDialog(null,"Inserisci l'ID del prodotto che vuoi modificare");
                                                    int id=Integer.parseInt(idS);
                                                    boolean trovato= false;
                                                    List<IArticolo> prodotti=prodottoCompositoDAO.findAll();
                                                    ListIterator<IArticolo> prodottiIterator=prodotti.listIterator();
                                                    while (prodottiIterator.hasNext()){
                                                        Prodotto prodotto1=(Prodotto) prodottiIterator.next();
                                                        if (id == prodotto1.getId()){
                                                            trovato=true;
                                                        }
                                                    }
                                                    if(!trovato){
                                                        prodottoComposito.setId(id);
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null,"L'ID inserito esiste già");
                                                        return;
                                                    }
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    prodottoCompositoDAO.update(prodottoComposito);
                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"L'ID deve essere un numero intero");
                                                    return;
                                                }
                                            }
                                            else if ("Nome".equals(campo)){
                                                String nome= JOptionPane.showInputDialog(null,"Inserisci il nome del prodotto che vuoi modificare");
                                                if(nome == null || "".equals(nome))
                                                    return;
                                                prodottoComposito.setNome(nome);
                                                JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                prodottoCompositoDAO.update(prodottoComposito);
                                            }
                                            else if("Produttore".equals(campo)){
                                                String nomeP= JOptionPane.showInputDialog(null,"Inserisci il produttore del prodotto che vuoi modificare");
                                                if(nomeP == null || "".equals(nomeP))
                                                    return;

                                                IProduttore produttoreI=produttoreDAO.findByName(nomeP);
                                                ProduttoreMobili produttore=(ProduttoreMobili) produttoreI;
                                                if(produttoreI==null){
                                                    String message=JOptionPane.showInputDialog(null,"Il produttore non esiste, lo vuoi creare? Inserisici si per continuare oppure qualcos'altro per terminare");
                                                    if("si".equalsIgnoreCase(message)){
                                                        ProduttoreFactoryProvider.ProduttoreType typePr= ProduttoreFactoryProvider.ProduttoreType.PRODUTTORE_MOBILI;
                                                        ProduttoreAbstractFactory factoryPr=ProduttoreFactoryProvider.getFactory(typePr);
                                                        ProduttoreMobili produttore1=(ProduttoreMobili) factoryPr.crea();
                                                        String nomeProduttore1=JOptionPane.showInputDialog(null,"Inserisci nome produttore");
                                                        if(nomeProduttore1 == null || "".equals(nomeProduttore1))
                                                            return;
                                                        produttore1.setNomeProduttore(nomeProduttore1);
                                                        String nomeCitta=JOptionPane.showInputDialog(null,"Inserisci nome Città");
                                                        if(nomeCitta == null || "".equals(nomeCitta))
                                                            return;
                                                        produttore1.setCitta(nomeCitta);
                                                        String nazione=JOptionPane.showInputDialog(null,"Inserisci nazione");
                                                        if(nazione == null || "".equals(nazione))
                                                            return;
                                                        produttore1.setNazione(nazione);
                                                        String url=JOptionPane.showInputDialog(null,"Inserisci URL");
                                                        if(url == null || "".equals(url))
                                                            return;
                                                        produttore1.setSitoWeb(url);
                                                        prodottoComposito.setProduttore(produttore1);
                                                        produttoreI= produttore1;
                                                        produttoreDAO.add(produttoreI);
                                                        prodottoCompositoDAO.update(prodottoComposito);
                                                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    }else {
                                                        return;
                                                    }
                                                }else {
                                                    prodottoComposito.setProduttore(produttore);
                                                    prodottoCompositoDAO.update(prodottoComposito);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }

                                            }
                                            else if("Categoria".equals(campo)){
                                                String nomeC= JOptionPane.showInputDialog(null,"Inserisci la categoria del prodotto che vuoi modificare");
                                                if(nomeC == null || "".equals(nomeC))
                                                    return;
                                                ICategoria categoriaI=categoriaDAO.findByName(nomeC);
                                                CategoriaProdotto categoriaProdotto=(CategoriaProdotto) categoriaI;
                                                if(categoriaI==null){
                                                    String message=JOptionPane.showInputDialog(null,"La categoria non esiste, la vuoi creare? Inserisici si per continuare oppure qualcos'altro per terminare");
                                                    if("si".equalsIgnoreCase(message)){
                                                        CategoriaFactoryProvider.CategoriaFactoryType typeC= CategoriaFactoryProvider.CategoriaFactoryType.PRODOTTO;
                                                        CategoriaAbstractFactory factoryC=CategoriaFactoryProvider.getFactory(typeC);
                                                        CategoriaProdotto categoriaProdotto1=(CategoriaProdotto) factoryC.crea();
                                                        String nomeCategoria1=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
                                                        if(nomeCategoria1 == null || "".equals(nomeCategoria1))
                                                            return;
                                                        categoriaProdotto1.setNome(nomeCategoria1);
                                                        prodottoComposito.setCategoria(categoriaProdotto1);
                                                        categoriaDAO.add(categoriaProdotto1);
                                                        prodottoCompositoDAO.update(prodottoComposito);
                                                        JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                    }else {
                                                        return;
                                                    }
                                                }else {
                                                    prodottoComposito.setCategoria(categoriaProdotto);
                                                    prodottoCompositoDAO.update(prodottoComposito);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }



                                            }
                                            else if ("Prezzo".equals(campo)){
                                                try {
                                                    String pS= JOptionPane.showInputDialog(null,"Inserisci il prezzo del prodotto che vuoi modificare");
                                                    float prezzo=Float.parseFloat(pS);
                                                    prodottoComposito.setPrezzo(prezzo);
                                                    prodottoCompositoDAO.update(prodottoComposito);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"Il prezzo deve essere un numero");
                                                    return;
                                                }
                                            }
                                            else if ("Immagine".equals(campo)) {
                                                String imageTi=JOptionPane.showInputDialog(null,"Inserisci titolo dell'immagine");
                                                String image2="/"+imageTi+".jpg";
                                                InputStream stream2 = getClass().getResourceAsStream(image2);
                                                System.out.println(image2);
                                                try {
                                                    ImageIcon icon2 = new ImageIcon(ImageIO.read(stream2));
                                                    riga.setImage(icon2);
                                                    JOptionPane.showMessageDialog(null,"Prodotto modificato con successo");
                                                } catch (IOException exception) {
                                                    JOptionPane.showMessageDialog(null,"Errore l'immagine non è inserita nella cartella references o il titolo è sbagliato");
                                                    exception.printStackTrace();
                                                    return;

                                                }
                                                finestra.repaint();
                                                finestra.revalidate();
                                            }else if ("Descrizione".equals(campo)){
                                                String descrizione=JOptionPane.showInputDialog(null,"Inserisci la nuova descrizione");
                                                prodottoComposito.setDescrizione(descrizione);
                                                prodottoCompositoDAO.update(prodottoComposito);
                                            }else if("Collocazione".equalsIgnoreCase(campo)){

                                                String corsia=JOptionPane.showInputDialog(null,"Inserisci nome corsia");
                                                if(corsia== null || "".equals(corsia))
                                                    return;
                                                int scaffale=0;
                                                try {
                                                    String scaffaleS=JOptionPane.showInputDialog(null,"Inserisci scaffale");
                                                    scaffale=Integer.parseInt(scaffaleS);
                                                    Collocazione collocazione=collocazioneDAO.findByCorsiaEScaffale(corsia,scaffale);
                                                    if(collocazione==null){
                                                        String message=JOptionPane.showInputDialog(null,"La collocazione inserita non esiste non esiste, la vuoi creare? Inserisci si per crearla o qualcos'altro per terminare");
                                                        if("si".equalsIgnoreCase(message)){
                                                            String corsia1=JOptionPane.showInputDialog(null,"Inserisci nome corsia");
                                                            if(corsia1 == null || "".equals(corsia1))
                                                                return;
                                                            try {
                                                                String scaffale1=JOptionPane.showInputDialog(null,"Inserisci scaffale");
                                                                int scaffale2=Integer.parseInt(scaffale1);
                                                                CollocazioneFactory.CollocazioneType type1= CollocazioneFactory.CollocazioneType.MAGAZZINO;
                                                                CollocazioneFactory factory1=new CollocazioneFactory();
                                                                CollocazioneProdotto collocazioneProdotto = (CollocazioneProdotto) factory1.getCollocazione(type1);
                                                                collocazioneProdotto.setCorsia(corsia1);
                                                                collocazioneProdotto.setScaffale(scaffale2);
                                                                CollocazioneDAO.getInstance().add(collocazioneProdotto);
                                                                prodottoComposito.setCollocazione(collocazioneProdotto);
                                                                prodottoCompositoDAO.update(prodottoComposito);
                                                            }catch (Exception exception){
                                                                JOptionPane.showMessageDialog(null,"Lo scaffale deve essere un numero intero");
                                                                return;
                                                            }
                                                        }else {
                                                            return;
                                                        }
                                                    }else {
                                                        prodottoComposito.setCollocazione((CollocazioneProdotto) collocazione);
                                                        prodottoCompositoDAO.update(prodottoComposito);
                                                    }

                                                }catch (Exception exception){
                                                    JOptionPane.showMessageDialog(null,"Lo scaffale deve essere un numero intero");
                                                    return;
                                                }


                                            }
                                        }
                                    });
                                }
                            });

                            CatalogoPanel.getInstance().revalidate();
                            CatalogoPanel.getInstance().repaint();
                            break;

                        }

                    }
                    if(!trovato){
                        JOptionPane.showMessageDialog(null,"L'ID inserito non esiste");
                    }
















                }
                */

            }
        });

    }


    public JFrame creaFinestra(){
        JPanel nord=new JPanel();
        JPanel sud=new JPanel();
        JPanel east=new JPanel();
        JPanel west=new JPanel();
        JPanel centro=new JPanel();

        nord.setLayout(new FlowLayout());
        sud.setLayout(new FlowLayout());
        east.setLayout(new FlowLayout());
        west.setLayout(new FlowLayout());
        centro.setLayout(new FlowLayout());

        JFrame finestra=new JFrame();
        finestra.setSize(1000,700);
        Container c=finestra.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(nord,BorderLayout.NORTH);
        c.add(sud,BorderLayout.SOUTH);
        c.add(east,BorderLayout.EAST);
        c.add(west,BorderLayout.WEST);
        c.add(centro,BorderLayout.CENTER);
        return finestra;

    }

}
