/*
 * File: Executive.java
 * Author: Alex Bailey
 * Date: 24 March 18
 * Purpose: Subclass of Employee
 */

public class Executive extends Employee{
    // instance variables
    private int stockPrice;

    // constructor
    public Executive(){}

    public Executive(String name, int monthlySalary, int stockPrice) {
        super(name, monthlySalary);
        this.stockPrice = stockPrice;
    }

    // getters and setters
    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    // methods
    protected int annualSalary(){
        int annualSalary = getMonthlySalary() * 12;
        int bonus = 30000;
        if(stockPrice > 50) {
            return annualSalary + bonus;
        }
        return annualSalary;
    }

    // returns objects' fields as a String
    public String toString() {
        String str = "\n" + super.toString() +
                "\nStock Price: %d";
        return String.format(str, stockPrice);
    }
}
