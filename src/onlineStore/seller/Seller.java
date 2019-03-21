package onlineStore.seller;

import onlineStore.order.Order;
import onlineStore.product.Product;

import java.util.List;

public class Seller {

    private List<Product> productList;
    private Order order;

    public Seller(List<Product> productList) {
        this.productList = productList;
        this.order = new Order();
    }

    public void showAllProducts() {
        for (Product prod : productList) {
            System.out.println(prod);
        }
    }

    public Product getProductByID(int id) {
        for (Product prod : productList) {
            if (prod.getID() == id) {
                return prod;
            }
        }
        return null;
    }

    public boolean addProductInOrder(Product product) {
        if (product.buyThis()) {
            this.order.addProduct(product);
            return true;
        }
        return false;
    }

    public void removeProductFromOrder(Product product) {
        product.returnThis();
        this.order.removeProduct(product);
    }

    public Order getOrder() {
        return this.order;
    }

    public void resetOrder() {
        this.order.reset();
    }
}
