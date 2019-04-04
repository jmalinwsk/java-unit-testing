package services;

public class UserServiceTest {
    /*
    private static UserService userService;
    private static Database database;
    private static User user, user2;
    private static Hotel hotel;
    private static Room room;
    private static Reservation reservation, reservation2, reservation3;
    private static Order order, order2, order3;
    private static HashMap<Integer, Order> orders;
    private static HashMap<Integer, Order> ordersOfUser;

    private static int counter = 0;
    private static String databaseFilename = "UserService";

    @BeforeAll
    public static void setup() throws IOException {
        userService = new UserService();
        database = new Database();
        user = new User("test@test.pl");
        user2 = new User("test2@test2.pl");

        hotel = new Hotel("Sample Hotel", new LocalTime(8), new LocalTime(22));
        room = new Room(hotel, 516);
        reservation = new Reservation(DayOfWeek.MONDAY, new LocalTime(11), DayOfWeek.TUESDAY, new LocalTime(11), room);
        reservation2 = new Reservation(DayOfWeek.TUESDAY, new LocalTime(11), DayOfWeek.WEDNESDAY, new LocalTime(11), room);
        reservation3 = new Reservation(DayOfWeek.WEDNESDAY, new LocalTime(11), DayOfWeek.THURSDAY, new LocalTime(11), room);
        order = new Order(user, reservation);
        order2 = new Order(user, reservation2);
        order3 = new Order(user2, reservation3);
        orders = new HashMap<>();
        orders.put(1, order);
        orders.put(2, order2);
        orders.put(3, order3);
        database.setOrders(orders);
        database.serializeDatabase(databaseFilename + counter);
    }

    @Test
    @DisplayName("getting orders of user")
    public void getOrdersOfUserTest() throws IOException {
        ordersOfUser = userService.getOrdersOfUser(databaseFilename + counter, user2);

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
    }

    @Test
    @DisplayName("getting orders of user - database name is null")
    public void getOrdersOfUser2Test() {
        assertThrows(NullPointerException.class,
                () -> ordersOfUser = userService.getOrdersOfUser(null, user2));
    }

    @Test
    @DisplayName("getting orders of user - database name is wrong")
    public void getOrdersOfUser3Test() {
        assertThrows(NullPointerException.class,
                () -> ordersOfUser = userService.getOrdersOfUser("wrong database name", user2));
    }

    @Test
    @DisplayName("getting orders of user - user is null")
    public void getOrdersOfUser4Test() {
        assertThrows(NullPointerException.class,
                () -> ordersOfUser = userService.getOrdersOfUser(databaseFilename + counter, null));
    }

    @Test
    @DisplayName("getting orders of user - user doesn't exist in database")
    public void getOrdersOfUser5Test() {
        assertThrows(NullPointerException.class,
                () -> ordersOfUser = userService.getOrdersOfUser(databaseFilename + counter, new User(3, "ee@ee.pl")));
    }

    @Test
    @DisplayName("getting orders of user - user and database name are null")
    public void getOrdersOfUser6Test() {
        assertThrows(NullPointerException.class,
                () -> ordersOfUser = userService.getOrdersOfUser(null, null));
    }

    @AfterEach
    public void finalize() {
        userService = null;
    }

    @AfterAll
    public static void teardown() throws IOException {
        for(int i=0; i<counter; i++)
            Files.delete(Paths.get("target/" + databaseFilename + i + ".json"));
    }
     */
}
