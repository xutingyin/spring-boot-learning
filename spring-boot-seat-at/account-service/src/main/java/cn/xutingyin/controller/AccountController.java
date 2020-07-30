package cn.xutingyin.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.xutingyin.service.AccountService;
import io.seata.core.context.RootContext;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/debit")
    public void debit(@RequestParam String userId, @RequestParam BigDecimal orderMoney) {
        System.out.println("account XID " + RootContext.getXID());
        accountService.debit(userId, orderMoney);
    }
}
