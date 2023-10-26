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

import static org.junit.jupiter.api.Assertions.*;

public class TestOpretDagligSkævOrdination {
    private LocalDate startdato = LocalDate.parse("2019-01-01");
    private LocalDate slutdato = LocalDate.parse("2019-01-03");
    private LocalDate slutdato2 = LocalDate.parse("2018-12-31");
    private Patient finn = new Patient("070985-1153", "Finn Madsen", 83.2);
    private Lægemiddel fucidin = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
    private double[] antalEnheder1 = {2};
    private double[] antalEnheder2 = {2, 3, 2};
    private LocalTime[] klokkeSlet1 = {LocalTime.parse("09:00")};
    private LocalTime[] klokkeSlet2 = {LocalTime.parse("09:00"), LocalTime.parse("12:00"), LocalTime.parse("18:00")};
    private List<Dosis> doser1forventede = new ArrayList<>();
    private List<Dosis> doser2forventede = new ArrayList<>();

    @BeforeEach
    void setUp() {
        doser1forventede.add(new Dosis(LocalTime.parse("09:00"), 2));
        doser2forventede.add(new Dosis(LocalTime.parse("09:00"), 2));
        doser2forventede.add(new Dosis(LocalTime.parse("12:00"), 3));
        doser2forventede.add(new Dosis(LocalTime.parse("18:00"), 2));
    }

    @Test
    void opretDagligSkævOrdinationTC1() {
        // arrange
        DagligSkæv dagligSkæv1 = Controller.opretDagligSkævOrdination(startdato, slutdato, finn, fucidin, klokkeSlet1, antalEnheder1);

        // act
        Lægemiddel lm1 = dagligSkæv1.getLægemiddel();
        List<Dosis> doser1 = new ArrayList<>(dagligSkæv1.getDoser());
        int antalOrdinationer1 = finn.getOrdinationer().size();

        // assert
        assertSame(fucidin, lm1);
        for (int i = 0; i < doser1forventede.size(); i++){
            assertEquals(doser1forventede.get(i).toString(), doser1.get(i).toString());
        }
        assertEquals(1, antalOrdinationer1);
    }

    @Test
    void opretDagligSkævOrdinationTC2() {
        // arrange
        DagligSkæv dagligSkæv2 = Controller.opretDagligSkævOrdination(startdato, slutdato, finn, fucidin, klokkeSlet2, antalEnheder2);

        // act
        Lægemiddel lm2 = dagligSkæv2.getLægemiddel();
        List<Dosis> doser2 = new ArrayList<>(dagligSkæv2.getDoser());
        int antalOrdinationer2 = finn.getOrdinationer().size();

        // assert
        assertSame(fucidin, lm2);
        for (int i = 0; i < doser2forventede.size(); i++){
            assertEquals(doser2forventede.get(i).toString(), doser2.get(i).toString());
        }
        assertEquals(1, antalOrdinationer2);
    }

    @Test
    void opretDagligSkævOrdinationTC3() {
        // arrange
        DagligSkæv dagligSkæv3 = Controller.opretDagligSkævOrdination(startdato, startdato, finn, fucidin, klokkeSlet1, antalEnheder1);

        // act
        Lægemiddel lm3 = dagligSkæv3.getLægemiddel();
        List<Dosis> doser3 = new ArrayList<>(dagligSkæv3.getDoser());
        int antalOrdinationer3 = finn.getOrdinationer().size();

        // assert
        assertSame(fucidin, lm3);
        for (int i = 0; i < doser1forventede.size(); i++){
            assertEquals(doser1forventede.get(i).toString(), doser3.get(i).toString());
        }
        assertEquals(1, antalOrdinationer3);
    }

    @Test
    void opretDagligSkævOrdinationTC4() {
        // arrange
        DagligSkæv dagligSkæv4 = Controller.opretDagligSkævOrdination(startdato, startdato, finn, fucidin, klokkeSlet2, antalEnheder2);

        // act
        Lægemiddel lm4 = dagligSkæv4.getLægemiddel();
        List<Dosis> doser4 = new ArrayList<>(dagligSkæv4.getDoser());
        int antalOrdinationer4 = finn.getOrdinationer().size();

        // assert
        assertSame(fucidin, lm4);
        for (int i = 0; i < doser2forventede.size(); i++){
            assertEquals(doser2forventede.get(i).toString(), doser4.get(i).toString());
        }
        assertEquals(1, antalOrdinationer4);
    }

    @Test
    void calculatePremiumThrowsExceptionDueToDate(){
        // arrange

        // act + assert
        // expected to throw an error since age < 18
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Controller.opretDagligSkævOrdination(startdato, slutdato2, finn, fucidin, klokkeSlet2, antalEnheder2));
        assertEquals("Startdatoen kan ikke være efter slutdatoen!", exception.getMessage());
    }

    @Test
    void calculatePremiumThrowsExceptionDueToDifferentSizes(){
        // arrange

        // act + assert
        // expected to throw an error since age < 18
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Controller.opretDagligSkævOrdination(startdato, slutdato, finn, fucidin, klokkeSlet1, antalEnheder2));
        assertEquals("Antallet af elementer i klokkeSlet og antalEnheder kan ikke være forskellige!", exception.getMessage());
    }
}
