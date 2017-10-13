package com.doing.service;

import com.doing.dao.ICalculateDao;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

//使用easymock
public class CalculateServiceTest {

    ICalculateDao calculateDaoMock;
    CalculateService calculateService;

    @Test
    public void test1() {
        /*******************************  EasyMock.createMock *********************************/

        calculateDaoMock = EasyMock.createMock(ICalculateDao.class);

        //一个mock对象可以record录制多种行为，最终只要一个replay预演
        EasyMock.expect(calculateDaoMock.add(1, 2)).andReturn(3);
        EasyMock.expect(calculateDaoMock.add(3, 2)).andReturn(5).times(2);
//        EasyMock.expect(calculateDaoMock.add(1, 2)).andReturn(3).anyTimes();
        EasyMock.replay(calculateDaoMock);

        calculateService = new CalculateService(calculateDaoMock);
        assertThat(5, equalTo(calculateService.add(3, 2)));
        System.out.println("3+2 = " + calculateService.add(3, 2)); //mock对象对于这个行为可以执行2次
        assertThat(3, equalTo(calculateService.add(1, 2)));
        //System.out.println("1+2 = " + calculateService.add(1, 2)); //默认mock对象对于这个行为只能执行一次 : java.lang.AssertionError: Unexpected method call ICalculateDao.add(1, 2): ICalculateDao.add(1, 2): expected: 1, actual: 2

        //验证calculateDaoMock的每个行为的调用次数是否与设置相同（本例子中即1次、2次），否则抛出异常
        EasyMock.verify(calculateDaoMock);

        //允许重用mock对象（重置后，需要重新录制行为和预演）
        EasyMock.reset(calculateDaoMock);
        EasyMock.expect(calculateDaoMock.add(3, 5)).andReturn(8).times(2);
        EasyMock.replay(calculateDaoMock);
        calculateService = new CalculateService(calculateDaoMock);
        //assertThat(3, equalTo(calculateService.add(1, 2))); //错误，因为mock已经被重置
        assertThat(8, equalTo(calculateService.add(3, 5)));

        /*******************************  EasyMock.createStrictMock *********************************/

        //如果 Mock 对象是通过 EasyMock.createMock() 或是 IMocksControl.createMock() 所创建的，那么在进行 verify 验证时，方法的调用顺序是不进行检查的。(见上第一个例子)
        // 如果要创建方法调用的先后次序敏感的 Mock 对象（Strick Mock），应该使用 EasyMock.createStrickMock() 来创建
        calculateDaoMock = EasyMock.createStrictMock(ICalculateDao.class);

        EasyMock.expect(calculateDaoMock.add(1, 2)).andReturn(3);
        EasyMock.expect(calculateDaoMock.add(3, 2)).andReturn(5).times(2);
        EasyMock.replay(calculateDaoMock);

        calculateService = new CalculateService(calculateDaoMock);
        assertThat(3, equalTo(calculateService.add(1, 2))); //正确顺序
        assertThat(5, equalTo(calculateService.add(3, 2)));
        assertThat(5, equalTo(calculateService.add(3, 2)));
        // assertThat(3, equalTo(calculateService.add(1, 2))); //错误顺序。要在前面

        EasyMock.verify(calculateDaoMock);

    }

    @Test
    public void test2() {
        /********************************* IMocksControl *******************************/

        //使用EasyMock：每个mock对象增加create语句，而且需要为这个新增的mock对象更新replay()/verify()/reset()方法，比较啰嗦，而且容易出错
        //IMocksControl接口容许创建多个mock对象，这些创建的对象自动关联到这个mocksControl实例上，以后再调用mocksControl的replay()/verify()/reset()即可
        //使用IMocksControl还有另外一个重要的好处，就是如果使用strict control，则可以跨多个mock对象检测方法的调用顺序

        IMocksControl control = EasyMock.createControl();
        ICalculateDao calculateDaoMock1 = control.createMock(ICalculateDao.class);
        ICalculateDao calculateDaoMock2 = control.createMock(ICalculateDao.class);

        EasyMock.expect(calculateDaoMock1.add(1, 2)).andReturn(3);
        EasyMock.expect(calculateDaoMock2.add(3, 2)).andReturn(5).times(2);

        control.replay();

        CalculateService calculateService1 = new CalculateService(calculateDaoMock1);
        CalculateService calculateService2 = new CalculateService(calculateDaoMock2);
        assertThat(5, equalTo(calculateService2.add(3, 2)));
        assertThat(5, equalTo(calculateService2.add(3, 2)));
        assertThat(3, equalTo(calculateService1.add(1, 2)));

        control.verify();
    }


}