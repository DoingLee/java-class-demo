package edu.zju.cst.w3.entity;

import edu.zju.cst.w3.exception.InvalidParamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//模拟数据库存放所有股票信息
public class StockMsgUtil {

    private static HashMap<String, StockMsg> stockMsgMap = new HashMap<String, StockMsg>();

    public StockMsgUtil() {
    }

    public static void insert(StockMsg stockMsg) throws InvalidParamException {
        //入参校验：StockId的形式是六位数字，比如“300100”，“600000”，其他形式无效
        if (!stockMsg.getStockId().matches("^\\d{6}$")) {
            throw new InvalidParamException("StockId必须是六位数字");
        }
        stockMsgMap.put(stockMsg.getStockId(), stockMsg);
    }

    public static void delete(String stockId) {
        stockMsgMap.remove(stockId);
    }

    public static StockMsg getStockMsg(String stockId) {
        return stockMsgMap.get(stockId);
    }

    public static List<String> getAllStockName() {
        ArrayList<String> stockNames = new ArrayList<String>();
        for (StockMsg stockMsg : stockMsgMap.values()) {
            stockNames.add(stockMsg.getStockName());
        }
        return stockNames;
    }
}
