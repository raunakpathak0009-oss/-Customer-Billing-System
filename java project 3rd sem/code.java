
import java.util.Scanner;

class Customer {
    int id;
    String name;
    String product;
    int quantity;
    double price;

    Customer(int id, String name, String product,
             int quantity, double price) {
        this.id = id;
        this.name = name;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    double calculateBill() {
        return quantity * price;
    }

    void display() {
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: Rs." + price);
        System.out.println("Total Bill: Rs." + calculateBill());
        System.out.println("--------------------------");
    }
}

public class CustomerBillingSystem {

    static Customer[] customers = new Customer[100];
    static int count = 0;

    // Queue implementation using Array
    static int front = 0;
    static int rear = -1;
    static int[] queue = new int[100];

    static Scanner sc = new Scanner(System.in);

    // Add Customer
    static void addCustomer() {

        if (count == customers.length) {
            System.out.println("Customer storage is full!");
            return;
        }

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (searchCustomer(id) != -1) {
            System.out.println("Customer ID already exists!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Product Name: ");
        String product = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        System.out.print("Enter Product Price: ");
        double price = sc.nextDouble();

        if (quantity <= 0 || price < 0) {
            System.out.println("Invalid quantity or price!");
            return;
        }

        customers[count] = new Customer(
            id, name, product, quantity, price
        );

        count++;

        System.out.println("Customer Added Successfully!");
    }

    // Display Customers
    static void displayCustomers() {

        if (count == 0) {
            System.out.println("No Customers Found!");
            return;
        }

        for (int i = 0; i < count; i++) {
            customers[i].display();
        }
    }

    // Linear Search by Customer ID
    static int searchCustomer(int id) {

        for (int i = 0; i < count; i++) {

            if (customers[i].id == id) {
                return i;
            }
        }

        return -1;
    }

    // Search Customer
    static void search() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        int index = searchCustomer(id);

        if (index != -1) {
            System.out.println("Customer Found!");
            customers[index].display();
        } else {
            System.out.println("Customer Not Found!");
        }
    }

    // Add Customer to Queue
    static void addToQueue() {

        if (rear == queue.length - 1) {
            System.out.println("Billing Queue is Full!");
            return;
        }

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        int index = searchCustomer(id);

        if (index == -1) {
            System.out.println("Customer Not Found!");
            return;
        }

        queue[++rear] = index;

        System.out.println("Customer Added to Billing Queue!");
    }

    // Process Billing Queue
    static void processBill() {

        if (front > rear) {
            System.out.println("Billing Queue is Empty!");
            return;
        }

        int index = queue[front++];

        System.out.println("\nProcessing Customer Bill...");
        customers[index].display();

        System.out.println("Bill Processed Successfully!");
    }

    // Main Method
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== CUSTOMER BILLING SYSTEM =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Display Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Add Customer to Billing Queue");
            System.out.println("5. Process Customer Bill");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    displayCustomers();
                    break;

                case 3:
                    search();
                    break;

                case 4:
                    addToQueue();
                    break;

                case 5:
                    processBill();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
