/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class OrderDetail {

    private int id;
    private int orderID;
    private int productID;
    private int quantity;
    private float subPrice;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderID, int productID, int quantity, float subPrice) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.subPrice = subPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(float subPrice) {
        this.subPrice = subPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + ", subPrice=" + subPrice + '}';
    }

}
