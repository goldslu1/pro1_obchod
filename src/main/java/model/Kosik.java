package model;

import java.util.ArrayList;
import java.util.List;

public class Kosik {
    private List<PolozkaKosiku> seznamPolozek;

    public Kosik(){
        seznamPolozek = new ArrayList<>();
    }
    public void pridej(Zbozi zbozi) { //dodělat aby se tam nepřidalo 2krát stejné zboží
        for (var polozka : seznamPolozek)
        {
            Zbozi zb1 = polozka.getZbozi();
            if (zbozi == zb1)
            {
                polozka.zvysit();
            }
            else
                {
                seznamPolozek.add(new PolozkaKosiku(zbozi, 1));
            }
        }
    }

    public void odeber(int index) { //dodělat kontrola jestli je index v platném intervalu
        if (index > 0) seznamPolozek.remove(index);
    }

    public void zvysit(int index) {
        if (index > 0) seznamPolozek.get(index).zvysit();

    }

    public void snizit(int index) {
        if (index > 0) seznamPolozek.get(index).snizit();
    }

    public void vysypat() {
        seznamPolozek.clear();
    }
}
