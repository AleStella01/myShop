package View.Listener;

import DAO.*;
import Model.*;
import View.CatalogoPanel;
import View.SchiedaArticoloTableModel;
import View.ViewModel.RigaCatalogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AggiungiArticoloListener implements ActionListener {

    public final static String AGGIUNGI = "Aggiungi prodotto";
    public final static String RIMUOVI = "Rimuovi prodotto";
    public final static String MODIFICA= "Modifica prodotto";




    @Override
    public void actionPerformed(ActionEvent e) {

        ProduttoreDAO produttoreDAO=ProduttoreDAO.getInstance();
        CategoriaDAO categoriaDAO=CategoriaDAO.getInstance();
        CollocazioneDAO collocazioneDAO=CollocazioneDAO.getInstance();
        String action = e.getActionCommand();
        if(AGGIUNGI.equals(action)){

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

            jFrame.setTitle("Seleziona l'articolo che vuoi aggiungere");

            jFrame.setLayout(null);
            jFrame.setSize(350, 250);
            jFrame.setVisible(true);

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String scelta= jComboBox.getItemAt(jComboBox.getSelectedIndex());
                    if("Prodotto".equalsIgnoreCase(scelta)){
                        ArticoloFactoryProvider.ArticoloType type= ArticoloFactoryProvider.ArticoloType.PRODOTTO;
                        ArticoloAbstractFactory factory= ArticoloFactoryProvider.getFactory(type);
                        ProdottoDAO prodottoDAO=ProdottoDAO.getInstance();
                        Prodotto prodotto=(Prodotto) factory.crea();

                        String nome=JOptionPane.showInputDialog(null,"Inserisci nome prodotto");
                        if(nome == null || "".equals(nome))
                            return;
                        prodotto.setNome(nome);
                        String nomeProduttore=JOptionPane.showInputDialog(null,"Inserisci nome produttore");
                        if(nomeProduttore == null || "".equals(nomeProduttore))
                            return;
                        IProduttore produttoreI=produttoreDAO.findByName(nomeProduttore);
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
                                prodotto.setProduttore(produttore);
                                produttoreI= produttore1;
                                produttoreDAO.add(produttoreI);
                            }else {
                                return;
                            }
                        }else {
                            prodotto.setProduttore(produttore);
                        }

                        String nomeCategoria=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
                        if(nomeCategoria == null || "".equals(nomeCategoria))
                            return;

                        ICategoria categoriaI=categoriaDAO.findByName(nomeCategoria);
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
                                prodotto.setCategoria(categoriaProdotto1);
                                categoriaDAO.add(categoriaProdotto1);

                            }else {
                                return;
                            }
                        }else {
                            prodotto.setCategoria(categoriaProdotto);
                        }

                        try {
                            String prezzoS=JOptionPane.showInputDialog(null,"Inserisci prezzo");
                            Float prezzo=Float.parseFloat(prezzoS);
                            prodotto.setPrezzo(prezzo);
                        }
                        catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"Errore, prodotto non creato: Il prezzo deve essere un numero");
                            return;
                        }

                        String descrizione=JOptionPane.showInputDialog(null,"Inserisci descrizione del prodotto");
                        if(descrizione == null || "".equals(descrizione))
                            return;
                        prodotto.setDescrizione(descrizione);

                        String corsia=JOptionPane.showInputDialog(null,"Inserisci nome corsia");
                        if(corsia == null || "".equals(corsia))
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
                                    }catch (Exception exception){
                                        JOptionPane.showMessageDialog(null,"Lo scaffale deve essere un numero intero");
                                        return;
                                    }

                                }else {
                                    return;
                                }
                            }else {
                                prodotto.setCollocazione((CollocazioneProdotto) collocazione);
                            }
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"Lo scaffale deve essere un numero intero");
                            return;
                        }


                        String imageT=JOptionPane.showInputDialog(null,"Inserisci titolo dell'immagine");
                        String image="/"+imageT+".jpg";

