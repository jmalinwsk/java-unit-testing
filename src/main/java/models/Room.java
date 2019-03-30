package models;

public class Room {
    private Integer id;
    private Integer number;
    private Hotel hotel;

    public Room() {}

    public Room(Integer id, Hotel hotel, Integer number) {
        this.id = id;
        this.hotel = hotel;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
