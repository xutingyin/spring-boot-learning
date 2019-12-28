package cn.xutingyin.designpattern.facade;

/**
 * @description:包工头
 * @author: Tingyin.Xu
 * @email : sunshinexuty@163.com
 * @create: 2019-12-22 15:29
 **/
public class ContractorCafade {
    public void can() {
        Mason mason = new Mason();
        Himma himma = new Himma();
        Bracklayer bracklayer = new Bracklayer();
        mason.can();
        himma.can();
        bracklayer.can();
    }

}
