package database;

import models.Hotel;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static database.Database.deserializeDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseTest {
    private Database database;
    private static String databaseFilename = "DatabaseTest";
    private static int counter = 0;

    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    @Test
    @DisplayName("correct serialization - empty database")
    public void serializeDatabaseTest() throws IOException {
        assertEquals(0, database.serializeDatabase(databaseFilename + counter));
        counter++;
    }

    @Test
    @DisplayName("correct serialization")
    public void serializeDatabase2Test() throws IOException {
        HashMap<Long, Hotel> hotels = new HashMap<>();
        hotels.put(1L, new Hotel("Sample Hotel", new LocalTime(10L), new LocalTime(23L)));
        database.setHotels(hotels);

        assertEquals(0, database.serializeDatabase(databaseFilename + counter));
        counter++;
    }

    @Test
    @DisplayName("correct deserialization")
    public void deserializeDatabaseTest() throws IOException {
        File file = new File("target/" + databaseFilename + counter + ".json");
        HashMap<Long, Hotel> hotels = new HashMap<>();
        hotels.put(1L, new Hotel("Sample Hotel", new LocalTime(10L), new LocalTime(23L)));
        database.setHotels(hotels);
        database.serializeDatabase(databaseFilename + counter);
        Database newDatabase = deserializeDatabase(databaseFilename + counter);

        HashMap<Long, Hotel> newHotels = newDatabase.getHotels();

        assertEquals(hotels.get(1L).getName(), newHotels.get(1L).getName());
        assertEquals(hotels.get(1L).getOpenHour(), newHotels.get(1L).getOpenHour());
        assertEquals(hotels.get(1L).getCloseHour(), newHotels.get(1L).getCloseHour());
        counter++;
    }

    @Test
    @DisplayName("incorrect deserialization - wrong filename")
    public void deserializeDatabase2Test() {
        assertThrows(IOException.class,
                () -> database = deserializeDatabase("wrongFilename"));
    }

    @Test
    @DisplayName("incorrect deserialization - empty argument of method")
    public void deserializeDatabase3Test() {
        assertThrows(IOException.class,
                () -> database = deserializeDatabase(null));
    }

    @AfterEach
    public void tearDown() {
        database = null;
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        for(int i=0; i<counter; i++)
            Files.delete(Paths.get("target/" + databaseFilename + i + ".json"));
    }
}
