package com.shn.gmall.order;

import com.shn.gmall.annotation.LoginRequire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sss
 */
@Controller
public class OrderController {

    @LoginRequire(isNeedSuccess = true)
    @RequestMapping("/toTrade")
    public String toTrade() {
        return "trade";
    }
}
