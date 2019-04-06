package services;

import database.Database;
import models.Reservation;
import models.User;

import java.util.HashMap;

public class ReservationService {
    public boolean reservationValidation(Reservation reservation) {
        if (reservation != null &&
                reservation.getRoom() != null &&
                reservation.getUser() != null &&
                reservation.getStartDate().isBefore(reservation.getEndDate()))
            return true;
        else return false;
    }

    public void addReservationToDatabase(Database database, Reservation reservation) {

    }

    public HashMap<Integer, Reservation> getReservationsOfUser(Database database, User user) {
        HashMap<Integer, Reservation> reservationsOfUser = new HashMap<>();
        int counter = 0;
        for (Reservation r : database.getReservations().values())
            if (r.getUser() == user) {
                reservationsOfUser.put(counter, r);
                counter++;
            }
        return reservationsOfUser;
    }
}
