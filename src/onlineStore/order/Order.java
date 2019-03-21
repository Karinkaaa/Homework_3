package onlineStore.order;

import onlineStore.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Product> products;
    private double sum;

    public double getSum() {
        return sum;
    }

    public Order() {
        this.products = new ArrayList<>();
        this.sum = 0;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.sum += product.getPrice();
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        this.sum -= product.getPrice();
    }

    public void reset() {
        this.sum = 0;
        this.products = new ArrayList<>();
    }

    @Override
    public String toString() {
        String res = "";
        for (Product prod : products) {
            res += prod.getName() + "\t" + prod.getPrice() + "\n";
        }
        res += "-------------------\nИТОГО: " + this.getSum() + " грн.\n-------------------";
        return res;
    }
}
