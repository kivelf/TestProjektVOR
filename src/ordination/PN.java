package ordination;

import java.time.LocalDate;
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
        if (dato.isAfter(super.getStartDato()) && dato.isBefore(super.getSlutDato())) {
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
        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return "PN";
    }
}
