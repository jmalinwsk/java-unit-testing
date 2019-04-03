package models;

import database.Database;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalTime;

import java.time.DayOfWeek;

public class Reservation {
    private Integer id;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek startDate;
    private DayOfWeek endDate;
    private Boolean ifFree = false;
    private Room room;

    public Reservation() {}

    public Reservation(DayOfWeek startDate, LocalTime startTime, DayOfWeek endDate, LocalTime endTime, Room room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getStartDate() {
        return startDate;
    }

    public void setStartDate(DayOfWeek startDate) {
        this.startDate = startDate;
    }

    public DayOfWeek getEndDate() {
        return endDate;
    }

    public void setEndDate(DayOfWeek endDate) {
        this.endDate = endDate;
    }

    public Boolean getIfFree() {
        return ifFree;
    }

    public void setIfFree(Boolean ifFree) {
        this.ifFree = ifFree;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void addReservationToDatabase(Database database, Reservation reservation) {
        Integer id = database.getNextReservationId();
        reservation.setId(id);

        database.getReservations().put(id, reservation);
    }
}
