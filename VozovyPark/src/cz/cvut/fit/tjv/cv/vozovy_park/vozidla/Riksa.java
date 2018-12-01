package cz.cvut.fit.tjv.cv.vozovy_park.vozidla;

public class Riksa extends Vozidlo implements Osobni {
    private final int pocetOsob;

    public Riksa(int pocetOsob) {
        this.pocetOsob = pocetOsob;
    }

    @Override
    public boolean uveze(int co) {
        return co <= pocetOsob;
    }

    @Override
    public int getPocetOsob() {
        return pocetOsob;
    }
    
}
