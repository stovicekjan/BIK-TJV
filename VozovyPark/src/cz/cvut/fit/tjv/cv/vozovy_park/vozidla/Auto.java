package cz.cvut.fit.tjv.cv.vozovy_park.vozidla;

public class Auto extends MotoroveVozidlo {
    private final int pocetOsob;

    public Auto(int pocetOsob, int kapacitaNadrze, double spotreba) {
        super(kapacitaNadrze, spotreba);
        this.pocetOsob = pocetOsob;
    }

    public boolean uveze(int co) {
        return co <= pocetOsob;
    }

    public int getPocetOsob() {
        return pocetOsob;
    }
    
}
