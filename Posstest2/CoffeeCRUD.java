import java.util.ArrayList;
import java.util.Scanner;

// Class Coffee
class Coffee {
    private String name;
    private String origin;
    private double price;

    // Constructor
    public Coffee(String name, String origin, double price) {
        this.name = name;
        this.origin = origin;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffee{name='" + name + "', origin='" + origin + "', price=" + price + "}";
    }
}

// Main Program
public class CoffeeCRUD {
    private static ArrayList<Coffee> coffeeList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Coffee Menu ===");
            System.out.println("1. Add Coffee");
            System.out.println("2. View Coffees");
            System.out.println("3. Update Coffee");
            System.out.println("4. Delete Coffee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    addCoffee();
                    break;
                case 2:
                    viewCoffees();
                    break;
                case 3:
                    updateCoffee();
                    break;
                case 4:
                    deleteCoffee();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addCoffee() {
        System.out.print("Enter coffee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter coffee origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter coffee price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        coffeeList.add(new Coffee(name, origin, price));
        System.out.println("Coffee added successfully!");
    }

    private static void viewCoffees() {
        if (coffeeList.isEmpty()) {
            System.out.println("No coffees available.");
        } else {
            System.out.println("\nList of Coffees:");
            for (int i = 0; i < coffeeList.size(); i++) {
                System.out.println((i + 1) + ". " + coffeeList.get(i));
            }
        }
    }

    private static void updateCoffee() {
        viewCoffees();
        if (!coffeeList.isEmpty()) {
            System.out.print("Select coffee number to update: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Clear the buffer

            if (index >= 0 && index < coffeeList.size()) {
                System.out.print("Enter new coffee name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new coffee origin: ");
                String origin = scanner.nextLine();
                System.out.print("Enter new coffee price: ");
                double price = scanner.nextDouble();
                scanner.nextLine(); // Clear the buffer

                coffeeList.get(index).setName(name);
                coffeeList.get(index).setOrigin(origin);
                coffeeList.get(index).setPrice(price);
                System.out.println("Coffee updated successfully!");
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private static void deleteCoffee() {
        viewCoffees();
        if (!coffeeList.isEmpty()) {
            System.out.print("Select coffee number to delete: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Clear the buffer

            if (index >= 0 && index < coffeeList.size()) {
                coffeeList.remove(index);
                System.out.println("Coffee deleted successfully!");
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }
}
