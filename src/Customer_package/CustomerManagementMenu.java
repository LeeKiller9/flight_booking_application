package Customer_package;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerManagementMenu {
    private CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();

    public void displayMenu() {
        System.out.println("=============================================");
        System.out.println("||    ====== Customer Management ======    ||");
        System.out.println("|| 1: Search customer by phone number      ||");
        System.out.println("|| 2: Display all customers                ||");
        System.out.println("|| 3: Display all Gold customer            ||");
//        System.out.println("|| 4: Display all Silver customer          ||");
//        System.out.println("|| 5: Display all Bronze customer          ||");
        System.out.println("|| 6: Display customers who buy the most   ||");
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
                    case 1 -> searchCustomerByPhone();
                    case 2 -> displayAllCustomers();
                    case 3 -> displayGoldCustomers();
//                    case 4 -> searchFlightByTripAndDate();
//                    case 5 -> removeFlight();
                    case 6 -> displayCustomersBuyMost();
                    case 0 -> saveCustomers();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong Format");
        }
    }

    public void searchCustomerByPhone() {
        System.out.println("Search customer by phone number");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer's phone number");
        String phone = scanner.nextLine();
        Customer searchCustomer = customerManagement.searchByPhone(phone);
        if (searchCustomer != null) {
            System.out.println(searchCustomer);
            System.out.println("Search customer completed");
        } else {
            System.out.println("Not found customer");
        }
    }

    public void displayAllCustomers() {
        System.out.println("Display all customers");
        if (!customerManagement.displayAll().equals("")) {
            System.out.println("Name - Phone number - Email - Level - Total Spending" + "\n");
            System.out.println(customerManagement.displayAll());
            System.out.println("Display all customers completed");
        } else {
            System.out.println("No customer in list");
        }
    }

    public void displayGoldCustomers() {
        System.out.println("Display gold customers");
        if (!customerManagement.displayGoldCustomers().equals("")) {
            System.out.println("Name - Phone number - Email - Level - Total Spending");
            System.out.println(customerManagement.displayGoldCustomers());
            System.out.println("Display gold customers completed");
        } else {
            System.out.println("No customer in list");
        }
    }

    public void displayCustomersBuyMost() {
        System.out.println("Display customers who buy most");
        if (!customerManagement.displayCustomersBuyMost().equals("")) {
            System.out.println("Name - Phone number - Email - Level - Total Spending");
            System.out.println(customerManagement.displayCustomersBuyMost());
            System.out.println("Display customers who buy most completed");
        } else {
            System.out.println("No customer in list");
        }
    }

    public void saveCustomers() {
        customerManagement.saveCustomerList();
    }
}
