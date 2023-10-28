package fastfoodrestaurantapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    MenuItem menuItem;
    int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }
}

class Order {
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}

public class FastFoodRestaurantApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Burger", 5.99));
        menu.add(new MenuItem("Fries", 2.49));


        System.out.println("Welcome to the Fast Food Restaurant");
        Order order = new Order();

        while (true) {
            System.out.println("\nMenu:");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i).getName() + " - $" + menu.get(i).getPrice());
            }

            System.out.println("0. Checkout");
            System.out.print("Choose an item (0 to checkout): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= menu.size()) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                MenuItem menuItem = menu.get(choice - 1);
                OrderItem orderItem = new OrderItem(menuItem, quantity);
                order.addItem(orderItem);
                System.out.println("Item added to the order.");
            } else {
                System.out.println("Invalid choice. Please select a valid menu item or 0 to checkout.");
            }
        }

        System.out.println("\nOrder Summary:");
        for (OrderItem item : order.getItems()) {
            System.out.println(item.menuItem.getName() + " x" + item.quantity + " - $" + item.getTotalPrice());
        }

        System.out.println("Total Price: $" + order.getTotalPrice());
        System.out.println("Thank you for your order!");
    }
}
