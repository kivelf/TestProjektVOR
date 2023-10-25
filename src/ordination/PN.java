package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {
    private final double antalEnheder;
    private List<LocalDate> datoerForAnvendelse = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public List<LocalDate> getDatoerForAnvendelse() {
        return new ArrayList<>(datoerForAnvendelse);
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) {
        if (dato.isAfter(super.getStartDato().minusDays(1)) && dato.isBefore(super.getSlutDato().plusDays(1))) {
            datoerForAnvendelse.add(dato);
        }
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {
        return datoerForAnvendelse.size();
    }

    @Override
    public double samletDosis() {
        return datoerForAnvendelse.size() * antalEnheder;
    }

    @Override
    public double døgnDosis() {
        if(datoerForAnvendelse.isEmpty()) return 0;
        return samletDosis() / dageMellemFørsteOgSidsteAnvendelse();
    }

    @Override
    public String getType() {
        return "PN";
    }

    public int dageMellemFørsteOgSidsteAnvendelse() {
        LocalDate første = datoerForAnvendelse.get(0);
        LocalDate sidste = datoerForAnvendelse.get(0);

        for (LocalDate dato : datoerForAnvendelse) {
            if (dato.isBefore(første)) {
                første = dato;
            }
            if (dato.isAfter(sidste)) {
                sidste = dato;
            }
        }
        return (int) ChronoUnit.DAYS.between(første, sidste) + 1;
    }
}
