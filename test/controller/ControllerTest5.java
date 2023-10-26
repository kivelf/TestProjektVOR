package controller;

import ordination.DagligSkæv;
import ordination.Dosis;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ControllerTest5 {
    private LocalDate startdato = LocalDate.parse("2019-01-01");
    private LocalDate slutdato = LocalDate.parse("2019-01-03");
    private LocalDate slutdato1 = LocalDate.parse("2018-12-31");
    private Patient finn;
    private Lægemiddel fucidin = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
    private double[] antalEnheder1 = {2};
    private double[] antalEnheder2 = {2, 3, 2};
    private LocalTime[] klokkeSlet1 = {LocalTime.parse("09:00")};
    private LocalTime[] klokkeSlet2 = {LocalTime.parse("09:00"),   LocalTime.parse("12:00"), LocalTime.parse("18:00")};

    @BeforeEach
    void setUp() {
        finn = new Patient("070985-1153", "Finn Madsen", 83.2);
    }

    @Test
    void opretDagligSkævOrdination() {
        // arrange
        List<Dosis> doser1forventede = new ArrayList<>();
        doser1forventede.add(new Dosis(LocalTime.parse("09:00"), 2));
        List<Dosis> doser2forventede = new ArrayList<>();
        doser2forventede.add(new Dosis(LocalTime.parse("09:00"), 2));
        doser2forventede.add(new Dosis(LocalTime.parse("12:00"), 3));
        doser2forventede.add(new Dosis(LocalTime.parse("18:00"), 2));

        DagligSkæv dagligSkæv1 = Controller.opretDagligSkævOrdination(startdato, slutdato, finn, fucidin, klokkeSlet1, antalEnheder1);   // TC1
        DagligSkæv dagligSkæv2 = Controller.opretDagligSkævOrdination(startdato, slutdato, finn, fucidin, klokkeSlet2, antalEnheder2);   // TC1


        // act
        Lægemiddel lm1 = dagligSkæv1.getLægemiddel();                   // TC1
        List<Dosis> doser1 = new ArrayList<>(dagligSkæv1.getDoser());   // TC1
        int antalOrdinationer1 = finn.getOrdinationer().size();         // TC1

        Lægemiddel lm2 = dagligSkæv2.getLægemiddel();                   // TC2
        List<Dosis> doser2 = new ArrayList<>(dagligSkæv2.getDoser());   // TC2
        int antalOrdinationer2 = finn.getOrdinationer().size();         // TC2

        // assert
        // TC1
        assertSame(fucidin, lm1);
        for (int i = 0; i < doser1forventede.size(); i++){
            assertEquals(doser1forventede.get(i).toString(), doser1.get(i).toString());
        }
        assertEquals(1, antalOrdinationer1);

        // TC2
        assertSame(fucidin, lm2);
        for (int i = 0; i < doser2forventede.size(); i++){
            assertEquals(doser2forventede.get(i).toString(), doser2.get(i).toString());
        }
        assertEquals(2, antalOrdinationer2);
    }
}
