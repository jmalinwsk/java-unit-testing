package services;

import models.Reservation;

public class ReservationService {
    public boolean reservationValidation(Reservation reservation) {
        if(reservation != null &&
            reservation.getRoom() != null &&
            reservation.getUser() != null &&
            reservation.getStartDate().isBefore(reservation.getEndDate()))
            return true;
        else return false;
    }
}
