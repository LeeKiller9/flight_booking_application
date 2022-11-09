package Booking_package;

import CommonTask_package.CommonTask;
import Customer_package.CustomerManagement;
import Export_package.*;
import Flight_package.Flight;
import Flight_package.FlightManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingManagement extends CommonTask<Booking> {
    private final String FILE_PATH = "bookings.csv";
    private FlightManagement flightManagement = FlightManagement.getFlightManagement();

    private CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();

    private List<Booking> bookingList;
    private static BookingManagement bookingManagement = new BookingManagement();

    public static BookingManagement getBookingManagement() {
        return bookingManagement;
    }

    private BookingManagement() {
        bookingList = new ArrayList<>();
        super.readFromFile(FILE_PATH, bookingList);
    }

    public void addBookingToList(int flightID, String customerName, String customerPhone, String customerEmail, HashMap<String, Integer> map) {
        int bookingID = 0;
        boolean flag = false;
        while (!flag) {
            bookingID = (int) (100 + Math.random() * 101);
            flag = true;
            for (Booking booking : bookingList) {
                if (bookingID == booking.getBookingID()) {
                    flag = false;
                    break;
                }
            }
        }
        Booking newBooking = new Booking(bookingID, flightID, customerName, customerPhone, customerEmail);
        newBooking.setMapSeat(map);
        newBooking.setTotal(getTotal(newBooking));
        bookingList.add(newBooking);
        super.saveToFile(FILE_PATH, bookingList);
        flightManagement.updateSeat(flightID, map);
        customerManagement.updateTotalSpending(customerName, customerPhone, customerEmail, newBooking.getTotal());
    }

    @Override
    public String getStringFromObj(Booking obj, String nameOfVariable) {
        return null;
    }

    @Override
    public int getNumberFromObj(Booking obj, String nameOfVariable) {
        return 0;
    }

    @Override
    public Booking handleLine(String line) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] str = line.split(",");
        for (int i = 6; i < str.length; i += 2) {
            map.put(str[i], Integer.parseInt(str[i + 1]));
        }
        Booking newBooking = new Booking(Integer.parseInt(str[0]), Integer.parseInt(str[1]), str[2], str[3], str[4], Double.parseDouble(str[5]));
        newBooking.setMapSeat(map);
        return newBooking;
    }

    public void saveBookingToList() {
        super.saveToFile(FILE_PATH, bookingList);
    }

    public String displayAll() {
        return super.displayString(bookingList);
    }

    public double getSubTotal(String key, int qty, int flightID) {
        Flight flight = searchFlightByID(flightID);
        double priceVIP = flight.getCostVIPSeat();
        double priceCommon = flight.getCostCommonSeat();
        if (key.equals("VIP")) {
            return qty * priceVIP;
        } else {
            return qty * priceCommon;
        }
    }

    public double getTotal(Booking booking) {
        HashMap<String, Integer> map = booking.getMapSeat();
        int flightID = booking.getFlightID();
        double total = 0;
        for (Map.Entry<String, Integer> s : map.entrySet()) {
            total += getSubTotal(s.getKey(), s.getValue(), flightID);
        }
        return total;
    }

    public Booking searchByID(int id) {
        for (Booking booking : bookingList) {
            if (booking.getBookingID() == id) {
                return booking;
            }
        }
        return null;
    }

    public List<Flight> searchSuitableFlight(String fromPlace, String toPlace, String flightDate) {
        return flightManagement.searchFlightByTripAndDate(fromPlace, toPlace, flightDate);
    }

    public String displaySuitableFlight(List<Flight> list) {
        String str = "";
        for (Flight flight : list) {
            str += flight + "\n";
        }
        return str;
    }

    public Flight searchFlightByID(int id) {
        return flightManagement.searchByID(id);
    }

    public boolean checkValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]\\w+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        boolean isValidEmail = matcher.matches();
        return isValidEmail;
    }

    public void getPayAndSendTicketMethod(String payMethod, String sendTicketMethod) {
        if (payMethod.equals("PayPal")) {
            PayMethod payMethod1 = new PayPal();
            payMethod1.getPayMethod();
        } else if (payMethod.equals("Credit")) {
            PayMethod payMethod1 = new CreditDebitCard();
            payMethod1.getPayMethod();
        } else if (payMethod.equals("Cash")) {
            PayMethod payMethod1 = new CashMethod();
            payMethod1.getPayMethod();
        }
        if (sendTicketMethod.equals("EMS")) {
            SendTicketMethod sendTicketMethod1 = new SendViaEMS();
            sendTicketMethod1.getSendTicketMethod();
        } else if (sendTicketMethod.equals("Email")) {
            SendTicketMethod sendTicketMethod1 = new SendViaEmail();
            sendTicketMethod1.getSendTicketMethod();
        }
    }
}