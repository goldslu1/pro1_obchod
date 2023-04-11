package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Obchod {

    private JPanel hlavniPanel;
    private JMenuBar nabidka;

    public Obchod() {
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
        hlavniPanel = new JPanel();
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

        JMenu mnSoubor = new JMenu();
        mnSoubor.setText("Soubor");
        mnSoubor.add(miUkoncit);

        JMenu mnNapoveda = new JMenu("Nápověda");
        mnNapoveda.add(miOProgramu);

        nabidka = new JMenuBar();
        nabidka.add(mnSoubor); //přidání menu
        nabidka.add(mnNapoveda);
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
