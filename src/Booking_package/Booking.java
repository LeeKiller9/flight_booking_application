package Booking_package;

import java.util.HashMap;
import java.util.Map;

public class Booking {
    private int bookingID;
    private int flightID;
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    private double total;
    private HashMap<String, Integer> mapSeat;

    public Booking(int bookingID, int flightID, String customerName, String customerPhone, String customerEmail) {
        this.bookingID = bookingID;
        this.flightID = flightID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    public Booking(int bookingID, int flightID, String customerName, String customerPhone, String customerEmail, double total) {
        this.bookingID = bookingID;
        this.flightID = flightID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.total = total;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public HashMap<String, Integer> getMapSeat() {
        return mapSeat;
    }

    public void setMapSeat(HashMap<String, Integer> mapSeat) {
        this.mapSeat = mapSeat;
    }

    @Override
    public String toString() {
        String str = bookingID + "," + flightID + "," + customerName + "," + customerPhone + "," + customerEmail + "," + total;
        for (Map.Entry<String, Integer> e : this.mapSeat.entrySet()) {
            str += "," + e.getKey() + "," + e.getValue();
        }
        return str;
    }
}
