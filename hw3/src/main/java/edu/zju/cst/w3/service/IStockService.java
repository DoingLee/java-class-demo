package edu.zju.cst.w3.service;

import edu.zju.cst.w3.exception.InvalidParamException;

import java.util.List;

public interface IStockService {

    double getStockClosingPrice(String stockId, int date) throws InvalidParamException;

    void insertStockClosingPrice(String stockId, int date, double closingPrice) throws InvalidParamException;

    String getStockName(String stockId) throws InvalidParamException;

    List<String> getStockIdList();

    String getBestStock(int startDate, int endDate); //(获取指定时间内涨幅最高的股票，包括首尾两天，返回该股票代码)
}
