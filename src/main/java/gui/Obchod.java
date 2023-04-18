package gui;
//přes maven se dá vložit závislot na externího knihovně gson-json buď ručně, nebo sama Idea v "dependencies" v pom.xml
import model.Sklad;
import serdes.GsonSerDes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//CO DODĚLAT: tlačitka, ukladani - ukladani, nacitani s .csv ručně
public class Obchod {     //Přidat panel tlačítek s tlačítkama pro přidávání a odebrání položek
    private Sklad sklad;
    private JPanel hlavniPanel;
    private JPanel panelSkladu;
    private JTable tabulkaSkladu;
    private JMenuBar nabidka;

    public Obchod() {
        sklad = new Sklad();
        Docasne.napln(sklad);
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
        hlavniPanel = new JPanel();
        hlavniPanel.add(panelSkladu);
    }

    private void vytvorNabidku() {
        JMenuItem miUkoncit = new JMenuItem();
        miUkoncit.setText("Ukončit");
        miUkoncit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem miOProgramu = new JMenuItem("O Programu");
        miOProgramu.addActionListener((e) -> {
            JOptionPane.showMessageDialog(hlavniPanel,"O programu\nJednoduchý simulátor e-shopu","O programu", JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem miUlozit = new JMenuItem("Uložit");
        miUlozit.addActionListener((evt) -> {
            try {
                sklad.uloz(new GsonSerDes(), "sklad.json");
            } catch (Exception e) {
                // Obsluha - např dialog
            }
        });

        JMenuItem miNactist = new JMenuItem("Nacti"); //dodělat nacti


        JMenu mnSoubor = new JMenu();
        mnSoubor.setText("Soubor");
        mnSoubor.add(miUkoncit);
        mnSoubor.add(miUlozit);

        JMenu mnNapoveda = new JMenu("Nápověda");
        mnNapoveda.add(miOProgramu);

        nabidka = new JMenuBar();
        nabidka.add(mnSoubor); //přidání menu
        nabidka.add(mnNapoveda);
    }

    private void vytvorPanelSkladu() {
        tabulkaSkladu = new JTable();
        tabulkaSkladu.setModel(sklad);
        tabulkaSkladu.setFillsViewportHeight(true); //aby vyplnila tabulka celý prostor
        tabulkaSkladu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //možnost vybírat pouze 1 položku


        JScrollPane spTabulka = new JScrollPane(tabulkaSkladu);

        panelSkladu = new JPanel();
        panelSkladu.add(spTabulka);
    }

    public static void vytvorHlavniOkno() {
        JFrame hlavniOkno = new JFrame();
        hlavniOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hlavniOkno.setTitle("Obchod");
        hlavniOkno.setLocationByPlatform(true);

        Obchod obchod = new Obchod();
        hlavniOkno.setContentPane(obchod.getHlavniPanel());
        hlavniOkno.setJMenuBar(obchod.getNabidka());


        hlavniOkno.pack();
        hlavniOkno.setVisible(true);

    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> vytvorHlavniOkno());
    }
}
