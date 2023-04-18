package serdes;

import model.Zbozi;

import java.io.IOException;
import java.util.List;

public interface SerDes { //interface na serilizaci a desirilizaci do určitého formátu, všechny objekty to musí dodržet
    List<Zbozi> nacti(String soubor) throws IOException;
    void uloz(String soubor, List<Zbozi> seznam) throws IOException;
}
