package com.doing.service;

import com.doing.dao.ICalculateDao;

public class CalculateService {

    ICalculateDao calculateDao;

    public CalculateService(ICalculateDao calculateDao) {
        this.calculateDao = calculateDao;
    }

    public int add(int a, int b) {
        return calculateDao.add(a, b);
    }
}
