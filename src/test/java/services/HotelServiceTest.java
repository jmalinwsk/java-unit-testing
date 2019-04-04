package services;

import database.Database;
import models.Hotel;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.*;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotelServiceTest {
    private Database database;
    private HotelService hotelService;
    private Hotel hotel;

    @BeforeEach
    public void init() {
        database = new Database();
        hotelService = new HotelService();
        hotel = new Hotel("Sample name", new LocalTime(8), new LocalTime(22));
    }

    @Test
    @DisplayName("validation of hotel (valid)")
    public void hotelValidationTest() {
        boolean result = hotelService.hotelValidation(hotel);
        assertTrue(result);
    }

    @Test
    @DisplayName("validation of hotel " +
            "(throws NullPointerException because of null argument)")
    public void hotelValidation2Test() {
        assertThrows(NullPointerException.class,
                () -> hotelService.hotelValidation(null));
    }

    @Test
    @DisplayName("validation of hotel " +
            "(throws NullPointerException because of hotel name as null)")
    public void hotelValidation3Test() {
        hotel.setName(null);
        assertThrows(NullPointerException.class,
                () -> hotelService.hotelValidation(hotel));
    }

    @Test
    @DisplayName("validation of hotel " +
            "(throws NullPointerException because hotel name is empty string)")
    public void hotelValidation4Test() {
        hotel.setName("");
        assertThrows(IllegalArgumentException.class,
                () -> hotelService.hotelValidation(hotel));
    }

    @Test
    @DisplayName("validation of hotel " +
            "(throws NullPointerException because open hour is null)")
    public void hotelValidation5Test() {
        hotel.setOpenHour(null);
        assertThrows(NullPointerException.class,
                () -> hotelService.hotelValidation(hotel));
    }

    @Test
    @DisplayName("validation of hotel " +
            "(throws NullPointerException because close hour is null)")
    public void hotelValidation6Test() {
        hotel.setCloseHour(null);
        assertThrows(NullPointerException.class,
                () -> hotelService.hotelValidation(hotel));
    }

    @Test
    @DisplayName("validation of hotel " +
            "(throws NullPointerException because open hour is after close hour)")
    public void hotelValidation7Test() {
        hotel.setOpenHour(new LocalTime(21));
        hotel.setCloseHour(new LocalTime(20));
        assertThrows(DateTimeException.class,
                () -> hotelService.hotelValidation(hotel));
    }


    @AfterEach
    public void cleanup() {
        database = null;
        hotelService = null;
        hotel = null;
    }

}
