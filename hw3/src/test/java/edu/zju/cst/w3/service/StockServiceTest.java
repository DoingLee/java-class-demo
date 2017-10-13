package edu.zju.cst.w3.service;

import edu.zju.cst.w3.dao.IStockDAO;
import edu.zju.cst.w3.dao.StockDAO;
import edu.zju.cst.w3.entity.StockMsg;
import edu.zju.cst.w3.entity.StockMsgUtil;
import edu.zju.cst.w3.exception.InvalidParamException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockServiceTest {

    IStockDAO stockDAO;
    IStockService stockService;

    @Before
    public void init() throws InvalidParamException {
        stockDAO = new StockDAO();
        stockService = new StockService(stockDAO);

        //公司信息
        StockMsgUtil.insert(new StockMsg("123456", "阿里巴巴"));
        StockMsgUtil.insert(new StockMsg("123457", "网易"));
        StockMsgUtil.insert(new StockMsg("123458", "百度"));
        StockMsgUtil.insert(new StockMsg("123459", "趣链"));
    }

    @Test(expected = InvalidParamException.class)
    public void testInvalidPamas1() throws InvalidParamException {
        stockService.insertStockClosingPrice("111", 1, 82.0);
    }

    @Test(expected = InvalidParamException.class)
    public void testInvalidPamas2() throws InvalidParamException {
        stockService.getStockClosingPrice("111", 1);
    }

    @Test(expected = InvalidParamException.class)
    public void testInvalidPamas3() throws InvalidParamException {
        stockService.getStockName("111");
    }

    @Test(expected = InvalidParamException.class)
    public void testInvalidPamas4() throws InvalidParamException {
        stockService.insertStockClosingPrice("123456", 1, -2.0);
    }

    @Test
    public void testLogic() throws InvalidParamException {
        stockService.insertStockClosingPrice("123456", 1, 80.0);
        stockService.insertStockClosingPrice("123457", 1, 80.1);
        stockService.insertStockClosingPrice("123458", 1, 80.2);
        stockService.insertStockClosingPrice("123459", 1, 80.3);

        stockService.insertStockClosingPrice("123456", 2, 81.0);
        stockService.insertStockClosingPrice("123457", 2, 81.1);
        stockService.insertStockClosingPrice("123458", 2, 81.2);
        stockService.insertStockClosingPrice("123459", 2, 81.3);


        Assert.assertEquals(80.3, stockService.getStockClosingPrice("123459", 1), 0.001);

        Assert.assertEquals("趣链", stockService.getStockName("123459"));

        Assert.assertEquals(4, stockService.getStockIdList().size());

        Assert.assertEquals("123459", stockService.getBestStock(1, 2));
    }

}