package model;

import java.util.ArrayList;
import java.util.List;

public class Sklad {
    private List<Zbozi> seznamZbozi;

    public Sklad() {
        seznamZbozi = new ArrayList<>();
    }

    public void pridejZbozi(Zbozi zbozi){
        seznamZbozi.add(zbozi);
    }
    public Zbozi getZbozi(int index){ //zde taky jestli je správný index
        Zbozi zbozi = null;
        if (index > 0){
            zbozi = seznamZbozi.get(index);
        }
        return zbozi;
    }
    public void smazZbozi(int index) { //zde taky jestli je správný index
        if (index > 0){
            seznamZbozi.remove(index);
        }
    }
    public void smazVsechnoZbozi() {
        seznamZbozi.clear();
    }
}
