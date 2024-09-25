package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення об'єктів класу Product
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> products = new ArrayList<>(List.of(product1, product2, product3));
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        History history = new History();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();

                    int flag = 0;
                    for (Product product : products) {
                        if (product.getId() == id) {
                            cart.addProduct(product);
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        System.err.println("Товар з таким ID не знайдено");
                    }
                    break;

                case 3:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    id = scanner.nextInt();
                    flag = 0;
                    for (Product product : cart.getProducts()) {
                        if (product.getId() == id) {
                            cart.removeProduct(product);
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        System.err.println("Товар з таким ID не знайдено");
                    }
                    break;

                case 4:
                    System.out.println(cart);
                    break;

                case 5:
                    if (!cart.getProducts().isEmpty()) {
                        Order order = new Order(cart);
                        System.out.println("Замовлення оформлено:");
                        order.setStatus(Status.DELIVERING);
                        System.out.println(order);
                        history.addOrder(order);
                        cart.clear(); // Метод для очищення кошика, який потрібно реалізувати в класі Cart
                    } else {
                        System.out.println("Ваш кошик порожній!");
                    }
                    break;

                case 6:
                    history.printHistory();
                    break;

                case 7:
                    System.out.println("За яким параметром ви хочете знайти товар?");
                    System.out.println("1 - За назвою товару");
                    System.out.println("2 - За категорією");
                    int chooseFeature = scanner.nextInt();
                    switch (chooseFeature) {
                        case 1:
                            String name = scanner.next();

                            flag = 0;
                            for (Product product : products) {
                                if (product.getName().equals(name)) {
                                    System.out.println(product);
                                    flag = 1;
                                }
                            }
                            if (flag == 0) {
                                System.err.println("Товар з такою назвою не знайдено!");
                            }
                            break;
                        case 2:
                            String categoryName = scanner.next();

                            flag = 0;
                            for (Product product : products) {
                                if (product.getCategory().getName().equals(categoryName)) {
                                    System.out.println(product);
                                    flag = 1;
                                }
                            }
                            if (flag == 0) {
                                System.err.println("Товар з такою категорією не знайдено!");
                            }
                            break;
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що відвідали наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }

        }
    }
}