package services;

import database.Database;
import models.*;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserService userService;
    private Database database;

    private static int counter = 0;
    private static String databaseFilename = "UserService";

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        database = new Database();
    }

    @Test
    public void getOrdersOfUserTest() throws IOException {
        User user = new User(1, "test@test.pl");
        User user2 = new User(2, "test2@test2.pl");
        Hotel hotel = new Hotel(1, "Sample Hotel", new LocalTime(8), new LocalTime(22));
        Room room = new Room(1, hotel, 516);
        Reservation reservation = new Reservation(1, DayOfWeek.MONDAY, new LocalTime(11), DayOfWeek.TUESDAY, new LocalTime(11), room);
        Reservation reservation2 = new Reservation(2, DayOfWeek.TUESDAY, new LocalTime(11), DayOfWeek.WEDNESDAY, new LocalTime(11), room);
        Reservation reservation3 = new Reservation(3, DayOfWeek.WEDNESDAY, new LocalTime(11), DayOfWeek.THURSDAY, new LocalTime(11), room);
        Order order1 = new Order(1, user, reservation);
        Order order2 = new Order(2, user, reservation2);
        Order order3 = new Order(3, user2, reservation3);
        HashMap<Integer, Order> orders = new HashMap<>();
        orders.put(1, order1);
        orders.put(2, order2);
        orders.put(3, order3);
        database.setOrders(orders);
        database.serializeDatabase(databaseFilename + counter);

        HashMap<Integer, Order> ordersOfUser = userService.getOrdersOfUser(databaseFilename + counter, user2);

        assertAll(
                () -> assertEquals(user2.getId(), ordersOfUser.get(1).getUser().getId()),
                () -> assertEquals(user2.getEmail(), ordersOfUser.get(1).getUser().getEmail()),
                () -> assertEquals(reservation3.getId(), ordersOfUser.get(1).getReservation().getId()),
                () -> assertEquals(reservation3.getStartTime(), ordersOfUser.get(1).getReservation().getStartTime()),
                () -> assertEquals(reservation3.getEndTime(), ordersOfUser.get(1).getReservation().getEndTime()),
                () -> assertEquals(reservation3.getStartDate(), ordersOfUser.get(1).getReservation().getStartDate()),
                () -> assertEquals(reservation3.getEndDate(), ordersOfUser.get(1).getReservation().getEndDate()),
                () -> assertEquals(reservation3.getIfFree(), ordersOfUser.get(1).getReservation().getIfFree()),
                () -> assertEquals(reservation3.getRoom().getHotel().getId(), ordersOfUser.get(1).getReservation().getRoom().getHotel().getId()),
                () -> assertEquals(reservation3.getRoom().getHotel().getName(), ordersOfUser.get(1).getReservation().getRoom().getHotel().getName()),
                () -> assertEquals(reservation3.getRoom().getHotel().getOpenHour(), ordersOfUser.get(1).getReservation().getRoom().getHotel().getOpenHour()),
                () -> assertEquals(reservation3.getRoom().getHotel().getCloseHour(), ordersOfUser.get(1).getReservation().getRoom().getHotel().getCloseHour())
                );

        counter++;
    }

    @AfterEach
    public void tearTown() {
        userService = null;
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        for(int i=0; i<counter; i++)
            Files.delete(Paths.get("target/" + databaseFilename + i + ".json"));
    }
}
