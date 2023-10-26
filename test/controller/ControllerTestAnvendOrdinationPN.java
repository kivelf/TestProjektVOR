package controller;


import ordination.Lægemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class ControllerTestAnvendOrdinationPN {
    private LocalDate startdato;
    private LocalDate slutdato;
    private Patient patient;
    private Lægemiddel lægemiddel;
    private int antalEnheder;
    private PN ordination;
    private LocalDate dato;

    @BeforeEach
    void setup() {
        startdato = LocalDate.parse("2019-10-25");
        slutdato = LocalDate.parse("2019-10-30");
        patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        lægemiddel = new Lægemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        antalEnheder = 50;
        ordination = Controller.opretPNOrdination(startdato, slutdato, patient, lægemiddel, antalEnheder);
    }

    @Test
    void testCase1() {
        // TestCase 1 - Dato er efter startdato og før slutdato
        // Arrange
        dato = LocalDate.parse("2019-10-27");
        // Act
        int antalGangeAnvendtFør = ordination.antalGangeAnvendt();
        Controller.anvendOrdinationPN(ordination, dato);
        int antalGangeAnvendtEfter = ordination.antalGangeAnvendt();
        int faktiskResultat = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(1, faktiskResultat);
    }

    @Test
    void testCase2() {
        // TestCase 2 - Dato er det samme som startdato
        // Arrange
        dato = startdato;
        // Act
        int antalGangeAnvendtFør = ordination.antalGangeAnvendt();
        Controller.anvendOrdinationPN(ordination, dato);
        int antalGangeAnvendtEfter = ordination.antalGangeAnvendt();
        int faktiskResultat = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(1, faktiskResultat);
    }

    @Test
    void testCase3() {
        // TestCase 3 - Dato er det samme som slutdato
        // Arrange
        dato = slutdato;
        // Act
        int antalGangeAnvendtFør = ordination.antalGangeAnvendt();
        Controller.anvendOrdinationPN(ordination, dato);
        int antalGangeAnvendtEfter = ordination.antalGangeAnvendt();
        int faktiskResultat = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(1, faktiskResultat);
    }

    @Test
    void testCase4() {
        // TestCase 4 - Dato er før startdato
        // Arrange
        dato = startdato.minusDays(1);
        // Act

        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> Controller.anvendOrdinationPN(ordination, dato));
    }

    @Test
    void testCase5() {
        // TestCase 5 - Dato er efter slutdato
        // Arrange
        dato = slutdato.plusDays(1);
        // Act

        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> Controller.anvendOrdinationPN(ordination, dato));
    }

}
