package Models;

import org.joda.time.LocalTime;

public class Reservation {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean ifFree = false;
    private Room room;

    public Reservation(LocalTime startTime, LocalTime endTime, Room room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
