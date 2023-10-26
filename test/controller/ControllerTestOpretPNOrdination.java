package controller;

import ordination.Lægemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTestOpretPNOrdination {

    private LocalDate startdato;
    private LocalDate slutdato;
    private Patient patient;
    private Lægemiddel lægemiddel;
    private int antal;


    @BeforeEach
    void setup() {
        startdato = LocalDate.parse("2023-10-23");
        patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        antal = 1;

    }

    @Test
    void TestCase1() {
        // TestCase 1 - dato for start og slut er ens
        // Arrange
        slutdato = startdato;
        int førOrdination = patient.getOrdinationer().size();
        PN pn = Controller.opretPNOrdination(startdato, slutdato, patient, lægemiddel, antal);
        int efterOrdination = patient.getOrdinationer().size();
        // Act
        int faktiskResultat = efterOrdination - førOrdination;
        // Assert
        assertEquals(1, faktiskResultat); // Tjekker om ordinationen er knyttet til patienten
        assertSame(lægemiddel, pn.getLægemiddel()); // Tjekker om lægemidlet er knyttet til ordinationen
    }

    @Test
    void TestCase2() {
        // TestCse 2 - dato for start er før slutdato
        // Arrange
        slutdato = startdato.plusDays(5);
        int førOrdination = patient.getOrdinationer().size();
        PN pn = Controller.opretPNOrdination(startdato, slutdato, patient, lægemiddel, antal);
        int efterOrdination = patient.getOrdinationer().size();
        // Act
        int faktiskResultat = efterOrdination - førOrdination;
    }
}
