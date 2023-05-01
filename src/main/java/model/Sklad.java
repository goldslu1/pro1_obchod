package model;

import serdes.SerDes;

import javax.swing.table.AbstractTableModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Sklad extends AbstractTableModel { //tímto zdědíme sklad, musi se naimpletmentovat metody
    private static final int POCET_SLOUPCU = 3;
    private static final int SLOUPEC_NAZEV = 0;
    private static final int SLOUPEC_CENA = 1;
    private static final int SLOUPEC_POCET = 2;

    private static final String[] nazvysloupcu= {"Název", "Cena", "Počet"};

    private List<Zbozi> seznamZbozi;

    public Sklad() {
        seznamZbozi = new ArrayList<>();
    }

    public void pridejZbozi(Zbozi zbozi){
        seznamZbozi.add(zbozi);
        fireTableDataChanged();
    }
    public Zbozi getZbozi(int index){
        Zbozi zbozi = null;
        if (index >= 0){
            zbozi = seznamZbozi.get(index);
        }
        return zbozi;
    }
    public void smazZbozi(int index) {
        seznamZbozi.remove(index);
        fireTableDataChanged();
    }
    public void smazVsechnoZbozi() {
        seznamZbozi.clear();
        fireTableDataChanged();
    }

    public void nacti(SerDes serdes, String soubor) throws IOException { //použití interface
        seznamZbozi = serdes.nacti(soubor);
        fireTableDataChanged();
    }

    public void uloz(SerDes serdes, String soubor) throws IOException{ //kontrolovaná výjimka, vždy se musi nejak zabezpecit, pomoci throw ji predavame dal
        serdes.uloz(soubor, seznamZbozi);
    }

    public void ulozCsv(Path soubor) throws IOException {
        Files.writeString(soubor, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        for (Zbozi zbozi : seznamZbozi) {
            String radek = zbozi.getNazev() + ";" + zbozi.getCena() + ";" + zbozi.getPocet() + System.lineSeparator();
            Files.writeString(soubor, radek, StandardOpenOption.APPEND);
        }
    }

    public void nactiCsv(Path soubor) throws IOException {
        seznamZbozi.clear();
        for (String radek : Files.readAllLines(soubor)) {
            String[] rozdeleno = radek.split(";");
            pridejZbozi(new Zbozi(rozdeleno[0], Float.parseFloat(rozdeleno[1]), Integer.parseInt(rozdeleno[2])));
        }
    }

    @Override
    public int getRowCount() {
        return seznamZbozi.size();
    }

    @Override
    public int getColumnCount() { //počet sloupců k vytvoření
        return POCET_SLOUPCU;
    }
    @Override
    public String getColumnName(int column) { //změní názvy sloupců
        return nazvysloupcu[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) { //vrátí class literál podle sloupce prvku
        switch (columnIndex) {
            case SLOUPEC_NAZEV -> {
                return String.class;
            }
            case SLOUPEC_CENA -> {
                return Float.class;
            }
            case SLOUPEC_POCET -> {
                return Integer.class;
            }
            default -> throw new IllegalArgumentException("Nesprávný sloupec skladu");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // zde říkáme jestli se dá buňka měnit, editovat
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zbozi zbozi = seznamZbozi.get(rowIndex);
        switch (columnIndex) {
            case SLOUPEC_NAZEV -> {
                return zbozi.getNazev();
            }
            case SLOUPEC_CENA -> {
                return zbozi.getCena();
            }
            case SLOUPEC_POCET -> {
                return zbozi.getPocet();
            }
            default -> throw new IllegalArgumentException("Nesprávný sloupec skladu");
        }
    }

    @Override
    public void setValueAt(Object hodnota, int rowIndex, int columnIndex) {
        Zbozi zbozi = seznamZbozi.get(rowIndex);

        switch (columnIndex) {
            case SLOUPEC_NAZEV -> zbozi.setNazev((String)hodnota);
            case SLOUPEC_CENA -> zbozi.setCena((Float)hodnota);
            case SLOUPEC_POCET -> zbozi.setPocet((Integer)hodnota);
            default -> throw new IllegalArgumentException("Nesprávný sloupec skladu");
        }

        fireTableCellUpdated(rowIndex, columnIndex); //která buňka se změnila a zareaguje a aktualizuje v skladě
    }
}
