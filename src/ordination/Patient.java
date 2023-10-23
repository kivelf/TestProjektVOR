package ordination;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private final String cprNr;
    private final String navn;
    private final double vægt;
    private List<Ordination> ordinationer = new ArrayList<>(); // link til Ordination

    public Patient(String cprNr, String navn, double vægt) {
        this.cprNr = cprNr;
        this.navn = navn;
        this.vægt = vægt;
    }

    public String getCprNr() {
        return cprNr;
    }

    public String getNavn() {
        return navn;
    }

    public List<Ordination> getOrdinationer() {
        return new ArrayList<>(ordinationer);
    }

    public void addOrdinationer(Ordination o){
        ordinationer.add(o);
    }

    public double getVægt() {
        return vægt;
    }

    @Override
    public String toString() {
        return navn + "  " + cprNr;
    }
}
