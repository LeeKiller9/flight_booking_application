package Booking_package;

import Flight_package.Flight;

import java.util.*;

public class BookingManagementMenu {
    private BookingManagement bookingManagement = BookingManagement.getBookingManagement();

    public void displayMenu() {
        System.out.println("======================================");
        System.out.println("||  ===== Booking Management =====  ||");
        System.out.println("|| 1: Add new booking               ||");
        System.out.println("|| 2: Search booking by ID          ||");
        System.out.println("|| 3: Display all booking           ||");
        System.out.println("|| 4: Display cost of booking by ID ||");
        System.out.println("|| 5: Export booking by ID          ||");
        System.out.println("|| 0: Exit program                  ||");
        System.out.println("======================================");
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
                    case 1 -> addNewBooking();
                    case 2 -> searchBookingByID();
                    case 3 -> displayAllBooking();
                    case 4 -> displayTotalOfBookingByID();
                    case 5 -> exportBookingByID();
                    case 0 -> saveBookings();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Format");
        }
    }

    public List<Flight> findSuitableFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new booking");
        System.out.println("Enter from place");
        String fromPlace = scanner.nextLine();
        System.out.println("Enter to place");
        String toPlace = scanner.nextLine();
        System.out.println("Enter flight date (yyyy-mm-dd)");
        String flightDateStr = scanner.nextLine();
        return bookingManagement.searchSuitableFlight(fromPlace, toPlace, flightDateStr);
    }

    public void addNewBooking() {
        List<Flight> flightList = findSuitableFlight();
        if (flightList.size() != 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Suitable Flight");
            System.out.println("Flight's ID - From Place - To Place - Flight's Date - Flight's Time - VIP Seats - Common Seats");
            System.out.println(bookingManagement.displaySuitableFlight(flightList));
            int flightID = inputIDOfFlight(flightList);
            HashMap<String, Integer> map = inputTypeOfSeatAndAmount(flightID);
            System.out.println("Enter customer's name");
            String customerName = scanner.nextLine();
            System.out.println("Enter customer's phone number");
            String customerPhone = scanner.nextLine();
            String customerEmail;
            do {
                System.out.println("Enter customer's email");
                customerEmail = scanner.nextLine();
                if (!bookingManagement.checkValidEmail(customerEmail)) {
                    System.out.println("Email not valid");
                }
            } while (!bookingManagement.checkValidEmail(customerEmail));
            bookingManagement.addBookingToList(flightID, customerName, customerPhone, customerEmail, map);
        } else {
            System.out.println("Not found suitable flight");
        }
    }

    public int inputIDOfFlight(List<Flight> flightList) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        int flightID = -1;
        while (!flag) {
            System.out.println("Enter ID of flight");
            flightID = scanner.nextInt();
            for (Flight flight : flightList) {
                if (flightID == flight.getFlightID()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("ID not valid");
            }
        }
        return flightID;
    }

    public HashMap<String, Integer> inputTypeOfSeatAndAmount(int flightID) {
        Flight flight = bookingManagement.searchFlightByID(flightID);
        int vipSeat = flight.getAvailableVIPSeats();
        int commonSeat = flight.getAvailableCommonSeats();
        System.out.printf("VIP: %d seats  - Common: %d seats\n", vipSeat, commonSeat);
        HashMap<String, Integer> map = new HashMap<>();
        try {
            int choice = -1;
            while (choice != 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter type of seat and amount");
                System.out.println(" 1: VIP seat");
                System.out.println(" 2: Common seat");
                System.out.println(" 0: Exit");
                System.out.println("Enter choice");
                choice = scanner.nextInt();
                scanner.nextLine();
                String typeSeat = "";
                int amount = 0;
                switch (choice) {
                    case 1:
                        typeSeat = "VIP";
                        do {
                            System.out.println("Enter amount");
                            amount = scanner.nextInt();
                            if (amount > vipSeat) {
                                System.out.println("Amount not valid");
                            }
                        } while (amount > vipSeat);
                        map.put(typeSeat, amount);
                        System.out.println("Booked VIP: " + amount + " seat");
                        break;
                    case 2:
                        typeSeat = "Common";
                        do {
                            System.out.println("Enter amount");
                            amount = scanner.nextInt();
                            if (amount > commonSeat) {
                                System.out.println("Amount not valid");
                            }
                        } while (amount > commonSeat);
                        map.put(typeSeat, amount);
                        System.out.println("Booked Common: " + amount + " seat");
                        break;
                    case 0:
                        break;
                }
            }
        } catch (
                InputMismatchException e) {
            System.out.println("Wrong Format");
        }
        return map;
    }


    public void saveBookings() {
        bookingManagement.saveBookingToList();
    }

    public void displayAllBooking() {
        System.out.println("Display all bookings");
        if (!bookingManagement.displayAll().equals("")) {
            System.out.println("ID - Flight ID - Name - Phone - Email - Seat - Amount");
            System.out.println(bookingManagement.displayAll());
            System.out.println("Display all bookings completed");
        } else {
            System.out.println("No customer in list");
        }
    }

    public void displayTotalOfBookingByID() {
        System.out.println("Display total of booking by ID");
        Booking searchBooking = searchByID();
        System.out.println("Total: " + bookingManagement.getTotal(searchBooking));
    }

    public Booking searchByID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter booking's ID");
        int id = scanner.nextInt();
        return bookingManagement.searchByID(id);
    }

    public void searchBookingByID() {
        System.out.println("Search booking by ID");
        Booking searchBooking = searchByID();
        if (searchBooking != null) {
            System.out.println("ID - Flight ID - Name - Phone - Email - Seat - Amount");
            System.out.println(searchBooking);
            System.out.println("Search booking completed");
        } else {
            System.out.println("Not found booking");
        }
    }

    public void exportBookingByID() {
        System.out.println("Export booking by ID");
        Booking searchBooking = searchByID();
        if (searchBooking != null) {
            System.out.println("ID - Flight ID - Name - Phone - Email - Seat - Amount");
            System.out.println(searchBooking);
            System.out.println("Enter method paying");
        } else {
            System.out.println("Not found booking");
        }
    }
}