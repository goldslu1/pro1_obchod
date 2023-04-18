package serdes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Zbozi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GsonSerDes implements SerDes{ //na serilizaci a desirilizaci| ukládání do souboru json
    @Override
    public List<Zbozi> nacti(String soubor) throws IOException {
        Gson gson = new Gson();
        FileReader vstup = new FileReader(soubor);

        List<Zbozi> seznam = gson.fromJson(vstup, new TypeToken<ArrayList<Zbozi>>(){}.getType());
        vstup.close();

        return seznam;
    }

    @Override
    public void uloz(String soubor, List<Zbozi> seznam) throws IOException {  //ulozeni dat ze seznamu do souboru .json
        FileWriter vystup = new FileWriter(soubor);
        Gson gson = new Gson();
        String json = gson.toJson(seznam);
        vystup.write(json);
        vystup.close();
    }
}
