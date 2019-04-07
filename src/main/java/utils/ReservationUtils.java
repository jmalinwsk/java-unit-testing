package utils;

import models.Reservation;
import org.joda.time.DateTime;

public class ReservationUtils {
    public static boolean hasMinutesInDate(Reservation reservation) {
        System.out.println();
        if(reservation.getStartDate().getMinuteOfDay()%60 != 0 ||
            reservation.getEndDate().getMinuteOfDay()%60 != 0)
            return true;
        else return false;
    }

    public static boolean isContainedIn(Reservation r1, Reservation r2) {
        return (r1.getStartDate().isAfter(r2.getStartDate()) &&
                r1.getEndDate().isBefore(r2.getEndDate()));
    }

    public static boolean isEqualTo(Reservation r1, Reservation r2) {
        return (r1.getStartDate().isEqual(r2.getStartDate()) &&
                r1.getEndDate().isEqual(r2.getEndDate()));
    }

    public static boolean ifRoomIsInTheSameHotel(Reservation r1, Reservation r2) {
        return (r1.getRoom().getId().equals(r2.getRoom().getId()) &&
                r1.getRoom().getHotel().getId().equals(r2.getRoom().getHotel().getId()));
    }

    //Czy a1 nalezy do a2-b2
    //I b1 nalezy do a2-b2
    //Jesli ktorys z tych warunków jest prawdziwy to 1 i 2 się przecinają
    public static boolean hasAProductOfSets(Reservation r1, Reservation r2) {
        DateTime temp = r1.getEndDate().minus(r2.getEndDate().minuteOfDay().get());
        return (r1.getStartDate().isAfter(temp) && r1.getEndDate().isBefore(temp) ||
                (r2.getStartDate().isAfter(temp) && r2.getEndDate().isBefore(temp)));
    }
}
