package services;

import models.Room;

public class RoomService {
    public boolean roomValidation(Room room) {
        if(room != null &&
            room.getNumberOfRoom() > 0 &&
            room.getAmountOfPeople() > 0)
            return true;
        else return false;
    }
}
