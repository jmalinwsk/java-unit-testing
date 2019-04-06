package services;

import database.Database;
import models.*;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {
    private Database database;
    private ReservationService reservationService;
    private RoomService roomService;
    private UserService userService;
    private HotelService hotelService;
    private Reservation reservation;
    private Room room;
    private User user;
    private User user2;
    private Hotel hotel;

    @BeforeEach
    public void init() {
        database = new Database();
        reservationService = new ReservationService();
        roomService = new RoomService();
        userService = new UserService();
        hotelService = new HotelService();
        hotel = new Hotel("Sample name", new LocalTime(8), new LocalTime(23));
        room = new Room(hotel, 200, 2);
        user = new User("test@test.com");
        user2 = new User("test2@test2.com");
        reservation = new Reservation(new DateTime(2019, 5, 5, 11, 0),
                new DateTime(2019, 5, 6, 11, 0),
                user, room);
        hotelService.addHotelToDatabase(database, hotel);
        roomService.addRoomToDatabase(database, room);
        userService.addUserToDatabase(database, user);
        userService.addUserToDatabase(database, user2);
    }

    @Test
    @DisplayName("validation of reservation (valid)")
    public void reservationValidationTest() {
        boolean result = reservationService.reservationValidation(reservation);
        assertTrue(result);
    }

    @Test
    @DisplayName("validation of reservation " +
            "(returns false because of null argument)")
    public void reservationValidation2Test() {
        assertFalse(reservationService.reservationValidation(null));
    }

    @Test
    @DisplayName("validation of reservation " +
            "(returns false because start date is after end date)")
    public void reservationValidation3Test() {
        reservation.setEndDate(new DateTime(2019, 1, 1, 11, 0));
        assertFalse(reservationService.reservationValidation(reservation));
    }

    @Test
    @DisplayName("validation of reservation " +
            "(returns false because user in reservation is null)")
    public void reservationValidation4Test() {
        reservation.setUser(null);
        assertFalse(reservationService.reservationValidation(reservation));
    }

    @Test
    @DisplayName("validation of reservation " +
            "(returns false because room in reservation is null)")
    public void reservationValidation5Test() {
        reservation.setRoom(null);
        assertFalse(reservationService.reservationValidation(reservation));
    }

    @Test
    @DisplayName("validation of reservation " +
            "(returns false because user and room in reservation is null)")
    public void reservationValidation6Test() {
        reservation.setUser(null);
        reservation.setRoom(null);
        assertFalse(reservationService.reservationValidation(reservation));
    }

    @Test
    @DisplayName("adding reservation to database (valid)")
    public void addReservationToDatabaseTest() {
        assertTrue(database.getReservations().isEmpty());

        reservationService.addReservationToDatabase(database, reservation);

        HashMap<Integer, Reservation> reservationsTemp = new HashMap<>();
        reservationsTemp.put(1, reservation);
        assertEquals(reservationsTemp, database.getRooms());
    }

    @Test
    @DisplayName("adding reservation to database " +
            "(throws NullPointerException when database is null")
    public void addRoomToDatabase2Test() {
        assertThrows(NullPointerException.class,
                () -> reservationService.addReservationToDatabase(null, reservation));
    }

    @Test
    @DisplayName("adding reservation to database " +
            "(throws IllegalArgumentException when reservation is null")
    public void addRoomToDatabase3Test() {
        assertThrows(IllegalArgumentException.class,
                () -> reservationService.addReservationToDatabase(database, null));
    }

    @Test
    @DisplayName("adding reservation to database " +
            "(throws IllegalArgumentException when database and reservation are null")
    public void addRoomToDatabase4Test() {
        assertThrows(IllegalArgumentException.class,
                () -> reservationService.addReservationToDatabase(null, null));
    }

    @Test
    @DisplayName("adding reservation to database" +
            "(throws IllegalArgumentException when reservation doesn't pass validation")
    public void addRoomToDatabase5Test() {
        reservation.setEndDate(null);
        assertThrows(IllegalArgumentException.class,
                () -> reservationService.addReservationToDatabase(database, reservation));
    }

    @Test
    @DisplayName("adding reservation to database" +
            "(throws NullPointerException when room doesn't exist in database")
    public void addRoomToDatabase6Test() {
        reservation.getRoom().setHotel(new Hotel("Example name 2", new LocalTime(7), new LocalTime(22)));
        assertThrows(NullPointerException.class,
                () -> reservationService.addReservationToDatabase(database, reservation));
    }

    @Test
    @DisplayName("adding reservation to database" +
            "(throws NullPointerException when user doesn't exist in database")
    public void addRoomToDatabase7Test() {
        reservation.getUser().setEmail("example@example.com");
        assertThrows(NullPointerException.class,
                () -> reservationService.addReservationToDatabase(database, reservation));
    }


    @Test
    @DisplayName("getting reservations of specific user when hashmap of user's revervations is not empty")
    public void getReservationsOfUserTest() {
        HashMap<Integer, Reservation> reservations = new HashMap<>();
        reservations.put(1, reservation);
        reservationService.addReservationToDatabase(database, reservation);

        assertEquals(reservations, reservationService.getReservationsOfUser(database, user));
    }

    @Test
    @DisplayName("getting reservations of specific user when hashmap of user's revervation is empty")
    public void getReservationsOfUser2Test() {
        assertTrue(reservationService.getReservationsOfUser(database, user2).isEmpty());
    }

    @AfterEach
    public void cleanup() {
        database = null;
        reservationService = null;
        reservation = null;
        room = null;
        user = null;
        hotel = null;
    }
}
