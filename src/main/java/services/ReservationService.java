package services;

import database.Database;
import models.Reservation;
import models.User;

import java.util.HashMap;

public class ReservationService {
    public boolean reservationValidation(Reservation reservation) {
        if(reservation != null &&
            reservation.getRoom() != null &&
            reservation.getUser() != null &&
            reservation.getStartDate().isBefore(reservation.getEndDate()))
            return true;
        else return false;
    }

    public HashMap<Integer, Reservation> getReservationsOfUser(Database database, User user) {
        return null;
    }
}
