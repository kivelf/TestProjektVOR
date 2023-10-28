package ordination;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DagligFastTest {
    private static Lægemiddel lm;
    @BeforeEach
    void setup(){
        lm = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
    }

    @Test
    void antalDage(){
        //Testcase 1
        //Arrange
        double[] array = {1, 1, 1, 1};
        DagligFast df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-10"), array);
        //Act
        int result = df.antalDage();
        //Assert
        assertEquals(1, result);

        //Testcase 2
        //Arrange
        df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-14"), array);
        //Act
        result = df.antalDage();
        //Assert
        assertEquals(5, result);
    }

    @Test
    void samletDosis() {
        //Testcase 1
        //Arrange
        double[] array = {0, 0, 0, 0};
        DagligFast df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-10"), array);
        //Act
        double result = df.samletDosis();
        //Assert
        assertEquals(0, result);

        //Testcase 2
        //Arrange
        array = new double[]{1, 1, 0, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-10"), array);
        //Act
        result = df.samletDosis();
        //Assert
        assertEquals(3, result);

        //Testcase 3
        //Arrange
        array = new double[]{1, 1, 1, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-10"), array);
        //Act
        result = df.samletDosis();
        //Assert
        assertEquals(4, result);

        //Testcase 4
        //Arrange
        array = new double[]{0, 0, 0, 0};
        df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-13"), array);
        //Act
        result = df.samletDosis();
        //Assert
        assertEquals(0, result);

        //Testcase 5
        //Arrange
        array = new double[]{1, 1, 0, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-13"), array);
        //Act
        result = df.samletDosis();
        //Assert
        assertEquals(12, result);

        //Testcase 6
        //Arrange
        array = new double[]{1, 1, 1, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"), LocalDate.parse("2019-01-13"), array);
        //Act
        result = df.samletDosis();
        //Assert
        assertEquals(16, result);

    }

    @Test
    void døgnDosis() {
        //Testcase 1
        //Arrange
        double[] array = {0, 0, 0, 0};
        DagligFast df = new DagligFast(LocalDate.parse("2019-01-10"),
                LocalDate.parse("2019-01-12"), array);
        df.setLægemiddel(lm);
        //Act
        double result = df.døgnDosis();
        //Assert
        assertEquals(0, result);


        //Testcase 2
        //Arrange
       array = new double[]{1, 1, 1, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"),
                LocalDate.parse("2019-01-12"), array);
        //Act
        result = df.døgnDosis();
        //Assert
        assertEquals(4, result);

        //Testcase 3
        //Arrange
        array = new double[]{0, 2, 0, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"),
                LocalDate.parse("2019-01-12"), array);
        //Act
        result = df.døgnDosis();
        //Assert
        assertEquals(3, result);
    }
}