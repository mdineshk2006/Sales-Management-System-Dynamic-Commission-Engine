import model.Product;
import model.Sale;
import model.SalesPerson;
import model.JuniorSalesPerson;
import model.SeniorSalesPerson;
import model.SalesManager;

import service.SalesService;

import exception.EmployeeNotFoundException;
import exception.ProductNotFoundException;
import exception.InvalidQuantityException;
import exception.InvalidSalesAmountException;

import java.util.Scanner;

/**
 * Main.java
 *
 * Console entry point for the Sales Management System with
 * Dynamic Commission Engine.
 *
 * Provides a menu-driven interface to:
 *   - Add salespersons (Junior / Senior / Manager)
 *   - Add products
 *   - View salespersons and products
 *   - Create a sale (with optional discount) and view its commission
 *   - View all recorded sales
 *
 * Demonstrates try / catch / finally, throw / throws, custom exceptions,
 * abstract classes, interfaces, and runtime polymorphism all working
 * together in one workflow.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SalesService salesService = new SalesService();

    public static void main(String[] args) {
        seedSampleData();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addSalesPerson();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    viewSalesPersons();
                    break;
                case "4":
                    viewProducts();
                    break;
                case "5":
                    createSale();
                    break;
                case "6":
                    viewSales();
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting Sales Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("       SALES MANAGEMENT SYSTEM");
        System.out.println("   (Dynamic Commission Engine)");
        System.out.println("========================================");
        System.out.println("1. Add Salesperson");
        System.out.println("2. Add Product");
        System.out.println("3. View Salespersons");
        System.out.println("4. View Products");
        System.out.println("5. Create Sale");
        System.out.println("6. View All Sales");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // ---------------- Menu actions ----------------

    private static void addSalesPerson() {
        try {
            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine().trim();

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine().trim();

            System.out.println("Select Type: 1) Junior  2) Senior  3) Manager");
            System.out.print("Enter choice: ");
            String type = scanner.nextLine().trim();

            SalesPerson salesperson;
            switch (type) {
                case "1":
                    salesperson = new JuniorSalesPerson(id, name);
                    break;
                case "2":
                    salesperson = new SeniorSalesPerson(id, name);
                    break;
                case "3":
                    salesperson = new SalesManager(id, name);
                    break;
                default:
                    System.out.println("Invalid type selected. Salesperson not added.");
                    return;
            }

            salesService.addSalesPerson(salesperson);
            System.out.println("Salesperson added successfully:");
            System.out.println(salesperson);

        } finally {
            System.out.println("[Add Salesperson operation completed]");
        }
    }

    private static void addProduct() {
        try {
            System.out.print("Enter Product ID: ");
            String id = scanner.nextLine().trim();

            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Stock Quantity: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());

            Product product = new Product(id, name, price, stock);
            salesService.addProduct(product);

            System.out.println("Product added successfully:");
            System.out.println(product);

        } catch (NumberFormatException e) {
            System.out.println("Error: Price and Stock must be valid numbers.");
        } finally {
            System.out.println("[Add Product operation completed]");
        }
    }

    private static void viewSalesPersons() {
        System.out.println("---------------- Salespersons ----------------");
        if (salesService.getSalesPersons().isEmpty()) {
            System.out.println("No salespersons added yet.");
        } else {
            for (SalesPerson sp : salesService.getSalesPersons()) {
                System.out.println(sp); // uses overridden getRole() via toString()
            }
        }
    }

    private static void viewProducts() {
        System.out.println("---------------- Products ----------------");
        if (salesService.getProducts().isEmpty()) {
            System.out.println("No products added yet.");
        } else {
            for (Product p : salesService.getProducts()) {
                p.displayInfo();
            }
        }
    }

    private static void createSale() {
        try {
            System.out.print("Enter Employee ID: ");
            String employeeId = scanner.nextLine().trim();

            System.out.print("Enter Product ID: ");
            String productId = scanner.nextLine().trim();

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Apply 10% discount? (y/n): ");
            boolean applyDiscount = scanner.nextLine().trim().equalsIgnoreCase("y");

            Sale sale;
            if (applyDiscount) {
                // Calls the 4-argument overload
                sale = salesService.createSale(employeeId, productId, quantity, true);
            } else {
                // Calls the 3-argument overload
                sale = salesService.createSale(employeeId, productId, quantity);
            }

            System.out.println("Sale created successfully!");
            System.out.println(sale);

        } catch (NumberFormatException e) {
            System.out.println("Error: Quantity must be a valid whole number.");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidSalesAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("[Sale transaction processing completed]");
        }
    }

    private static void viewSales() {
        System.out.println("---------------- All Sales ----------------");
        if (salesService.getSales().isEmpty()) {
            System.out.println("No sales recorded yet.");
        } else {
            for (Sale sale : salesService.getSales()) {
                System.out.println(sale);
            }
        }
    }

    /**
     * Preloads a few salespersons and products so the menu can be
     * demonstrated immediately without manual data entry.
     */
    private static void seedSampleData() {
        salesService.addSalesPerson(new JuniorSalesPerson("E101", "Rahul"));
        salesService.addSalesPerson(new SeniorSalesPerson("E102", "Priya"));
        salesService.addSalesPerson(new SalesManager("E103", "Anita"));

        salesService.addProduct(new Product("P201", "Laptop", 50000.0, 10));
        salesService.addProduct(new Product("P202", "Mouse", 500.0, 50));
        salesService.addProduct(new Product("P203", "Keyboard", 1200.0, 30));

        System.out.println("Sample salespersons and products have been preloaded.");
        System.out.println("(Employee IDs: E101, E102, E103 | Product IDs: P201, P202, P203)");
    }
}
