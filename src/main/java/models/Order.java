package models;

public class Order {
    private Integer id;
    private User user;
    private Reservation reservation;

    public Order() {}

    public Order(Integer id, User user, Reservation reservation) {
        this.id = id;
        this.user = user;
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
