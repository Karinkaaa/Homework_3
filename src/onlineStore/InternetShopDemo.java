package onlineStore;

import onlineStore.customer.Customer;
import onlineStore.order.Order;
import onlineStore.product.Product;
import onlineStore.seller.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InternetShopDemo {
    public static void main(String[] args) {

        /* С помощью классов описать процесс "оплата товара в нтернет магазине":
        - Реализовать следущие классы: Seller, Product, Customer, Order.
        - Реализовать класс InternetShopDemo с методом main где показать пример
        использования спректированных классов. */

        menu();
    }

    public static void menu() {

        Scanner in = new Scanner(System.in);
        int action;
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(14725, "shirt", 385, 50));
        productList.add(new Product(36744, "sweater", 690, 1));
        productList.add(new Product(96411, "jeans", 1400, 75));

        Seller seller = new Seller(productList);
        Customer customer = new Customer(1500);

        do {
            header();
            action = in.nextInt();
            setColor(Color.Green);

            if (action == 1) {
                viewProductCatalog(seller);
            } else if (action == 2) {
                addProductInCheck(in, seller);
            } else if (action == 3) {
                removeProductInCheck(in, seller);
            } else if (action == 4) {
                makePayment(seller, customer);
            }
        } while (action != 0);
    }

    private static void header() {
        setColor(Color.Red);
        print("\n * * * МЕНЮ * * *");
        print("[1] посмотреть каталог товаров");
        print("[2] добавить товар в чек");
        print("[3] удалить товар из чека");
        print("[4] провести оплату");
        print("[0] выход");
        System.out.print("\nВыберите действие: ");
    }

    enum Color {
        Red, Green
    }

    private static void setColor(Color color) {
        switch (color) {
            case Red:
                print((char) 27 + "[31m" + (char) 27 + "[0m");
                break;
            case Green:
                print((char) 27 + "[32m" + (char) 27 + "[0m");
                break;
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void viewProductCatalog(Seller seller) {
        print("\n\nКАТАЛОГ ТОВАРОВ:\n\n" + seller.getAllProducts());
    }

    private static void addProductInCheck(Scanner in, Seller seller) {
        System.out.print("\nВведите код товара: ");
        int id = in.nextInt();
        Product product = seller.getProductByID(id);
        if (product != null) {
            if (seller.addProductInOrder(product)) {
                print("Товар успешно добавлен!");
            } else {
                print("Данного товара нет в наличии!");
            }
        } else {
            print("Товар с кодом " + id + " не найден!");
        }
    }

    private static void removeProductInCheck(Scanner in, Seller seller) {
        if (seller.getOrder().getSum() > 0) {
            System.out.print("\nВведите код товара: ");
            int id = in.nextInt();
            Product product = seller.getProductByID(id);
            if (product != null) {
                seller.removeProductFromOrder(product);
                print("Товар успешно удален!");
            } else {
                print("Товар с кодом " + id + " не найден!");
            }
        } else {
            print("Ваш чек пустой!");
        }
    }

    private static void makePayment(Seller seller, Customer customer) {
        Order order = seller.getOrder();
        if (order.getSum() > 0) {
            print("\n\nЧEK:\n-------------------\n" + order);
            if (customer.getMoney() >= order.getSum()) {
                customer.makePayment(order);
                print("Оплата прошла успешно!");
                seller.resetOrder();
            } else {
                print("Недостаточно средств! Ваш баланс: " + customer.getMoney() + " грн.");
            }
        } else {
            print("Ваш чек пустой!");
        }
    }
}
