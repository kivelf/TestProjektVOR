package controller;

import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import controller.Controller;
import java.time.LocalDate;

class ControllerTest {
    private LocalDate startdato = LocalDate.parse("2019-01-01");
    private LocalDate slutdato = LocalDate.parse("2019-01-03");
    private LocalDate slutdato1 = LocalDate.parse("2018-12-31");
    private Patient finn;
    private Lægemiddel fucidin = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

    @BeforeEach
    void setUp() {
        finn = new Patient("070985-1153", "Finn Madsen", 83.2);
    }


    @Test
    void opretDagligFastOrdination() {
        //Testcase 1
        //Arrange
        Controller.opretDagligFastOrdination(startdato, startdato, finn, fucidin, 0, 0, 0, 0);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}