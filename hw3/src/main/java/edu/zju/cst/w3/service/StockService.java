package edu.zju.cst.w3.service;

import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.entity.StockPrice;
import edu.zju.cst.w3.exception.InvalidParamException;

import java.util.List;

public class StockService implements IStockService  {

    IStockDAO stockDAO;

    public StockService(IStockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }


    public double getStockClosingPrice(String stockId, int date) throws InvalidParamException {
        //入参校验：StockId的形式是六位数字，比如“300100”，“600000”，其他形式无效
        if (!stockId.matches("^\\d{6}$")) {
            throw new InvalidParamException("StockId必须是六位数字");
        }

        return stockDAO.getStockClosingPrice(stockId,date);
    }

    public void insertStockClosingPrice(String stockId, int date, double closingPrice) throws InvalidParamException {
        //入参校验：StockId的形式是六位数字，比如“300100”，“600000”，其他形式无效；价格必须是正数
        if (!stockId.matches("^\\d{6}$")) {
            throw new InvalidParamException("StockId必须是六位数字");
        }
        if (closingPrice <= 0) {
            throw new InvalidParamException("价格必须是正数");
        }

        stockDAO.insertStockClosingPrice(stockId,date,closingPrice);
        return;
    }


    public String getStockName(String stockId) throws InvalidParamException {
        //入参校验：StockId的形式是六位数字，比如“300100”，“600000”，其他形式无效
        if (!stockId.matches("^\\d{6}$")) {
            throw new InvalidParamException("StockId必须是六位数字");
        }

        return stockDAO.getStockName(stockId);
    }

    public List<String> getStockIdList() {
        return stockDAO.getStockIdList();
    }

    /**
     * 获取指定时间内涨幅最高的股票，包括首尾两天，返回该股票代码
     * @param startDate
     * @param endDate
     * @return stockId
     */
    public String getBestStock(int startDate, int endDate) {
        List<StockPrice> stockPriceList = stockDAO.getAllStockPriceByDate(startDate, endDate);
        StockPrice bestStockPrice = stockPriceList.get(0);
        for (StockPrice stockPrice : stockPriceList) {
            if (stockPrice.getPrice() > bestStockPrice.getPrice()) {
                bestStockPrice = stockPrice;
            }
        }
        return bestStockPrice.getStockId();
    }
}
