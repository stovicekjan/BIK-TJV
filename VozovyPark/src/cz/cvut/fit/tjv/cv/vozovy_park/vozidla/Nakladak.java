package cz.cvut.fit.tjv.cv.vozovy_park.vozidla;

public class Nakladak extends MotoroveVozidlo {
    private final int nosnost;

    public Nakladak(int nosnost, int kapacitaNadrze, double spotreba) {
        super(kapacitaNadrze, spotreba);
        this.nosnost = nosnost;
    }

    public boolean uveze(int co) {
        return co <= nosnost;
    }

    public int getNosnost() {
        return nosnost;
    }
    
    
}
