package edu.zju.cst.w3.dao;

import edu.zju.cst.w3.entity.StockMsg;
import edu.zju.cst.w3.entity.StockMsgUtil;
import edu.zju.cst.w3.entity.StockPrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockDAO implements IStockDAO {

    //stockId => (date => StockPrice)
    HashMap<String, HashMap<Integer, StockPrice>> stockPriceMap = new HashMap<String, HashMap<Integer, StockPrice>>();

    public double getStockClosingPrice(String stockId, int date) {
        return stockPriceMap.get(stockId).get(date).getPrice();
    }

    public void insertStockClosingPrice(String stockId, int date, double closingPrice) {
        StockPrice stockPrice = new StockPrice(stockId, closingPrice, date);
        if (null != stockPriceMap.get(stockId)) {
            HashMap<Integer, StockPrice> dateMap = stockPriceMap.get(stockId);
            dateMap.put(date, stockPrice);
        } else {
            HashMap<Integer, StockPrice> dateMap = new HashMap<Integer, StockPrice>();
            dateMap.put(date, stockPrice);
            stockPriceMap.put(stockId, dateMap);
        }
    }

    public String getStockName(String stockId) {
        return StockMsgUtil.getStockMsg(stockId).getStockName();
    }

    public List<String> getStockIdList() {
        return StockMsgUtil.getAllStockName();
    }

    public List<StockPrice> getAllStockPriceByDate(int startDate, int endDate) {
        List<StockPrice> stockPriceList = new ArrayList<StockPrice>();

        //把每个股票的在startDate和endDate之间的股价信息取出到stockPriceList
        for (HashMap<Integer, StockPrice> datePriceMap : stockPriceMap.values()) {
            for (int date = startDate; date <= endDate; date++) {
                stockPriceList.add(datePriceMap.get(date));
            }
        }
        return stockPriceList;
    }
}
