package cn.cxd.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 16:17 2020/3/11
 * @Modified By:
 */
public class Order {
    private int id;
    private int userId;
    private String productName;
    private double price;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", product='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

    public Order(int id, int userId, String productName, double price) {
        this.id = id;
        this.userId = userId;
        this.productName = productName;
        this.price = price;
    }
}
