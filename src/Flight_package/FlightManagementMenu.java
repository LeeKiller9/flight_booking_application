package Flight_package;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlightManagementMenu {
    private FlightManagement flightManagement = FlightManagement.getFlightManagement();

    public void displayMenu() {
        System.out.println("=============================================");
        System.out.println("||     ====== Flight Management ======     ||");
        System.out.println("|| 1: Add new flight                       ||");
        System.out.println("|| 2: Change information of flight by ID   ||");
        System.out.println("|| 3: Search flight by ID                  ||");
        System.out.println("|| 4: Search flight by trip and date       ||");
        System.out.println("|| 5: Remove flight by ID                  ||");
        System.out.println("|| 6: Display all flights                  ||");
        System.out.println("|| 0: Exit program                         ||");
        System.out.println("=============================================");
    }

    public void handleMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = -1;
            while (choice != 0) {
                displayMenu();
                System.out.println("Enter choice");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> addNewFly();
                    case 2 -> changeInformationOfFlight();
                    case 3 -> searchFlightByID();
                    case 4 -> searchFlightByTripAndDate();
                    case 5 -> removeFlight();
                    case 6 -> displayAllFlights();
                    case 0 -> saveFlights();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Format");
        }
    }

    public void addNewFly() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new flight");
        System.out.println("Enter flight's ID");
        int id = scanner.nextInt();
        System.out.println("Enter from place");
        String fromPlace = scanner.nextLine();
        fromPlace = scanner.nextLine();
        System.out.println("Enter to place");
        String toPlace = scanner.nextLine();
        System.out.println("Enter flight's date (yyyy-mm-dd)");
        String flightDateStr = scanner.nextLine();
        LocalDate flightDate = LocalDate.parse(flightDateStr);
        System.out.println("Enter flight's time (hh:mm)");
        String flightTimeStr = scanner.nextLine();
        LocalTime flightTime = LocalTime.parse(flightTimeStr);
        System.out.println("Enter flight's VIP seats amount");
        int vipSeats = scanner.nextInt();
        System.out.println("Enter flight's VIP seat cost");
        double vipSeatCost = scanner.nextDouble();
        System.out.println("Enter flight's common seats amount");
        int commonSeats = scanner.nextInt();
        System.out.println("Enter flight's common seat cost");
        double commonSeatCost = scanner.nextDouble();
        flightManagement.addFlightToList(id, fromPlace, toPlace, flightDate, flightTime, vipSeats, vipSeatCost, commonSeats, commonSeatCost);
        System.out.println("Add new flight completed");
    }

    public void changeInformationOfFlight() {
        System.out.println("Change information of flight by ID");
        Flight searchFlight = searchByID();
        if (searchFlight != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter flight's ID");
            String idStr = scanner.nextLine();
            if (idStr.equals("")) {
                System.out.println("Keep current value");
            }

            System.out.println("Enter from place");
            String fromPlace = scanner.nextLine();
            if (fromPlace.equals("")) {
                System.out.println("Keep current value");
            }

            System.out.println("Enter to place");
            String toPlace = scanner.nextLine();
            if (toPlace.equals("")) {
                System.out.println("Keep current value");
            }

            System.out.println("Enter flight's date (yyyy-mm-dd)");
            String flightDateStr = scanner.nextLine();
            if (flightDateStr.equals("")) {
                System.out.println("Keep current value");
            }

            System.out.println("Enter flight's time (hh:mm)");
            String flightTimeStr = scanner.nextLine();
            if (flightTimeStr.equals("")) {
                System.out.println("Keep current value");
            }

            System.out.println("Enter flight's VIP seats amount");
            String vipSeatStr = scanner.nextLine();
            if (vipSeatStr.equals("")) {
                System.out.println("Keep current value");
            }

            System.out.println("Enter flight's common seats amount");
            String commonSeatStr = scanner.nextLine();
            if (commonSeatStr.equals("")) {
                System.out.println("Keep current value");
            }

            flightManagement.changeInformationFlight(searchFlight, idStr, fromPlace, toPlace, flightDateStr, flightTimeStr, vipSeatStr, commonSeatStr);
            System.out.println("Update information completed");
        } else {
            System.out.println("Not found flight");
        }
    }

    public Flight searchByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight's ID");
        int id = scanner.nextInt();
        return flightManagement.searchByID(id);
    }

    public void searchFlightByID() {
        System.out.println("Search flight by ID");
        Flight searchFlight = searchByID();
        if (searchFlight != null) {
            System.out.println(searchFlight);
            System.out.println("Search flight completed");
        } else {
            System.out.println("Not found flight");
        }
    }

    public void searchFlightByTripAndDate() {
        System.out.println("Search flight by trip and date");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter from place");
        String fromPlace = scanner.nextLine();
        System.out.println("Enter to place");
        String toPlace = scanner.nextLine();
        System.out.println("Enter flight's date");
        String flightDate = scanner.nextLine();
        List<Flight> searchList = flightManagement.searchFlightByTripAndDate(fromPlace, toPlace, flightDate);
        if (searchList != null) {
            System.out.println("Flight's ID - From Place - To Place - Flight's Date - Flight's Time - VIP Seats - Common Seats");
            System.out.println(flightManagement.displayString(searchList));
            System.out.println("Display all flights completed");
        } else {
            System.out.println("Not found flight");
        }
    }

    public void removeFlight() {
        System.out.println("Remove flight by ID");
        Flight removeFlight = searchByID();
        if (removeFlight != null) {
            flightManagement.removeFlightByID(removeFlight);
            System.out.println("Remove flight completed");
        } else {
            System.out.println("Not found flight");
        }
    }

    public void saveFlights() {
        flightManagement.saveFlights();
    }

    public void displayAllFlights() {
        System.out.println("Display all flights");
        if (!flightManagement.displayAll().equals("")) {
            System.out.println("Flight's ID - From Place - To Place - Flight's Date - Flight's Time - VIP Seats - Common Seats");
            System.out.println(flightManagement.displayAll());
            System.out.println("Display all flights completed");
        } else {
            System.out.println("No flight in list");
        }
    }
}
