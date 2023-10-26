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

class TestAntalOrdinationerPrVægtPrLægemiddel {
    private LocalDate startdato = LocalDate.parse("2019-01-01");
    private LocalDate slutdato1 = LocalDate.parse("2019-01-03");
    private LocalDate slutdato2 = LocalDate.parse("2018-12-31");
    private Patient finn;
    private Lægemiddel fucidin = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

    @BeforeEach
    void setUp() {
        finn = new Patient("070985-1153", "Finn Madsen", 83.2);
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        //Basisdata
        Controller.setStorage(new Storage());
        Patient ib = Controller.opretPatient("090149-2529", "Ib Hansen", 87.7);
        Lægemiddel methorexate = Controller.opretLægemiddel(
                "Methotrexate", 0.01, 0.015,
                0.02, "Styk");
        fucidin = Controller.opretLægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
        finn = Controller.opretPatient("070985-1153", "Finn Madsen", 83.2);
        Controller.opretPNOrdination(LocalDate.parse("2019-01-20"), LocalDate.parse("2019-01-25"),
                ib, fucidin, 5);
        Controller.opretDagligFastOrdination(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-12"),
                finn, fucidin, 2, 0, 1, 0);
        Controller.opretDagligFastOrdination(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-15"),
                finn, fucidin, 0, 0, 1, 0);


        //Testcase 1
        //Act
        int result = Controller.antalOrdinationerPrVægtPrLægemiddel(75, 100, methorexate);
        //Assert
        assertEquals(result, 0);

        //Testcase 2
        //Act
        result = Controller.antalOrdinationerPrVægtPrLægemiddel(75, 75, methorexate);
        //Assert
        assertEquals(result, 0);

        //Testcase 3
        //Act
        result = Controller.antalOrdinationerPrVægtPrLægemiddel(100, 75, methorexate);
        //Assert
        assertEquals(result, 0);

        //Testcase 4
        //Act
        result = Controller.antalOrdinationerPrVægtPrLægemiddel(75, 100, fucidin);
        //Assert
        assertEquals(result, 3);

        //Testcase 5
        //Act
        result = Controller.antalOrdinationerPrVægtPrLægemiddel(83.2, 83.2, fucidin);
        //Assert
        assertEquals(result, 2);

        //Testcase 6
        //Act
        result = Controller.antalOrdinationerPrVægtPrLægemiddel(100, 75, fucidin);
        //Assert
        assertEquals(result, 3);
    }
}