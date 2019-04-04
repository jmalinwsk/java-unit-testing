package services;

import database.Database;
import models.Hotel;

import java.time.DateTimeException;

public class HotelService {
    /** Validation of hotel.
     * @param hotel variable with hotel
     * @return true if validation is valid
     * @throws DateTimeException when open hour is after close hour
     * @throws NullPointerException when hotel variable or hotel name is null
     */
    public boolean hotelValidation(Hotel hotel) {
        if(hotel != null &&
                hotel.getName() != null &&
                hotel.getOpenHour() != null &&
                hotel.getCloseHour() != null)
                if(!hotel.getName().equals(""))
                    if(hotel.getOpenHour().isBefore(hotel.getCloseHour()))
                        return true;
                    else throw new DateTimeException("Open hour is after close hour!");
                else throw new IllegalArgumentException();
        else throw new NullPointerException();
    }

    /** adding hotel to the database
     * @param database variable of database
     * @param hotel variable with new hotel
     * @throws IllegalArgumentException when validation of hotel is wrong
     */
    public void addHotelToDatabase(Database database, Hotel hotel) {
        if (database != null) {
            Integer id = database.getNextHotelId();
            hotel.setId(id);

            hotelValidation(hotel);
            database.getHotels().put(id, hotel);
        }
        else throw new NullPointerException();
    }
}
