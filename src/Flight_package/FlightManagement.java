package Flight_package;

import CommonTask_package.CommonTask;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManagement extends CommonTask<Flight> {
    private List<Flight> flightList;
    private final String FILE_PATH = "flights.csv";

    private static FlightManagement flightManagement = new FlightManagement();

    public FlightManagement() {
        this.flightList = new ArrayList<>();
        super.readFromFile(FILE_PATH, flightList);
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public static FlightManagement getFlightManagement() {
        return flightManagement;
    }

    public void addFlightToList(int flightID, String fromPlace, String toPlace, LocalDate flightDate, LocalTime flightTime, int vipSeats, double vipSeatCost, int commonSeats, double commonSeatCost) {
        Flight flight = new Flight(flightID, fromPlace, toPlace, flightDate, flightTime, vipSeats, vipSeatCost, commonSeats, commonSeatCost);
        flightList.add(flight);
        super.saveToFile(FILE_PATH, flightList);
    }

    public void changeInformationFlight(Flight flight, String idStr, String fromPlace, String toPlace, String flightDateStr, String flightTimeStr, String vipSeatStr, String commonSeatStr) {
        if (!idStr.equals("")) {
            flight.setFlightID(Integer.parseInt(idStr));
        }
        if (!fromPlace.equals("")) {
            flight.setFromPlace(fromPlace);
        }
        if (!toPlace.equals("")) {
            flight.setToPlace(toPlace);
        }
        if (!flightDateStr.equals("")) {
            LocalDate flightDate = LocalDate.parse(flightDateStr);
            flight.setFlightDate(flightDate);
        }
        if (!flightTimeStr.equals("")) {
            LocalTime flightTime = LocalTime.parse(flightTimeStr);
            flight.setFlightTime(flightTime);
        }
        if (!vipSeatStr.equals("")) {
            flight.setAvailableVIPSeats(Integer.parseInt(vipSeatStr));
        }
        if (!commonSeatStr.equals("")) {
            flight.setAvailableCommonSeats(Integer.parseInt(commonSeatStr));
        }
        super.saveToFile(FILE_PATH, flightList);
    }

    public List<Flight> searchFlightByTripAndDate(String fromPlace, String toPlace, String flightDate) {
        List<Flight> searchList = new ArrayList<>();
        for (Flight flight : flightList) {
            if ((flight.getFromPlace().equals(fromPlace)) && (flight.getToPlace().equals(toPlace)) && ((flight.getFlightDate().toString()).equals(flightDate))) {
                searchList.add(flight);
            }
        }
        return searchList;
    }

    public Flight searchByID(int id) {
        return super.searchObjectByNumberAndReturnObject(id, "flightID", flightList);
    }

    public void removeFlightByID(Flight removeFlight) {
        flightList.remove(removeFlight);
        super.saveToFile(FILE_PATH, flightList);
    }

    @Override
    public String getStringFromObj(Flight obj, String nameOfVariable) {
        if (nameOfVariable.equals("fromPlace")) {
            return obj.getFromPlace();
        } else if (nameOfVariable.equals("toPlace")) {
            return obj.getToPlace();
        }
        return null;
    }

    @Override
    public int getNumberFromObj(Flight obj, String nameOfVariable) {
        if (nameOfVariable.equals("flightID")) {
            return obj.getFlightID();
        }
        return -1;
    }

    public Flight handleLine(String line) {
        String[] str = line.split(",");
        return new Flight(Integer.parseInt(str[0]), str[1], str[2], LocalDate.parse(str[3]), LocalTime.parse(str[4]), Integer.parseInt(str[5]), Double.parseDouble(str[6]), Integer.parseInt(str[7]), Double.parseDouble(str[8]));
    }

    public String displayAll() {
        return displayString(flightList);
    }


    public void updateSeat(int flightID, HashMap<String, Integer> map) {
        Flight flight = searchByID(flightID);
        for (Map.Entry<String, Integer> s : map.entrySet()) {
            if (s.getKey().equals("VIP")) {
                flight.setAvailableVIPSeats(flight.getAvailableVIPSeats() - s.getValue());
            } else {
                flight.setAvailableCommonSeats(flight.getAvailableCommonSeats() - s.getValue());
            }
        }
        super.saveToFile(FILE_PATH, flightList);
    }

    public void saveFlights() {
        super.saveToFile(FILE_PATH,flightList);
    }
}
