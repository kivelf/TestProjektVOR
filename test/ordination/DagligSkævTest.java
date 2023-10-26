package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkævTest {
    private static LocalDate startDato = LocalDate.of(2019, 01, 10);
    private static LocalDate slutDato = LocalDate.of(2019, 01, 12);

    @Test
    void døgnDosis() {
        // arrange
        DagligSkæv dagligSkæv1 = new DagligSkæv(startDato, slutDato);   // TC1
        DagligSkæv dagligSkæv2 = new DagligSkæv(startDato, slutDato);   // TC2
        DagligSkæv dagligSkæv3 = new DagligSkæv(startDato, slutDato);   // TC3
        DagligSkæv dagligSkæv4 = new DagligSkæv(startDato, slutDato);   // TC4

        // act
        dagligSkæv1.addDosis(new Dosis(LocalTime.parse("09:00"), 1));   // TC1

        dagligSkæv2.addDosis(new Dosis(LocalTime.parse("09:00"), 3));   // TC2

        dagligSkæv3.addDosis(new Dosis(LocalTime.parse("09:00"), 3));   // TC3
        dagligSkæv3.addDosis(new Dosis(LocalTime.parse("10:00"), 4));   // TC3

        dagligSkæv4.addDosis(new Dosis(LocalTime.parse("09:00"), 1));   // TC4
        dagligSkæv4.addDosis(new Dosis(LocalTime.parse("10:00"), 1));   // TC4

        // assert
        assertEquals(dagligSkæv1.døgnDosis(), 1, 0.0001);     // TC1
        assertEquals(dagligSkæv2.døgnDosis(), 3, 0.0001);     // TC2
        assertEquals(dagligSkæv3.døgnDosis(), 7, 0.0001);     // TC3
        assertEquals(dagligSkæv4.døgnDosis(), 2, 0.0001);     // TC4
    }

    @Test
    void samletDosis() {
        // arange
        DagligSkæv dagligSkæv1 = new DagligSkæv(startDato, startDato);   // TC1
        DagligSkæv dagligSkæv2 = new DagligSkæv(startDato, LocalDate.of(2019, 01, 11));   // TC2
        DagligSkæv dagligSkæv3 = new DagligSkæv(startDato, startDato);   // TC3
        DagligSkæv dagligSkæv4 = new DagligSkæv(startDato, slutDato);   // TC4
        DagligSkæv dagligSkæv5 = new DagligSkæv(startDato, startDato);   // TC5
        DagligSkæv dagligSkæv6 = new DagligSkæv(startDato, LocalDate.of(2019, 01, 11));   // TC6
        DagligSkæv dagligSkæv7 = new DagligSkæv(startDato, startDato);   // TC7
        DagligSkæv dagligSkæv8 = new DagligSkæv(startDato, slutDato);   // TC8

        // act
        dagligSkæv1.addDosis(new Dosis(LocalTime.parse("09:00"), 1));   // TC1

        dagligSkæv2.addDosis(new Dosis(LocalTime.parse("09:00"), 1));   // TC2

        dagligSkæv3.addDosis(new Dosis(LocalTime.parse("09:00"), 1));   // TC3
        dagligSkæv3.addDosis(new Dosis(LocalTime.parse("10:00"), 1));   // TC3

        dagligSkæv4.addDosis(new Dosis(LocalTime.parse("09:00"), 1));   // TC4
        dagligSkæv4.addDosis(new Dosis(LocalTime.parse("10:00"), 1));   // TC4

        dagligSkæv5.addDosis(new Dosis(LocalTime.parse("09:00"), 3));   // TC5

        dagligSkæv6.addDosis(new Dosis(LocalTime.parse("09:00"), 3));   // TC6

        dagligSkæv7.addDosis(new Dosis(LocalTime.parse("09:00"), 2));   // TC7
        dagligSkæv7.addDosis(new Dosis(LocalTime.parse("10:00"), 2));   // TC7

        dagligSkæv8.addDosis(new Dosis(LocalTime.parse("09:00"), 2));   // TC8
        dagligSkæv8.addDosis(new Dosis(LocalTime.parse("10:00"), 2));   // TC8

        // assert
        assertEquals(dagligSkæv1.samletDosis(), 1, 0.0001);     // TC1
        assertEquals(dagligSkæv2.samletDosis(), 2, 0.0001);     // TC2
        assertEquals(dagligSkæv3.samletDosis(), 2, 0.0001);     // TC3
        assertEquals(dagligSkæv4.samletDosis(), 6, 0.0001);     // TC4
        assertEquals(dagligSkæv5.samletDosis(), 3, 0.0001);     // TC5
        assertEquals(dagligSkæv6.samletDosis(), 6, 0.0001);     // TC6
        assertEquals(dagligSkæv7.samletDosis(), 4, 0.0001);     // TC7
        assertEquals(dagligSkæv8.samletDosis(), 12, 0.0001);    // TC8
    }
}