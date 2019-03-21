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
        int action, id;
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(14725, "shirt", 385, 50));
        productList.add(new Product(36744, "sweater", 690, 1));
        productList.add(new Product(96411, "jeans", 1400, 75));

        Seller seller = new Seller(productList);
        Customer customer = new Customer(1500);

        do {
            System.out.println((char) 27 + "[31m" + (char) 27 + "[0m");
            System.out.println("\n * * * МЕНЮ * * *");
            System.out.println("[1] посмотреть каталог товаров");
            System.out.println("[2] добавить товар в чек");
            System.out.println("[3] удалить товар из чека");
            System.out.println("[4] провести оплату");
            System.out.println("[0] выход");
            System.out.print("\nВыберите действие: ");
            action = in.nextInt();
            System.out.println((char) 27 + "[32m" + (char) 27 + "[0m");

            if (action == 1) {
                System.out.println("\n\nКАТАЛОГ ТОВАРОВ:\n");
                seller.showAllProducts();
            } else if (action == 2) {
                System.out.print("\nВведите код товара: ");
                id = in.nextInt();
                Product product = seller.getProductByID(id);
                if (product != null) {
                    if (seller.addProductInOrder(product)) {
                        System.out.println("Товар успешно добавлен!");
                    } else {
                        System.out.println("Данного товара нет в наличии!");
                    }
                } else {
                    System.out.println("Товар с кодом " + id + " не найден!");
                }
            } else if (action == 3) {
                if (seller.getOrder().getSum() > 0) {
                    System.out.print("\nВведите код товара: ");
                    id = in.nextInt();
                    Product product = seller.getProductByID(id);
                    if (product != null) {
                        seller.removeProductFromOrder(product);
                        System.out.println("Товар успешно удален!");
                    } else {
                        System.out.println("Товар с кодом " + id + " не найден!");
                    }
                } else {
                    System.out.println("Ваш чек пустой!");
                }
            } else if (action == 4) {
                Order order = seller.getOrder();
                if (order.getSum() > 0) {
                    System.out.println("\n\nЧEK:\n-------------------\n" + order);
                    if (customer.getMoney() >= order.getSum()) {
                        customer.makePayment(order);
                        System.out.println("Оплата прошла успешно!");
                        seller.resetOrder();
                    } else {
                        System.out.println("Недостаточно средств! Ваш баланс: " + customer.getMoney() + " грн.");
                    }
                } else {
                    System.out.println("Ваш чек пустой!");
                }
            }
        } while (action != 0);
    }
}
