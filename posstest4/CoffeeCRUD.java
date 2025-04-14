import java.util.ArrayList;
import java.util.Scanner;

// Superclass Coffee
class Coffee {
    private String name;
    private String origin;
    private double price;

    // Constructor overloading (polymorphism)
    public Coffee() {
        this.name = "Unknown";
        this.origin = "Unknown";
        this.price = 0.0;
    }

    public Coffee(String name, String origin, double price) {
        this.name = name;
        this.origin = origin;
        this.price = price;
    }

    // Method overloading (polymorphism)
    public void setPrice(int price) {
        this.price = (double) price;
    }

    public String getName() { return name; }
    public String getOrigin() { return origin; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setOrigin(String origin) { this.origin = origin; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Coffee{name='" + name + "', origin='" + origin + "', price=" + price + "}";
    }
}

// Subclass Espresso
class Espresso extends Coffee {
    private int caffeineLevel;

    public Espresso(String name, String origin, double price, int caffeineLevel) {
        super(name, origin, price);
        this.caffeineLevel = caffeineLevel;
    }

    public int getCaffeineLevel() { return caffeineLevel; }
    public void setCaffeineLevel(int caffeineLevel) { this.caffeineLevel = caffeineLevel; }

    @Override
    public String toString() {
        return super.toString() + ", Espresso{caffeineLevel=" + caffeineLevel + "mg}";
    }
}

// Subclass Latte
class Latte extends Coffee {
    private double milkRatio;

    public Latte(String name, String origin, double price, double milkRatio) {
        super(name, origin, price);
        this.milkRatio = milkRatio;
    }

    public double getMilkRatio() { return milkRatio; }
    public void setMilkRatio(double milkRatio) { this.milkRatio = milkRatio; }

    @Override
    public String toString() {
        return super.toString() + ", Latte{milkRatio=" + (milkRatio * 100) + "%}";
    }
}

// Main CRUD Program
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
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCoffee();
                case 2 -> viewCoffees();
                case 3 -> updateCoffee();
                case 4 -> deleteCoffee();
                case 5 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addCoffee() {
        System.out.println("Choose coffee type:");
        System.out.println("1. Espresso");
        System.out.println("2. Latte");
        System.out.print("Enter choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter coffee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter coffee origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter coffee price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Coffee newCoffee;
        if (typeChoice == 1) {
            System.out.print("Enter caffeine level (mg): ");
            int caffeineLevel = scanner.nextInt();
            scanner.nextLine();
            newCoffee = new Espresso(name, origin, price, caffeineLevel);
        } else if (typeChoice == 2) {
            System.out.print("Enter milk ratio (0.0 - 1.0): ");
            double milkRatio = scanner.nextDouble();
            scanner.nextLine();
            newCoffee = new Latte(name, origin, price, milkRatio);
        } else {
            System.out.println("Invalid selection.");
            return;
        }

        coffeeList.add(newCoffee);
        System.out.println("Coffee added successfully!");
    }

    private static void viewCoffees() {
        if (coffeeList.isEmpty()) {
            System.out.println("No coffees available.");
        } else {
            System.out.println("\nList of Coffees:");
            for (int i = 0; i < coffeeList.size(); i++) {
                Coffee coffee = coffeeList.get(i);
                // Polymorphic behavior
                if (coffee instanceof Espresso) {
                    System.out.println((i + 1) + ". " + coffee + " (High caffeine)");
                } else if (coffee instanceof Latte) {
                    System.out.println((i + 1) + ". " + coffee + " (Creamy)");
                } else {
                    System.out.println((i + 1) + ". " + coffee);
                }
            }
        }
    }

    private static void updateCoffee() {
        viewCoffees();
        if (!coffeeList.isEmpty()) {
            System.out.print("Select coffee number to update: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index >= 0 && index < coffeeList.size()) {
                Coffee coffee = coffeeList.get(index);

                System.out.print("Enter new coffee name: ");
                coffee.setName(scanner.nextLine());
                System.out.print("Enter new coffee origin: ");
                coffee.setOrigin(scanner.nextLine());
                System.out.print("Enter new coffee price: ");
                coffee.setPrice(scanner.nextDouble());
                scanner.nextLine();

                if (coffee instanceof Espresso) {
                    System.out.print("Enter new caffeine level (mg): ");
                    ((Espresso) coffee).setCaffeineLevel(scanner.nextInt());
                    scanner.nextLine();
                } else if (coffee instanceof Latte) {
                    System.out.print("Enter new milk ratio (0.0 - 1.0): ");
                    ((Latte) coffee).setMilkRatio(scanner.nextDouble());
                    scanner.nextLine();
                }

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
            scanner.nextLine();

            if (index >= 0 && index < coffeeList.size()) {
                coffeeList.remove(index);
                System.out.println("Coffee deleted successfully!");
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }
}
