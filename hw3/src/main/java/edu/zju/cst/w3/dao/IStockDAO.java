package edu.zju.cst.w3.dao;

import edu.zju.cst.w3.entity.StockMsg;
import edu.zju.cst.w3.entity.StockPrice;

import java.util.List;

public interface IStockDAO {

    double getStockClosingPrice(String stockId, int date);

    void insertStockClosingPrice(String stockId, int date, double closingPrice);

    String getStockName(String stockId);

    List<String> getStockIdList();

    List<StockPrice> getAllStockPriceByDate(int startDate, int endDate);
}
