package edu.zju.cst.w3.entity;

public class StockMsg {

    private String stockId;
    private String stockName;

    public StockMsg(String stockId, String stockName) {
        this.stockId = stockId;
        this.stockName = stockName;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
