package Database;

import Models.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Database {
    private HashMap<Long, Hotel> hotels;
    private HashMap<Long, Order> orders;
    private HashMap<Long, Reservation> reservations;
    private HashMap<Long, Room> rooms;
    private HashMap<Long, User> users;

    private Long nextHotelId = 0L;
    private Long nextOrderId = 0L;
    private Long nextReservationsId = 0L;
    private Long nextRoomId = 0L;
    private Long nextUserId = 0L;

    public Database() {
        hotels = new HashMap<Long, Hotel>();
        orders = new HashMap<Long, Order>();
        reservations = new HashMap<Long, Reservation>();
        rooms = new HashMap<Long, Room>();
        users = new HashMap<Long, User>();
    }

    /** zapisywanie bazy danych do pliku */
    public void serializeDatabase() throws IOException, JsonGenerationException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/database.json"), this);
    }

    /** wczytywanie bazy danych z pliku */
    public static Database deserializeDatabase() throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Database database = new Database();
        database = objectMapper.readValue(new File("target/database.json"), Database.class);

        return database;
    }

    private Long getNextHotelId() {
      return ++nextHotelId;
    }

    private Long getNextOrderId() {
        return ++nextOrderId;
    }

    private Long getNextReservationsId() {
        return ++nextReservationsId;
    }

    private Long getNextRoomId() {
        return ++nextRoomId;
    }

    private Long getNextUserId() {
        return ++nextUserId;
    }




}
