package onlineStore.product;

public class Product {

    private int ID;
    private String name;
    private double price;
    private int count;

    public Product(int id, String name, double price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getID() {
        return ID;
    }

    public boolean buyThis() {
        if (count == 0) {
            return false;
        }
        this.count--;
        return true;
    }

    public void returnThis() {
        this.count++;
    }

    @Override
    public String toString() {
        return "Код товара: " + this.getID() + "\nНаименование товара: " + this.getName() + "\nЦена: " +
                this.getPrice() + "\nКоличество: " + this.getCount() + "\n";
    }
}
