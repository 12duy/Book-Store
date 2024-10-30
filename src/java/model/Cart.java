/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Cart {

    private List<Item> items;

    public Cart(List<Item> items) {
        this.items = items;
    }

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItemByID(int id) {
        if (items != null) {
            for (Item i : items) {
                if (i.getProduct().getProductID() == id) {
                    return i;
                }
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        return getItemByID(id).getQuantity();
    }

    public void addItem(Item i) {
        if (getItemByID(i.getProduct().getProductID()) != null) {
            Item t = getItemByID(i.getProduct().getProductID());
            t.setQuantity(t.getQuantity() + i.getQuantity());
        } else {
            items.add(i);
        }
    }

    public void removeItem(int id) {
        if (getItemByID(id) != null) {
            items.remove(getItemByID(id));
        }
    }

    public float getTotalPrice() {
        float total = 0;
        for (Item i : this.items) {
            total += (i.getPrice() * i.getPrice());
        }
        return total;
    }
    

    public static void main(String[] args) {
        DAO dao = new DAO();
        Cart cart = new Cart();
        Product pr = dao.getProductByID(8);
        System.out.println(pr);
        Item i = new Item(pr, 1, 100);
        System.out.println(i);
        cart.addItem(i);
        System.out.println(cart.getItems());
    }
}
