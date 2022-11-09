package Customer_package;

import CommonTask_package.CommonTask;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagement extends CommonTask<Customer> {

    private List<Customer> customerList;
    private final double SILVER_LEVEL = 2000;
    private final double GOLD_LEVEL = 5000;

    private final String FILE_PATH = "customers.csv";

    private static CustomerManagement customerManagement = new CustomerManagement();

    public static CustomerManagement getCustomerManagement() {
        return customerManagement;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public CustomerManagement() {
        this.customerList = new ArrayList<>();
        super.readFromFile(FILE_PATH, customerList);
    }

    public Customer searchByPhone(String phone) {
        return super.searchObjectByStringAndReturnObject(phone, "phoneNumber", customerList);
    }

    @Override
    public String getStringFromObj(Customer obj, String nameOfVariable) {
        if (nameOfVariable.equals("phoneNumber")) {
            return obj.getPhoneNumber();
        }
        return null;
    }

    @Override
    public int getNumberFromObj(Customer obj, String nameOfVariable) {
        return 0;
    }

    @Override
    public Customer handleLine(String line) {
        String[] str = line.split(",");
        return new Customer(str[0], str[1], str[2], str[3], Double.parseDouble(str[4]));
    }

    public String displayAll() {
        return super.displayString(customerList);
    }

    public String displayGoldCustomers() {
        List<Customer> goldList = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.getLevel().equals("Gold")) {
                goldList.add(customer);
            }
        }
        return super.displayString(goldList);
    }

    public void saveCustomerList() {
        super.saveToFile(FILE_PATH, customerList);
    }

    public String displayCustomersBuyMost() {
        List<Customer> customersBuyMostList = new ArrayList<>();
        double max = 0;
        for (Customer customer : customerList) {
            if (customer.getTotalSpending() > max) {
                max = customer.getTotalSpending();
            }
        }
        for (Customer customer : customerList) {
            if (customer.getTotalSpending() == max) {
                customersBuyMostList.add(customer);
            }
        }
        return super.displayString(customersBuyMostList);
    }

    public void updateTotalSpending(String nameCustomer, String phoneCustomer, String emailCustomer, double total) {
        Customer customer = searchByPhone(phoneCustomer);
        if (customer != null) {
            customer.setTotalSpending(customer.getTotalSpending() + total);
            updateCustomerLevel(customer);
        } else {
            addNewCustomer(nameCustomer, phoneCustomer, emailCustomer, total);
        }
        super.saveToFile(FILE_PATH, customerList);
    }

    public void addNewCustomer(String nameCustomer, String phoneCustomer, String emailCustomer, double total) {
        Customer newCustomer = new Customer(nameCustomer, phoneCustomer, emailCustomer);
        newCustomer.setTotalSpending(total);
        updateCustomerLevel(newCustomer);
        customerList.add(newCustomer);
    }

    public void updateCustomerLevel(Customer customer) {
        if (customer.getTotalSpending() >= SILVER_LEVEL) {
            customer.setLevel("Silver");
        } else if (customer.getTotalSpending() >= GOLD_LEVEL) {
            customer.setLevel("Gold");
        }
    }
}
