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
        return seznamZbozi.get(index);
    }
    public void smazZbozi(int index) { //zde taky jestli je správný index
        seznamZbozi.remove(index);
    }
    public void smazVsechnoZbozi() {
        seznamZbozi.clear();
    }
}
