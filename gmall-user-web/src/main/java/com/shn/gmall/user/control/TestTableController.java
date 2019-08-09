package com.shn.gmall.user.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shn.gmall.bean.TestTableVO;
import com.shn.gmall.service.TestTableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestTableController {

    @Reference
    private TestTableService testTableService;

    @RequestMapping("getInfo")
    public List<TestTableVO> getInfo() {
        return testTableService.getAll();
    }
}
