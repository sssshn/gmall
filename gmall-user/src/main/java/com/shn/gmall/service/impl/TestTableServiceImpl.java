package com.shn.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shn.gmall.bean.TestTableVO;
import com.shn.gmall.mapper.TestTableDao;
import com.shn.gmall.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TestTableServiceImpl implements TestTableService {

    @Autowired
    private TestTableDao testTableDao;

    @Override
    public List<TestTableVO> getAll() {
        return testTableDao.selectAll();
    }
}
