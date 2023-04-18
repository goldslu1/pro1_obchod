package gui;

import model.Sklad;
import model.Zbozi;

public class Docasne {
    public static void napln(Sklad sklad){
        sklad.pridejZbozi(new Zbozi("Rohlík", 2.0f, 20));
        sklad.pridejZbozi(new Zbozi("Houska", 4.0f, 30));
        sklad.pridejZbozi(new Zbozi("Chleba", 20.0f, 10));
        sklad.pridejZbozi(new Zbozi("TV", 10000.0f, 5));
    }
}
