package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination{
    private Dosis[] dosis = new Dosis[4];
    private double[] antalDosis;

    protected DagligFast(LocalDate startDato, LocalDate slutDato, double[] antalDosis) {
        super(startDato, slutDato);
        this.antalDosis = antalDosis;
        dosis[0] = antalDosis[0]==0? null : new Dosis(LocalTime.parse("06:00"), antalDosis[0]);
        dosis[1] = antalDosis[1]==0? null : new Dosis(LocalTime.parse("12:00"), antalDosis[1]);
        dosis[2] = antalDosis[2]==0? null : new Dosis(LocalTime.parse("18:00"), antalDosis[2]);
        dosis[4] = antalDosis[3]==0? null : new Dosis(LocalTime.parse("24:00"), antalDosis[3]);
    }

    @Override
    public double samletDosis() {
        return døgnDosis()*this.antalDage();
    }

    @Override
    public double døgnDosis() {
        double antalSum = 0;
        for(double d : antalDosis){
            antalSum += d;
        }
        return antalSum;
    }

    @Override
    public String getType() {
        return "daglig fast";
    }
}
