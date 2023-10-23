package ordination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DagligSkæv extends Ordination {
    private final List<Dosis> doser = new ArrayList<>();

    public DagligSkæv(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public void addDosis (Dosis dosis){
        doser.add(dosis);
    }

    public void removeDosis (Dosis dosis){
        if (doser.contains(dosis)){
            doser.remove(dosis);
        }
    }

    public List<Dosis> getDoser(){
        return new ArrayList<>(doser);
    }

    /** Returner den totale dosis, der er givet i den periode, ordinationen er gyldig. */
    @Override
    public double samletDosis() {
        double total = 0;
        for (Dosis d : doser){
            total += d.getAntal();
        }
        return total;
    }

    /** Returner den gennemsnitlige dosis givet per dag. */
    @Override
    public double døgnDosis() {
        return samletDosis() / antalDage();
    }

    /** Returner ordinationstypen som en String. */
    @Override
    public String getType() {
        return "Daglig skæv";
    }
}
