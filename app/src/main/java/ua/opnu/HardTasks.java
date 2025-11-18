package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.util.*;
import java.util.stream.Collectors;

public class HardTasks {

    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
        HardTasks tasks = new HardTasks();

        System.out.println("--- Task 1 ---");
        Objects.requireNonNull(tasks.getBooksWithPrice(), "Method returns null").forEach(System.out::println);

        System.out.println("\n--- Task 2 ---");
        Objects.requireNonNull(tasks.getOrdersWithBabyProducts(), "Method returns null").forEach(System.out::println);

        System.out.println("\n--- Task 3 ---");
        Objects.requireNonNull(tasks.applyDiscountToToys(), "Method returns null").forEach(System.out::println);

        System.out.println("\n--- Task 4 ---");
        System.out.println(Objects.requireNonNull(tasks.getCheapestBook(), "Method returns null").orElse(null));

        System.out.println("\n--- Task 5 ---");
        Objects.requireNonNull(tasks.getRecentOrders(), "Method returns null").forEach(System.out::println);

        System.out.println("\n--- Task 6 ---");
        DoubleSummaryStatistics statistics = Objects.requireNonNull(tasks.getBooksStats(), "Method returns null");
        System.out.printf("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f%n",
                statistics.getCount(), statistics.getAverage(), statistics.getMax(), statistics.getMin(), statistics.getSum());

        System.out.println("\n--- Task 7 ---");
        Objects.requireNonNull(tasks.getOrdersProductsMap(), "Method returns null")
                .forEach((id, size) -> System.out.printf("%1$d : %2$d\n", id, size));

        System.out.println("\n--- Task 8 ---");
        Objects.requireNonNull(tasks.getProductsByCategory(), "Method returns null")
                .forEach((name, list) -> System.out.printf("%1$s : %2$s\n", name, list));
    }

    public List<Product> getBooksWithPrice() {
        // Фільтруємо за категорією "Books" та ціною > 100
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersWithBabyProducts() {
        // Фільтруємо замовлення: залишаємо ті, у яких в списку товарів є хоча б один з категорією "Baby"
        return orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> "Baby".equals(p.getCategory())))
                .collect(Collectors.toList());
    }

    public List<Product> applyDiscountToToys() {
        // Фільтруємо "Toys", застосовуємо знижку 50% через peek (або map), збираємо в список
        return products.stream()
                .filter(p -> "Toys".equals(p.getCategory()))
                .peek(p -> p.setPrice(p.getPrice() * 0.5))
                .collect(Collectors.toList());
    }

    public Optional<Product> getCheapestBook() {
        // Фільтруємо книги, шукаємо мінімум за ціною
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .min(Comparator.comparing(Product::getPrice));
    }

    public List<Order> getRecentOrders() {
        // Сортуємо за датою (від нових до старих) і беремо перші 3
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public DoubleSummaryStatistics getBooksStats() {
        // Перетворюємо об'єкти Product на примітивний потік цін (DoubleStream) і отримуємо статистику
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    public Map<Integer, Integer> getOrdersProductsMap() {
        // Створюємо Map, де ключ - ID замовлення, значення - кількість товарів
        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        o -> o.getProducts().size()
                ));
    }

    public Map<String, List<Integer>> getProductsByCategory() {
        // Групуємо за категорією.
        // Оскільки значенням Map має бути List<Integer> (ID), а не List<Product>,
        // використовуємо mapping для перетворення об'єкта в ID перед збиранням у список.
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }
}