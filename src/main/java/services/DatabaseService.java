package services;

import database.Database;
import models.Reservation;
import models.User;

import java.util.HashMap;

public class DatabaseService {

    public Integer theMostCrowdedDayOfTheWeek(Database database) {
        if(database != null) {
            HashMap<Integer, Integer> daysOfWeek = new HashMap<>();
            daysOfWeek.put(1, 0);
            daysOfWeek.put(2, 0);
            daysOfWeek.put(3, 0);
            daysOfWeek.put(4, 0);
            daysOfWeek.put(5, 0);
            daysOfWeek.put(6, 0);
            daysOfWeek.put(7, 0);
            for (Reservation r : database.getReservations().values()) {
                int day = r.getStartDate().getDayOfWeek();
                int value = daysOfWeek.get(day);
                daysOfWeek.replace(day, value + 1);
            }
            int mostCrowdedDayOfTheWeek = 0;
            int value = 0;
            int i=1;
            for (Integer visits : daysOfWeek.values()) {
                if (visits > value) {
                    value = visits;
                    mostCrowdedDayOfTheWeek = i;
                }
                i++;
            }

            return mostCrowdedDayOfTheWeek;
        } else throw new NullPointerException();
    }
}
