package com.redpanda.samples.springkafka.model;

public class Order {
    private int id;
    private float amount;

    public Order() {
    }

    public Order(int id, float amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
