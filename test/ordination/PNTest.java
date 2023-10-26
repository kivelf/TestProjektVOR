package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    private PN pn;
    private LocalDate dato;

    @BeforeEach
    void setup() {
        LocalDate startdato = LocalDate.parse("2019-02-12");
        LocalDate slutdato = LocalDate.parse("2019-02-14");
        int antalEnheder = 50;
        pn = new PN(startdato, slutdato, antalEnheder);
    }

    @Test
    void anvendDosis() {
        // TestCase 1 - dato = startdato
        // Arrange
        dato = pn.getStartDato();
        // Act
        int antalGangeAnvendtFør = pn.antalGangeAnvendt();
        pn.anvendDosis(dato);
        int antalGangeAnvendtEfter = pn.antalGangeAnvendt();
        int faktiskSvar = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(1, faktiskSvar);

        // TestCase 2 - dato > startdato og dato < slutdato
        // Arrange
        dato = LocalDate.parse("2019-02-13");
        // Act
        antalGangeAnvendtFør = pn.antalGangeAnvendt();
        pn.anvendDosis(dato);
        antalGangeAnvendtEfter = pn.antalGangeAnvendt();
        faktiskSvar = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(1, faktiskSvar);

        // TestCase 3 - dato = slutdato
        // Arrange
        dato = pn.getSlutDato();
        // Act
        antalGangeAnvendtFør = pn.antalGangeAnvendt();
        pn.anvendDosis(dato);
        antalGangeAnvendtEfter = pn.antalGangeAnvendt();
        faktiskSvar = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(1, faktiskSvar);

        // TestCase 4 - dato < startdato
        // Arrange
        dato = pn.getStartDato().minusDays(1);
        // Act
        antalGangeAnvendtFør = pn.antalGangeAnvendt();
        pn.anvendDosis(dato);
        antalGangeAnvendtEfter = pn.antalGangeAnvendt();
        faktiskSvar = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(0, faktiskSvar);

        // TestCase 5 - dato > slutdato
        // Arrange
        dato = pn.getSlutDato().plusDays(1);
        // Act
        antalGangeAnvendtFør = pn.antalGangeAnvendt();
        pn.anvendDosis(dato);
        antalGangeAnvendtEfter = pn.antalGangeAnvendt();
        faktiskSvar = antalGangeAnvendtEfter - antalGangeAnvendtFør;
        // Assert
        assertEquals(0, faktiskSvar);
    }

    @Test
    void dageMellemFørsteOgSidsteAnvendelseTC1() {
        // TestCase 1 - første anvendelsesdato er samme som sidste anvendelsesdato
        // Arrange
        dato = LocalDate.parse("2019-02-13");
        // Act
        pn.anvendDosis(dato);
        int faktiskSvar = pn.dageMellemFørsteOgSidsteAnvendelse();
        // Assert
        assertEquals(1, faktiskSvar);
    }

    @Test
    void dagemellemFørsteOgSidsteAnvendelseTC2() {
        // TestCase 2 - første anvendelsesdato er før sidst anvendelsesdato
        // Arrange
        // Act
        pn.anvendDosis(pn.getSlutDato());
        pn.anvendDosis(pn.getStartDato());
        int faktiskSvar = pn.dageMellemFørsteOgSidsteAnvendelse();
        // Assert
        assertEquals(3, faktiskSvar);
    }

    @Test
    void døgnDosisTC1() {
        // TestCase 1 - Anvendt 1 gang over 1 dag
        // Arrange
        dato = LocalDate.parse("2019-02-12");
        // Act
        pn.anvendDosis(dato);
        double faktiskSvar = pn.døgnDosis();
        // Assert
        assertEquals(50, faktiskSvar);
    }

    @Test
    void døgnDosisTC2() {
        // TestCase 2 - Anvendt flere gange over 1 dag
        // Arrange
        dato = LocalDate.parse("2019-02-12");
        pn.anvendDosis(dato);
        pn.anvendDosis(dato);
        pn.anvendDosis(dato);
        // Act
        double faktiskSvar = pn.døgnDosis();
        // Assert
        assertEquals(150, faktiskSvar);
    }

    @Test
    void døgnDosisTC3() {
        // TestCase 3 - Anvendt flere gange over 3 dage
        // Arrange
        dato = LocalDate.parse("2019-02-12");
        pn.anvendDosis(dato);
        dato = LocalDate.parse("2019-02-13");
        pn.anvendDosis(dato);
        dato = LocalDate.parse("2019-02-14");
        pn.anvendDosis(dato);
        // Act
        double faktiskSvar = pn.døgnDosis();
        // Assert
        assertEquals(50, faktiskSvar);

    }

    @Test
    void døgnDosisTC4() {
        // TestCase 4 - Anvendt flere gange over 3 dage
        // Arrange
        // Act
        double faktiskSvar = pn.døgnDosis();
        // Assert
        assertEquals(0, faktiskSvar);
    }

    @Test
    void samletDosisTC1() {
        // TestCase 1 - anvendt 1 gang
        // Arrange
        dato = LocalDate.parse("2019-02-12");
        pn.anvendDosis(dato);
        // Act
        double faktiskSvar = pn.samletDosis();
        // Assert
        assertEquals(50, faktiskSvar);

    }

    @Test
    void samletDosisTC2() {
        // TestCase 2 - anvendt 3 gange på forskellige datoer
        // Arrange
        dato = LocalDate.parse("2019-02-12");
        pn.anvendDosis(dato);
        dato = LocalDate.parse("2019-02-13");
        pn.anvendDosis(dato);
        dato = LocalDate.parse("2019-02-14");
        pn.anvendDosis(dato);
        // Act
        double faktiskSvar = pn.samletDosis();
        // Assert
        assertEquals(150, faktiskSvar);
    }

    @Test
    void samletDosisTC3() {
        // TestCase 3 - anvendt 3 gange på samme dato
        // Arrange
        dato = LocalDate.parse("2019-02-13");
        pn.anvendDosis(dato);
        dato = LocalDate.parse("2019-02-13");
        pn.anvendDosis(dato);
        dato = LocalDate.parse("2019-02-13");
        pn.anvendDosis(dato);
        // Act
        double faktiskSvar = pn.samletDosis();
        // Assert
        assertEquals(150, faktiskSvar);

    }


}