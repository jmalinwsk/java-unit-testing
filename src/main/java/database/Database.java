package database;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import models.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalTime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Database {
    private HashMap<Integer, Hotel> hotels;
    private HashMap<Integer, Order> orders;
    private HashMap<Integer, Reservation> reservations;
    private HashMap<Integer, Room> rooms;
    private HashMap<Integer, User> users;

    public Database() {
        hotels = new HashMap<>();
        orders = new HashMap<>();
        reservations = new HashMap<>();
        rooms = new HashMap<>();
        users = new HashMap<>();
    }

    /** saving database to the file */
    public int serializeDatabase(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.writeValue(new File("target/" + filename + ".json"), this);

        return 0;
    }

    /** loading database from file */
    public static Database deserializeDatabase(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());

        return objectMapper.readValue(new File("target/" + filename + ".json"), Database.class);
    }

    public HashMap<Integer, Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(HashMap<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Integer, Order> orders) {
        this.orders = orders;
    }

    public HashMap<Integer, Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(HashMap<Integer, Reservation> reservations) {
        this.reservations = reservations;
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Integer, User> users) {
        this.users = users;
    }
}
