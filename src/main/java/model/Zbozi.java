package model;

public class Zbozi {
    private String nazev;
    private float cena;
    private int pocet;

    public Zbozi(String nazev, float cena, int pocet) {
        this.nazev = nazev;
        this.cena = cena;
        this.pocet = pocet;
    }

    @Override
    public String toString() {
        return nazev + "(" + Float.toString(cena) + " Kč)";
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

}
