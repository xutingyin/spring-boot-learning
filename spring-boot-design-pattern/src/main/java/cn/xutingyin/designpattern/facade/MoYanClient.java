package cn.xutingyin.designpattern.facade;

/**
 * @description: 墨言客户
 * @author: Tingyin.Xu
 * @email : sunshinexuty@163.com
 * @create: 2019-12-22 15:32
 **/
public class MoYanClient {
    public void wantHouse() {
        ContractorCafade contractorCafade = new ContractorCafade();
        contractorCafade.can();
    }
}
