package com.shn.gmall.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sss
 */
@Controller
public class IndexController {

    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 跳转平台属性
     * @return
     */
    @RequestMapping("attrListPage")
    public String attrListPage() {
        return "attrListPage";
    }

    /**
     * 跳转spu
     * @return
     */
    @RequestMapping("/spuListPage")
    public String spuListPage() {
        return "spuListPage";
    }
}
