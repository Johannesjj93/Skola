package org.example;

class Crop extends Entity {
    private final String name;
    private int quantity;

    public Crop(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        return "Crop ID: " + id + ", Name: " + name + ", Quantity: " + quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public void decreaseQuantity() {
        quantity--;
    }
}