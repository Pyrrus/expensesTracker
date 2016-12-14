package com.example.guest.expensestracker;

/**
 * Created by Guest on 12/14/16.
 */
public class Expense {
    private Double cost;
    private String date;

    public Expense(Double cost, String date) {
        this.cost = cost;
        this.date = date;
    }

    public Expense() {
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
