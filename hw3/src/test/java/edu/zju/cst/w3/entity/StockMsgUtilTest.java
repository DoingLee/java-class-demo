package edu.zju.cst.w3.entity;

import edu.zju.cst.w3.exception.InvalidParamException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockMsgUtilTest {

    @Test(expected = InvalidParamException.class)
    public void testInvalidInsert() throws InvalidParamException {
        StockMsgUtil.insert(new StockMsg("20010", "阿里巴巴"));
    }

    @Test
    public void testLogic() throws InvalidParamException {
        StockMsgUtil.insert(new StockMsg("123456", "阿里巴巴"));
        StockMsgUtil.insert(new StockMsg("123457", "网易"));
        StockMsgUtil.insert(new StockMsg("123458", "百度"));
        StockMsgUtil.insert(new StockMsg("123459", "趣链"));

        Assert.assertEquals(4, StockMsgUtil.getAllStockName().size());

        Assert.assertEquals("趣链", StockMsgUtil.getStockMsg("123459").getStockName());

        StockMsgUtil.delete("123456");

        Assert.assertEquals(3, StockMsgUtil.getAllStockName().size());
    }

}