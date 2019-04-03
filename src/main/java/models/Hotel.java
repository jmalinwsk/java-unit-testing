package models;

import database.Database;
import org.joda.time.LocalTime;

import java.io.IOException;

public class Hotel {
    private Integer id;
    private String name;
    private LocalTime openHour;
    private LocalTime closeHour;

    public Hotel() {}

    public Hotel(String name, LocalTime openHour, LocalTime closeHour) {
        this.name = name;
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getOpenHour() {
        return openHour;
    }

    public void setOpenHour(LocalTime openHour) {
        this.openHour = openHour;
    }

    public LocalTime getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(LocalTime closeHour) {
        this.closeHour = closeHour;
    }

    public void addHotelToDatabase(Database database, Hotel hotel) {
        Integer id = database.getNextHotelId();
        hotel.setId(id);

        database.getHotels().put(id, hotel);
    }
}
