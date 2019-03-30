package database;

import models.Hotel;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static database.Database.deserializeDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    private Database database = new Database();
    private HashMap<Long, Hotel> hotels = new HashMap<>();

    @Test
    @DisplayName("correct serialization - empty database")
    public void serializeDatabaseTest() throws IOException {
        assertEquals(0, database.serializeDatabase());
    }

    @Test
    @DisplayName("correct serialization")
    public void serializeDatabase2Test() throws IOException {
        hotels.put(1L, new Hotel("Sample Hotel", new LocalTime(10L), new LocalTime(23L)));
        database.setHotels(hotels);
        assertEquals(0, database.serializeDatabase());
    }

    @Test
    @DisplayName("correct deserialization")
    public void deserializeDatabaseTest() throws IOException {
        Database newDatabase = deserializeDatabase();
        assertEquals(database.getHotels(), newDatabase.getHotels());
        assertEquals(database.getOrders(), newDatabase.getOrders());
        assertEquals(database.getReservations(), newDatabase.getReservations());
        assertEquals(database.getRooms(), newDatabase.getRooms());
        assertEquals(database.getUsers(), newDatabase.getUsers());
    }
}
