package com.example.guest.expensestracker;

/**
 * Created by Guest on 12/14/16.
 */
public class Expense {
    private Double cost;

    public Expense(Double cost) {
        this.cost = cost;
    }

    public Expense() {
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
