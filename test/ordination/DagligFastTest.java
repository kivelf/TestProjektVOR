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
    void samletDosis() {

    }

    @Test
    void døgnDosis() {
        double[] array = {0, 0, 0, 0};
        DagligFast df = new DagligFast(LocalDate.parse("2019-01-10"),
                LocalDate.parse("2019-01-12"), array);
        df.setLægemiddel(lm);
        assertEquals(df.døgnDosis(), 0);

       array = new double[]{1, 1, 1, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"),
                LocalDate.parse("2019-01-12"), array);
        assertEquals(df.døgnDosis(), 4);

        array = new double[]{0, 2, 0, 1};
        df = new DagligFast(LocalDate.parse("2019-01-10"),
                LocalDate.parse("2019-01-12"), array);
        assertEquals(df.døgnDosis(), 3);
    }
}