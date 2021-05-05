package com.example.product_cart;

public class Product {

    private int resourceId;
    private String name;
    private String description;
    private int total;
    private boolean isAddToCart;

    public Product(int resourceId, String name, String description, int total) {
        this.resourceId = resourceId;
        this.name = name;
        this.description = description;
        this.total = total;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
}
