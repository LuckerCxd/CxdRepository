package cn.cxd.jdbc.model;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 13:27 2020/5/14
 * @Modified By:
 */
public class BookState {
    private String ibsn;
    private int price;
    private int store;
    private String name;

    public String getIbsn() {
        return ibsn;
    }

    public int getPrice() {
        return price;
    }

    public int getStore() {
        return store;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BookState{" +
                "ibsn='" + ibsn + '\'' +
                ", price=" + price +
                ", store=" + store +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStore(int store) {
        this.store = store;
    }


}
