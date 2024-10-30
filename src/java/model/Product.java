/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Product {
    private int productID;
    private int catelogyID;
    private String name;
    private String imgUrl;
    private String description;
    private float price;

    public Product() {
    }

    public Product(int productID, int catelogyID, String name, String imgUrl, String description, float price) {
        this.productID = productID;
        this.catelogyID = catelogyID;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCatelogyID() {
        return catelogyID;
    }

    public void setCatelogyID(int catelogyID) {
        this.catelogyID = catelogyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", catelogyID=" + catelogyID + ", name=" + name + ", imgUrl=" + imgUrl + ", description=" + description + ", price=" + price + '}';
    }
    
    
}
