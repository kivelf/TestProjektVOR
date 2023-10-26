package controller;

import ordination.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import controller.Controller;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

class TestOpretDagligFast {
    private LocalDate startdato = LocalDate.parse("2019-01-01");
    private LocalDate slutdato1 = LocalDate.parse("2019-01-03");
    private LocalDate slutdato2 = LocalDate.parse("2018-12-31");
    private Patient finn = new Patient("070985-1153", "Finn Madsen", 83.2);
    private Lægemiddel fucidin = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

    @Test
    void testcase1() {
        //Arrange
        DagligFast df = Controller.opretDagligFastOrdination(startdato, startdato, finn, fucidin, 0, 0, 0, 0);
        //Act
        int result1 = finn.getOrdinationer().size();
        Lægemiddel result2 = df.getLægemiddel();
        //Assert
        assertEquals(result1, 1);
        assertEquals(result2.toString(), "Fucidin");
    }

    @Test
    void testcase2() {
        //Arrange
        DagligFast df = Controller.opretDagligFastOrdination(startdato, slutdato1, finn, fucidin, 0, 0, 0, 0);
        //Act
        int result1 = finn.getOrdinationer().size();
        Lægemiddel result2 = df.getLægemiddel();
        //Assert
        assertEquals(result1, 1);
        assertEquals(result2.toString(), "Fucidin");
    }

    @Test
    void testcase3() {
        //Arrange
        DagligFast df = Controller.opretDagligFastOrdination(startdato, slutdato1, finn, fucidin, 1, 2, 0, 4);
        //Act
        int result1 = finn.getOrdinationer().size();
        Lægemiddel result2 = df.getLægemiddel();
        //Assert
        assertEquals(result1, 1);
        assertEquals(result2.toString(), "Fucidin");
    }

    @Test
    void testcase4() {
        //Arrange
        DagligFast df = Controller.opretDagligFastOrdination(startdato, slutdato1, finn, fucidin, 1, 2, 3, 0);
        //Act
        int result1 = finn.getOrdinationer().size();
        Lægemiddel result2 = df.getLægemiddel();
        //Assert
        assertEquals(result1, 1);
        assertEquals(result2.toString(), "Fucidin");
    }

    @Test
    void testcase5() {
        //Testcase 5
        //Arrange && Assert
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> Controller.opretDagligFastOrdination(startdato, slutdato2, finn, fucidin, 0, 0, 0, 0));
        //Act
        int result1 = finn.getOrdinationer().size();
        //Assert
        assertEquals(result1, 0);
        assertEquals(exception.getMessage(), "dato forkert");
    }
}