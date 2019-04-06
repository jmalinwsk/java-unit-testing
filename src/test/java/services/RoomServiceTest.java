package services;

import database.Database;
import models.Hotel;
import models.Room;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {
    private Database database;
    private RoomService roomService;
    private Room room;

    @BeforeEach
    public void init() {
        database = new Database();
        roomService = new RoomService();
        Hotel hotel = new Hotel("Sample name",
                new LocalTime(6), new LocalTime(22));
        room = new Room(hotel, 232, 3);
    }

    @Test
    @DisplayName("validation of room (valid)")
    public void roomValidationTest() {
        boolean result = roomService.roomValidation(room);
        assertTrue(result);
    }

    @Test
    @DisplayName("validation of room" +
            "(returns false because of null argument")
    public void roomValidation2Test() {
        assertFalse(roomService.roomValidation(null));
    }

    @Test
    @DisplayName("validation of room" +
            "(returns false because of negative number of room)")
    public void roomValidation3Test() {
        room.setNumberOfRoom(-1);
        assertFalse(roomService.roomValidation(room));
    }

    @Test
    @DisplayName("validation of room" +
            "(returns false because of negative number of amount of people" +
            "in hotel room)")
    public void roomValidation4Test() {
        room.setAmountOfPeople(-1);
        assertFalse(roomService.roomValidation(room));
    }

    @Test
    @DisplayName("validation of room" +
            "(returns false because of 0 number of amount of people" +
            "in hotel room)")
    public void roomValidation5Test() {
        room.setAmountOfPeople(0);
        assertFalse(roomService.roomValidation(room));
    }

    @Test
    @DisplayName("adding room to database (valid)")
    public void addRoomToDatabaseTest() {
        assertEquals(new HashMap<Integer, Room>(), database.getRooms());

        roomService.addRoomToDatabase(database, room);

        HashMap<Integer, Room> roomsTemp = new HashMap<>();
        roomsTemp.put(1, room);
        assertEquals(roomsTemp, database.getHotels());
    }

    @Test
    @DisplayName("adding room to database " +
            "(throws IllegalArgumentException when database is null")
    public void addRoomToDatabase2Test() {
        assertThrows(IllegalArgumentException.class,
                () -> roomService.addRoomToDatabase(null, room));
    }

    @Test
    @DisplayName("adding room to database " +
            "(throws IllegalArgumentException when room is null")
    public void addRoomToDatabase3Test() {
        assertThrows(IllegalArgumentException.class,
                () -> roomService.addRoomToDatabase(database, null));
    }

    @Test
    @DisplayName("adding room to database " +
            "(throws IllegalArgumentException when database and room are null")
    public void addRoomToDatabase4Test() {
        assertThrows(IllegalArgumentException.class,
                () -> roomService.addRoomToDatabase(null, null));
    }

    @AfterEach
    public void cleanup() {
        database = null;
        roomService = null;
        room = null;
    }
}