//                        ImageIcon icon = null;
//                        JFileChooser chooser = new JFileChooser();
//                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                                "JPG & GIF Images", "jpg", "gif");
//                        chooser.setFileFilter(filter);
//                        int returnVal = chooser.showOpenDialog(null);
//                        if(returnVal == JFileChooser.APPROVE_OPTION) {
//                            InputStream stream = getClass().getResourceAsStream(chooser.getSelectedFile().getPath());
//                            try {
//                                 icon = new ImageIcon(ImageIO.read(stream));
//                            } catch (IOException exception) {
//                                JOptionPane.showMessageDialog(null,"Errore l'immagine non è inserita nella cartella references o il titolo è sbagliato");
//                                exception.printStackTrace();
//                                return;
//                            }
//                        }



                        RigaCatalogo riga1 = new RigaCatalogo();
                        riga1.setIdProdotto(prodotto.getId());
                        riga1.setNomeProdotto(prodotto.getNome());
                        riga1.setNomeProduttore(prodotto.getProduttore().getNomeProduttore());
                        riga1.setNomeCategoria(prodotto.getCategoria().getNome());
                        riga1.setPrezzo(prodotto.getPrezzo());
                        riga1.setProdottoComposito("No");
                        riga1.setProdotti("//");
                        InputStream stream = getClass().getResourceAsStream(image);
                        try {
                            ImageIcon icon = new ImageIcon(ImageIO.read(stream));
                            riga1.setImage(icon);
                        } catch (IOException exception) {
                            //throw new RuntimeException(e);
                            JOptionPane.showMessageDialog(null,"Errore l'immagine non eè inserita nella cartella references o il titolo è sbagliato");
                            exception.printStackTrace();
                            return;

                        }
                        //riga1.setImage(icon);


                        riga1.setCorsia(corsia);
                        riga1.setScaffale(scaffale);

                        List<RigaCatalogo> righeCreate= new ArrayList<>();
                        righeCreate.add(riga1);

                        CatalogoPanel.getInstance().revalidate();
                        CatalogoPanel.getInstance().repaint();
                        JFrame finestra=creaFinestra();
                        finestra.setTitle("Riepilogo creazione articolo");
                        finestra.add(new JLabel("Anteprima articolo creato"),BorderLayout.NORTH);
                        SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCreate);
                        JTable tabella = new JTable(tableModel);

                        tabella.setRowHeight(100);



                        JScrollPane scrollPane = new JScrollPane(tabella);
                        finestra.add(scrollPane, BorderLayout.CENTER);
                        JButton ok=new JButton("Ok");
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null,"Prodotto creato e inserito nel catalogo correttamente");
                                prodottoDAO.add(prodotto);

                            }
                        });
                        finestra.add(ok,BorderLayout.SOUTH);
                        finestra.setVisible(true);
                        finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



                    }else if("Servizio".equalsIgnoreCase(scelta)){
                        ArticoloFactoryProvider.ArticoloType type= ArticoloFactoryProvider.ArticoloType.SERVIZIO;
                        ArticoloAbstractFactory factory= ArticoloFactoryProvider.getFactory(type);
                        ServizioDAO servizioDAO=ServizioDAO.getInstance();
                        Servizio servizio=(Servizio) factory.crea();
                        String nomeServizio=JOptionPane.showInputDialog(null,"Inserisci nome del servizio");
                        if(nomeServizio == null || "".equals(nomeServizio))
                            return;
                        servizio.setNome(nomeServizio);
                        try {
                            String prezzoServizioString=JOptionPane.showInputDialog(null,"Inserisci prezzo");
                            float prezzo=Float.parseFloat(prezzoServizioString);
                            servizio.setPrezzo(prezzo);
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"Il prezzo deve essere un numero");
                            return;
                        }

                        String nomeCategoria=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
                        if(nomeCategoria == null || "".equals(nomeCategoria))
                            return;

                        ICategoria categoriaI=categoriaDAO.findByName(nomeCategoria);
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


                            }else {
                                return;
                            }
                        }else {
                            servizio.setCategoria(categoriaServizio);
                        }


                        String descrizione=JOptionPane.showInputDialog(null,"Inserisci la descrizione del servizio");
                        if(descrizione == null || "".equals(descrizione))
                            return;
                        servizio.setDescrizione(descrizione);

                        servizioDAO.add(servizio);
                        JOptionPane.showMessageDialog(null,"Servizio: " +servizio.getNome() + " Prezzo: " + servizio.getPrezzo() + " creato correttamente");

                    }
                    else if("Prodotto Composito".equals(scelta)){

                        ArticoloFactoryProvider.ArticoloType type= ArticoloFactoryProvider.ArticoloType.PRODOTTO_COMPOSITO;
                        ArticoloAbstractFactory factory= ArticoloFactoryProvider.getFactory(type);
                        ProdottoDAO prodottoDAO=ProdottoDAO.getInstance();
                        ProdottoComposito prodottoComposito=(ProdottoComposito) factory.crea();

                        String nome=JOptionPane.showInputDialog(null,"Inserisci nome prodotto");
                        if(nome == null || "".equals(nome))
                            return;
                        prodottoComposito.setNome(nome);
                        String nomeCategoria=JOptionPane.showInputDialog(null,"Inserisci nome categoria");
                        if(nomeCategoria == null || "".equals(nomeCategoria))
                            return;

                        ICategoria categoriaI=categoriaDAO.findByName(nomeCategoria);
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

                            }else {
                                return;
                            }
                        }else {
                            prodottoComposito.setCategoria(categoriaProdotto);
                        }

                        String descrizione=JOptionPane.showInputDialog(null,"Inserisci descrizione del prodotto");
                        if(descrizione == null || "".equals(descrizione))
                            return;
                        prodottoComposito.setDescrizione(descrizione);

                        boolean fine=false;
                        int i=0;
                        while (!fine){
                            try {
                                String idS=JOptionPane.showInputDialog(null,"Inserisci id del prodotto da inserire nel catalogo o fine per fermare l'inserimento");
                                if("fine".equalsIgnoreCase(idS)){
                                    fine=true;
                                }else {
                                    int id=Integer.parseInt(idS);
                                    Prodotto prodottoCercato= (Prodotto) prodottoDAO.findById(id);
                                    if(prodottoCercato==null){
                                        JOptionPane.showMessageDialog(null,"Il prodotto non esiste");
                                    }else {
                                        prodottoComposito.add(prodottoCercato);
                                    }

                                }

                            }catch (Exception exception){
                                JOptionPane.showMessageDialog(null,"Errore: l'id deve essere un numero intero ");
                            }

                        }

                        String imageT=JOptionPane.showInputDialog(null,"Inserisci titolo dell'immagine");
                        String image="/"+imageT+".jpg";


                        String visualizzaProdotti="";
                        ListIterator<IProdotto> prodottiInclusi=prodottoComposito.getSottoprodotti().listIterator();
                        while (prodottiInclusi.hasNext()){
                            Prodotto prodotto=(Prodotto) prodottiInclusi.next();
                            visualizzaProdotti=prodotto.getNome()+", ";
                        }

                        RigaCatalogo riga1 = new RigaCatalogo();
                        riga1.setIdProdotto(prodottoComposito.getId());
                        riga1.setNomeProdotto(prodottoComposito.getNome());
                        //riga1.setNomeProduttore(prodottoComposito.getProduttore().getNomeProduttore());
                        riga1.setNomeCategoria(prodottoComposito.getCategoria().getNome());
                        riga1.setPrezzo(prodottoComposito.getPrezzo());
                        riga1.setProdottoComposito("SI");
                        riga1.setProdotti(visualizzaProdotti);
                        InputStream stream = getClass().getResourceAsStream(image);
                        try {
                            ImageIcon icon = new ImageIcon(ImageIO.read(stream));
                            riga1.setImage(icon);
                        } catch (IOException exception) {
                            JOptionPane.showMessageDialog(null,"Errore l'immagine non è inserita nella cartella references o il titolo è sbagliato");
                            exception.printStackTrace();
                            return;

                        }


                        riga1.setNomeProduttore("MyShop");
                        riga1.setCorsia("//");
                        riga1.setScaffale(0);

                        List<RigaCatalogo> righeCreate= new ArrayList<>();
                        righeCreate.add(riga1);

                        CatalogoPanel.getInstance().revalidate();
                        CatalogoPanel.getInstance().repaint();
                        JFrame finestra=creaFinestra();
                        finestra.setTitle("Riepilogo creazione articolo");
                        finestra.add(new JLabel("Anteprima articolo creato"),BorderLayout.NORTH);
                        SchiedaArticoloTableModel tableModel = new SchiedaArticoloTableModel(righeCreate);
                        JTable tabella = new JTable(tableModel);

                        tabella.setRowHeight(100);



                        JScrollPane scrollPane = new JScrollPane(tabella);
                        finestra.add(scrollPane, BorderLayout.CENTER);
                        JButton ok=new JButton("Ok");
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null,"Prodotto creato e inserito nel catalogo correttamente");
                                ProdottoCompositoDAO.getInstance().add(prodottoComposito);
                                ArrayList<IArticolo> listaProdotto = ProdottoCompositoDAO.getInstance().findAll();
                                prodottoComposito.setId(listaProdotto.get(listaProdotto.size() - 1).getId());
                                ListIterator<IProdotto> prodottiIterator = prodottoComposito.getSottoprodotti().listIterator();
                                while (prodottiIterator.hasNext()){
                                    ProdottoCompositoDAO.getInstance().addProdotto(prodottoComposito, prodottiIterator.next());
                                }
                            }
                        });
                        finestra.add(ok,BorderLayout.SOUTH);
                        finestra.setVisible(true);
                        finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                    }

                }


            });




        }




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
