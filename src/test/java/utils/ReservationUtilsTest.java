package utils;

import models.Reservation;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationUtilsTest {
    private Reservation r1;
    private Reservation r2;

    @BeforeEach
    public void init() {
        r1 = new Reservation();
        r2 = new Reservation();
    }

    @Test
    @DisplayName("checks if date of first reservation is contained in " +
            "second reservation and returns true")
    public void isContainedInTest() {
        r1 = new Reservation(
                new DateTime(2019, 4, 4, 10, 0),
                new DateTime(2019, 4, 5, 10, 0),
                null, null);
        r2 = new Reservation(
                new DateTime(2019, 4, 3, 20, 0),
                new DateTime(2019, 4, 6, 20, 0),
                null, null);

        assertTrue(ReservationUtils.isContainedIn(r1, r2));
    }

    @Test
    @DisplayName("checks if date of first reservation is contained in " +
            "second reservation and returns false")
    public void isContainedIn2Test() {
        r1 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 11, 10, 0),
                null, null);
        r2 = new Reservation(
                new DateTime(2019, 4, 4, 20, 0),
                new DateTime(2019, 4, 5, 20, 0),
                null, null);

        assertFalse(ReservationUtils.isContainedIn(r1, r2));
    }

    @Test
    @DisplayName("checks if date of first reservation is equal to date in " +
            "second reservation and returns true")
    public void isEqualToTest() {
        r1 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 11, 10, 0),
                null, null);
        r2 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 11, 10, 0),
                null, null);

        assertTrue(ReservationUtils.isEqualTo(r1, r2));
    }

    @Test
    @DisplayName("checks if date of first reservation is equal to date in " +
            "second reservation and returns false")
    public void isEqualTo2Test() {
        r1 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 11, 10, 0),
                null, null);
        r2 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 11, 11, 0),
                null, null);

        assertFalse(ReservationUtils.isEqualTo(r1, r2));
    }

    @Test
    @DisplayName("checks if date of first reservation has a product of sets with " +
            "date of second reservation and returns true")
    public void hasAProductOfSetsTest() {
        r1 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 15, 10, 0),
                null, null);
        r2 = new Reservation(
                new DateTime(2019, 4, 9, 10, 0),
                new DateTime(2019, 4, 13, 10, 0),
                null, null);

        assertTrue(ReservationUtils.hasAProductOfSets(r1, r2));
    }

    @Test
    @DisplayName("checks if date of first reservation has a product of sets with " +
            "date of second reservation and returns true")
    public void hasAProductOfSets2Test() {
        r1 = new Reservation(
                new DateTime(2019, 4, 10, 10, 0),
                new DateTime(2019, 4, 15, 10, 0),
                null, null);
        r2 = new Reservation(
                new DateTime(2019, 4, 16, 10, 0),
                new DateTime(2019, 4, 20, 10, 0),
                null, null);

        assertFalse(ReservationUtils.hasAProductOfSets(r1, r2));
    }

    @AfterEach
    public void cleanup() {
        r1 = null;
        r2 = null;
    }
}
