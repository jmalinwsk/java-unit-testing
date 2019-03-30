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

    public Database(
            HashMap<Long, Hotel> hotels,
            HashMap<Long, Order> orders,
            HashMap<Long, Reservation> reservations,
            HashMap<Long, Room> rooms,
            HashMap<Long, User> users) {
        this.hotels = hotels;
        this.orders = orders;
        this.reservations = reservations;
        this.rooms = rooms;
        this.users = users;
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

    public HashMap<Long, Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(HashMap<Long, Hotel> hotels) {
        this.hotels = hotels;
    }

    public HashMap<Long, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Long, Order> orders) {
        this.orders = orders;
    }

    public HashMap<Long, Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(HashMap<Long, Reservation> reservations) {
        this.reservations = reservations;
    }

    public HashMap<Long, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<Long, Room> rooms) {
        this.rooms = rooms;
    }

    public HashMap<Long, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Long, User> users) {
        this.users = users;
    }

    public void setNextHotelId(Long nextHotelId) {
        this.nextHotelId = nextHotelId;
    }

    public void setNextOrderId(Long nextOrderId) {
        this.nextOrderId = nextOrderId;
    }

    public void setNextReservationsId(Long nextReservationsId) {
        this.nextReservationsId = nextReservationsId;
    }

    public void setNextRoomId(Long nextRoomId) {
        this.nextRoomId = nextRoomId;
    }

    public void setNextUserId(Long nextUserId) {
        this.nextUserId = nextUserId;
    }

    public Long getNextHotelId() {
        return ++nextHotelId;
    }

    public Long getNextOrderId() {
        return ++nextOrderId;
    }

    public Long getNextReservationsId() {
        return ++nextReservationsId;
    }

    public Long getNextRoomId() {
        return ++nextRoomId;
    }

    public Long getNextUserId() {
        return ++nextUserId;
    }
}
