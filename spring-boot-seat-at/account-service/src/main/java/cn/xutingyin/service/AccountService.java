package cn.xutingyin.service;

import java.math.BigDecimal;

public interface AccountService {

    void debit(String userId, BigDecimal num);

}