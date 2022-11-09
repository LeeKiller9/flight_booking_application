package Flight_package;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private int flightID;
    private String fromPlace;
    private String toPlace;
    private LocalDate flightDate;
    private LocalTime flightTime;
    private int availableVIPSeats;
    private double costVIPSeat;
    private int availableCommonSeats;
    private double costCommonSeat;


    public Flight(int flightID, String fromPlace, String toPlace, LocalDate flightDate, LocalTime flightTime, int availableVIPSeats, double costVIPSeat, int availableCommonSeats, double costCommonSeat) {
        this.flightID = flightID;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.availableVIPSeats = availableVIPSeats;
        this.costVIPSeat = costVIPSeat;
        this.availableCommonSeats = availableCommonSeats;
        this.costCommonSeat = costCommonSeat;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public LocalTime getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(LocalTime flightTime) {
        this.flightTime = flightTime;
    }

    public int getAvailableVIPSeats() {
        return availableVIPSeats;
    }

    public void setAvailableVIPSeats(int availableVIPSeats) {
        this.availableVIPSeats = availableVIPSeats;
    }

    public double getCostVIPSeat() {
        return costVIPSeat;
    }

    public void setCostVIPSeat(double costVIPSeat) {
        this.costVIPSeat = costVIPSeat;
    }

    public int getAvailableCommonSeats() {
        return availableCommonSeats;
    }

    public void setAvailableCommonSeats(int availableCommonSeats) {
        this.availableCommonSeats = availableCommonSeats;
    }

    public double getCostCommonSeat() {
        return costCommonSeat;
    }

    public void setCostCommonSeat(double costCommonSeat) {
        this.costCommonSeat = costCommonSeat;
    }

    @Override
    public String toString() {
        return flightID + "," + fromPlace + "," + toPlace + "," + flightDate + "," + flightTime + "," + availableVIPSeats + "," + costVIPSeat + "," + availableCommonSeats + "," + costCommonSeat;
    }
}
