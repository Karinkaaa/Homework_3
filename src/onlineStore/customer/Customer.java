package onlineStore.customer;

import onlineStore.order.Order;

public class Customer {

    private double money;

    public Customer(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void makePayment(Order order) {
        this.money -= order.getSum();
    }
}
