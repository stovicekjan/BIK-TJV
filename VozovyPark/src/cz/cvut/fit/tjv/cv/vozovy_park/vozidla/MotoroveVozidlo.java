package cz.cvut.fit.tjv.cv.vozovy_park.vozidla;

public abstract class MotoroveVozidlo extends Vozidlo { //kdyz ma trida aspon jednu abstract metodu, musi byt cela abstract
    private final int kapacitaNadrze;
    private final double spotreba;
    
    public static final double MAX_SPOTREBA = 100.0;

    public MotoroveVozidlo(int kapacitaNadrze, double spotreba) {
        this.kapacitaNadrze = kapacitaNadrze;
        this.spotreba = spotreba;
    }
    
    public double dojezd() {
        return kapacitaNadrze / spotreba * 100;
    }
    

    @Override
    public String toString() {
        return "Motorove vozidlo: kapacita " + kapacitaNadrze + ", spotreba " + spotreba;
    }

    public int getKapacitaNadrze() {
        return kapacitaNadrze;
    }

    public double getSpotreba() {
        return spotreba;
    }
    
    
    
    
}
