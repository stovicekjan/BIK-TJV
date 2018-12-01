package cz.cvut.fit.tjv.cv.vozovy_park;

import cz.cvut.fit.tjv.cv.vozovy_park.vozidla.*;

public class VozovyPark {
    private Vozidlo [] data = {new Riksa(2), 
                            new Auto(5, 50, 5.0), 
                            new Nakladak(25, 150, 15.0)};
    
    public int pocetCestujicich() {
        int vysledek = 0;
        for (Vozidlo v : data) {
            if (v instanceof Osobni) {
                vysledek += ((Osobni) v).getPocetOsob();
            }
        }
        return vysledek;
    }
}
