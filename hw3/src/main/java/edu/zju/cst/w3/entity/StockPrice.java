package edu.zju.cst.w3.entity;

public class StockPrice {

    private String stockId;
    private double price;
    private int date;

    public StockPrice(String stockId, double price, int date) {
        this.stockId = stockId;
        this.price = price;
        this.date = date;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
