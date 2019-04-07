package services;

import database.Database;
import models.Reservation;
import models.User;
import utils.ReservationUtils;

import java.time.DateTimeException;
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

    public void addReservationToDatabase(Database database, Reservation newReservation) {
        if(reservationValidation(newReservation)) {
            if(database != null &&
                    database.getUsers().containsValue(newReservation.getUser()) &&
                    database.getRooms().containsValue(newReservation.getRoom())) {
                if(!database.getReservations().isEmpty()) {
                    boolean flag = false;
                    for (Reservation r : database.getReservations().values()) {
                        if((ReservationUtils.isContainedIn(newReservation, r) ||
                        ReservationUtils.isEqualTo(newReservation, r) ||
                        ReservationUtils.hasAProductOfSets(newReservation, r)) &&
                        ReservationUtils.ifRoomIsInTheSameHotel(newReservation, r) &&
                        !ReservationUtils.hasMinutesInDate(newReservation)) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) {
                        Integer id = database.getNextReservationId();
                        newReservation.setId(id);

                        String identificator = newReservation.getStartDate().toString() +
                                newReservation.getEndDate().toString() +
                                newReservation.getRoom().getNumberOfRoom() +
                                newReservation.getRoom().getHotel().getName();
                        newReservation.setIdentificator(identificator);
                    database.getReservations().put(id, newReservation);
                    } else throw new DateTimeException("Selected room in this date and time is reserved " +
                            "by other person!");
                } else {
                    Integer id = database.getNextReservationId();
                    newReservation.setId(id);
                    database.getReservations().put(id, newReservation);
                }
            } else throw new NullPointerException();
        } else throw new IllegalArgumentException();
    }

    public HashMap<Integer, Reservation> getReservationsOfUser(Database database, User user) {
        if (user != null && database != null) {
            HashMap<Integer, Reservation> reservationsOfUser = new HashMap<>();
            int counter = 1;
            for (Reservation r : database.getReservations().values())
                if (r.getUser() == user) {
                    reservationsOfUser.put(counter, r);
                    counter++;
                }
            return reservationsOfUser;
        } else throw new NullPointerException();
    }
}
