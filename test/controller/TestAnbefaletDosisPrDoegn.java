package controller;

import org.junit.jupiter.api.Test;
import ordination.Lægemiddel;
import ordination.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAnbefaletDosisPrDoegn {
    private Patient jane = new Patient("121256-0512", "Jane Jensen", 63.4);
    private Patient kiddo = new Patient("151216-2231", "Young Youngster", 19.2);
    private Patient biggo = new Patient("120163-1205", "Absolute Unit", 143.8);
    private Lægemiddel paracetamol = new Lægemiddel("Paracetamol", 1, 1.5, 2, "Ml");
    private Lægemiddel fucidin = new Lægemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");

    @Test
    void anbefaletDosisPrDøgnTC1() {
        double anbefalet1 = Controller.anbefaletDosisPrDøgn(kiddo, fucidin);

        assertEquals(19.2 * 0.025, anbefalet1, 0.0001);
    }

    @Test
    void anbefaletDosisPrDøgnTC2() {
        double anbefalet2 = Controller.anbefaletDosisPrDøgn(jane, fucidin);

        assertEquals(63.4 * 0.025, anbefalet2, 0.0001);
    }

    @Test
    void anbefaletDosisPrDøgnTC3() {
        double anbefalet3 = Controller.anbefaletDosisPrDøgn(biggo, fucidin);

        assertEquals(143.8 * 0.025, anbefalet3, 0.0001);
    }

    @Test
    void anbefaletDosisPrDøgnTC4() {
        double anbefalet4 = Controller.anbefaletDosisPrDøgn(kiddo, paracetamol);

        assertEquals(19.2 * 1, anbefalet4, 0.0001);
    }

    @Test
    void anbefaletDosisPrDøgnTC5() {
        double anbefalet5 = Controller.anbefaletDosisPrDøgn(jane, paracetamol);

        assertEquals(63.4 * 1.5, anbefalet5, 0.0001);
    }

    @Test
    void anbefaletDosisPrDøgnTC6() {
        double anbefalet6 = Controller.anbefaletDosisPrDøgn(biggo, paracetamol);

        assertEquals(143.8 * 2, anbefalet6, 0.0001);
    }
}
