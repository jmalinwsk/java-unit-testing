package services;

import database.Database;
import models.Room;

public class RoomService {
    /** Validation of room.
     * @return true if validation is valid or false if validation is invalid
     */
    public boolean roomValidation(Room room) {
        if(room != null &&
            room.getNumberOfRoom() > 0 &&
            room.getAmountOfPeople() > 0)
            return true;
        else return false;
    }

    public void addRoomToDatabase(Database database, Room room) {

    }
}
