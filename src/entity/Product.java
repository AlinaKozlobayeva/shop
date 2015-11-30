package entity;

/**
 * Created by Alina on 06.11.2015.
 */
public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private String productView;

    public Product(String productName, double productPrice, String productView) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productView = productView;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductView() {
        return productView;
    }

    public void setProductView(String productView) {
        this.productView = productView;
    }
}
