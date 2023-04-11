package model;

import java.util.ArrayList;
import java.util.List;

public class Kosik {
    private List<PolozkaKosiku> seznamPolozek;

    public Kosik(){
        seznamPolozek = new ArrayList<>();
    }
    public void pridej(Zbozi zbozi) { //dodělat aby se tam nepřidalo 2krát stejné zboží
        seznamPolozek.add(new PolozkaKosiku(zbozi, 1));
    }

    public void odeber(int index) { //dodělat kontrola jestli je index v platném intervalu
        seznamPolozek.remove(index);
    }

    public void zvysit(int index) {
        seznamPolozek.get(index).zvysit();
    }

    public void snizit(int index) {
        seznamPolozek.get(index).snizit();
    }

    public void vysypat() {
        seznamPolozek.clear();
    }
}
