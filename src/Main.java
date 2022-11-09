import Booking_package.BookingManagementMenu;
import Customer_package.CustomerManagementMenu;
import Flight_package.FlightManagementMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        FlightManagementMenu flyManagementMenu = new FlightManagementMenu();
        CustomerManagementMenu customerManagementMenu = new CustomerManagementMenu();
        BookingManagementMenu bookingManagementMenu = new BookingManagementMenu();
        int choice = -1;
        while (choice != 0) {
            System.out.println("====================================");
            System.out.println("|| === Book Ticket Management === ||");
            System.out.println("|| 1: Flight Management Menu      ||");
            System.out.println("|| 2: Customer Management Menu    ||");
            System.out.println("|| 3: Booking Management Menu     ||");
            System.out.println("|| 0: Exit program                ||");
            System.out.println("====================================");
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter choice");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> flyManagementMenu.handleMenu();
                    case 2 -> customerManagementMenu.handleMenu();
                    case 3 -> bookingManagementMenu.handleMenu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Format");
            }
        }
    }
}