package gui;
//přes maven se dá vložit závislot na externího knihovně gson-json buď ručně, nebo sama Idea v "dependencies" v pom.xml
import model.Sklad;
import model.Zbozi;
import serdes.GsonSerDes;

import javax.swing.*;
import java.nio.file.Path;

//CO DODĚLAT: tlačitka, ukladani json + ukladani, nacitani s .csv ručně
public class Obchod {     //Přidat panel tlačítek s tlačítkama pro přidávání a odebrání položek
    private final Sklad sklad;
    private JPanel hlavniPanel;
    private JPanel panelSkladu;
    private JTable tabulkaSkladu;
    private JMenuBar nabidka;
    private JPanel panelTlacitek;
    private final Path path;

    public Obchod() {
        sklad = new Sklad();
        path = Path.of(System.getProperty("user.home"), "IdeaProjects\\pro1_obchod", "sklad.csv");
        try{
            sklad.nacti(new GsonSerDes(), "sklad.json");
        } catch (Exception e){
            JOptionPane.showMessageDialog(hlavniPanel,"Nepodařil se načíst soubor s daty", "Chyba", JOptionPane.ERROR_MESSAGE);
        }

        //Docasne.napln(sklad);
        vytvorKomponenty();
    }

    public JPanel getHlavniPanel() {
        return hlavniPanel;
    }

    public JMenuBar getNabidka() {
        return nabidka;
    }

    private void vytvorKomponenty() {
        vytvorNabidku();
        vytvorPanelSkladu();
        vytvorPanelTlacitek();
        hlavniPanel = new JPanel();
        hlavniPanel.add(panelSkladu);
        hlavniPanel.add(panelTlacitek);
    }

    private void vytvorNabidku() {
        JMenuItem miUkoncit = new JMenuItem();
        miUkoncit.setText("Ukončit");
        miUkoncit.addActionListener(e -> System.exit(0));

        JMenuItem miOProgramu = new JMenuItem("O Programu");
        miOProgramu.addActionListener((e) ->
                JOptionPane.showMessageDialog(hlavniPanel,"O programu\nJednoduchý simulátor e-shopu","O programu", JOptionPane.INFORMATION_MESSAGE));

        JMenuItem miUlozit = new JMenuItem("Uložit do .json");
        miUlozit.addActionListener((evt) -> {
            try {
                sklad.uloz(new GsonSerDes(), "sklad.json");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(hlavniPanel, "Při ukládání souboru se vyskytla chyba", "Chyba", JOptionPane.WARNING_MESSAGE);
            }
        });

        JMenuItem miNacist = new JMenuItem("Načíst z .json"); //dodělat nacti
        miNacist.addActionListener((evt) -> {
            try {
                sklad.nacti(new GsonSerDes(), "sklad.json");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(hlavniPanel, "Při načítání souboru se vyskytla chyba", "Chyba", JOptionPane.WARNING_MESSAGE);
            }
        });

        JMenuItem miUlozitCsv = new JMenuItem("Uložit do .csv");
        miUlozitCsv.addActionListener(evt -> {
            try {
                sklad.ulozCsv(path);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(hlavniPanel, "Při ukládání souboru se vyskytla chyba", "Chyba", JOptionPane.WARNING_MESSAGE);
            }
        });

        JMenuItem miNacistCsv = new JMenuItem("Načíst z .csv");
        miNacistCsv.addActionListener(evt -> {
            try {
                sklad.nactiCsv(path);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(hlavniPanel, "Při načítání souboru se vyskytla chyba", "Chyba", JOptionPane.WARNING_MESSAGE);
            }
        });

        JMenu mnSoubor = new JMenu();
        mnSoubor.setText("Soubor");
        mnSoubor.add(miUkoncit);
        mnSoubor.add(miUlozit);
        mnSoubor.add(miNacist);
        mnSoubor.add(miUlozitCsv);
        mnSoubor.add(miNacistCsv);

        JMenu mnNapoveda = new JMenu("Nápověda");
        mnNapoveda.add(miOProgramu);

        nabidka = new JMenuBar();
        nabidka.add(mnSoubor); //přidání menu
        nabidka.add(mnNapoveda);
    }

    private void vytvorPanelSkladu() {
        tabulkaSkladu = new JTable();
        tabulkaSkladu.setModel(sklad);
        tabulkaSkladu.setFillsViewportHeight(true);
        tabulkaSkladu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //možnost vybírat pouze 1 položku


        JScrollPane spTabulka = new JScrollPane(tabulkaSkladu);

        panelSkladu = new JPanel();
        panelSkladu.add(spTabulka);
    }

    private void vytvorPanelTlacitek() {
        Box box = Box.createVerticalBox();
        JButton btPridej = new JButton();

        btPridej.setText("Přidat");
        btPridej.addActionListener(e -> sklad.pridejZbozi(new Zbozi("", 0, 0)));

        JButton btOdeber = new JButton();
        btOdeber.setText("Odebrat");
        btOdeber.addActionListener(e -> {
            int pocetPolozek = tabulkaSkladu.getRowCount();
            int radek = tabulkaSkladu.getSelectedRow();
            if (radek > -1) {
                sklad.smazZbozi(radek);
            }
            else if (pocetPolozek <= 0){
                JOptionPane.showMessageDialog(hlavniPanel, "Sklad je už prázdný", "Informace", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(hlavniPanel, "Nebyla vybraná žádná položka k odebrání", "Informace", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btSmazVse = new JButton();
        btSmazVse.setText("Smazat vše");
        btSmazVse.addActionListener(e -> {
            int pocetPolozek = tabulkaSkladu.getRowCount();
            if (pocetPolozek > 0) {
                sklad.smazVsechnoZbozi();
            }
            else {
                JOptionPane.showMessageDialog(hlavniPanel, "Sklad je už prázdný", "Informace", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        box.add(btPridej);
        box.add(btOdeber);
        box.add(btSmazVse);

        panelTlacitek = new JPanel();
        panelTlacitek.add(box);

    }
    public static void vytvorHlavniOkno() {
        JFrame hlavniOkno = new JFrame();
        hlavniOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hlavniOkno.setTitle("Obchod");
        hlavniOkno.setLocationByPlatform(true);
        hlavniOkno.setResizable(false);

        Obchod obchod = new Obchod();
        hlavniOkno.setContentPane(obchod.getHlavniPanel());
        hlavniOkno.setJMenuBar(obchod.getNabidka());

        hlavniOkno.pack();
        hlavniOkno.setVisible(true);

    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(Obchod::vytvorHlavniOkno);
    }
}
