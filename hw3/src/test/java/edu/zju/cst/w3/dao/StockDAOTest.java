package edu.zju.cst.w3.dao;

import edu.zju.cst.w3.entity.StockMsg;
import edu.zju.cst.w3.entity.StockMsgUtil;
import edu.zju.cst.w3.exception.InvalidParamException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockDAOTest {

    StockDAO stockDAO = new StockDAO();

    @Before
    public void init() throws InvalidParamException {
        //公司信息
        StockMsgUtil.insert(new StockMsg("123456", "阿里巴巴"));
        StockMsgUtil.insert(new StockMsg("123457", "网易"));
        StockMsgUtil.insert(new StockMsg("123458", "百度"));
        StockMsgUtil.insert(new StockMsg("123459", "趣链"));
    }

    @Test
    public void test() {
        //插入数据
        stockDAO.insertStockClosingPrice("123456", 1, 80.0);
        stockDAO.insertStockClosingPrice("123457", 1, 70.0);
        stockDAO.insertStockClosingPrice("123458", 1, 60.0);
        stockDAO.insertStockClosingPrice("123459", 1, 50.0);

        stockDAO.insertStockClosingPrice("123456", 2, 81.1);
        stockDAO.insertStockClosingPrice("123457", 2, 71.1);
        stockDAO.insertStockClosingPrice("123458", 2, 61.1);
        stockDAO.insertStockClosingPrice("123459", 2, 51.1);


        Assert.assertEquals(51.1, stockDAO.getStockClosingPrice( "123459", 2),0.001);

        Assert.assertEquals("趣链", stockDAO.getStockName("123459"));

        Assert.assertEquals(4, stockDAO.getStockIdList().size());

        Assert.assertEquals(8, stockDAO.getAllStockPriceByDate(1,2).size());
    }

}