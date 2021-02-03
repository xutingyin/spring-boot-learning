package cn.xutingyin.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description: 泡茶
 * @author: Tingyin.Xu
 * @email : sunshinexuty@163.com
 * @create: 2020-03-29 09:03
 **/
public class CompletableFutureMakeTea {

    public void makeTea() {
        // 任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T1:烧开水...");
            sleep(15, TimeUnit.SECONDS);
        });
        // 任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1, TimeUnit.SECONDS);

            System.out.println("T2:洗茶杯...");
            sleep(2, TimeUnit.SECONDS);

            System.out.println("T2:拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });
        // 任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T2:拿到茶叶:" + tf);
            System.out.println("T3:泡茶...");
            return "上茶:" + tf;
        });
        // 等待任务3执行结果
        System.out.println(f3.join());
    }

    public void relationOr() {
        CompletableFuture f1 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom(5, 10);
            sleep(t, TimeUnit.SECONDS);
            return String.valueOf(t);
        });
        CompletableFuture f2 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom(5, 10);
            sleep(t, TimeUnit.SECONDS);
            return String.valueOf(t);
        });
        CompletableFuture f3 = f1.applyToEither(f2, s -> s);
        System.out.println(f3.join());

    }

    public static void main(String[] args) {
        // new CompletableFutureMakeTea().makeTea();
        new CompletableFutureMakeTea().relationOr();

    }

    void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }

    int getRandom(int a, int b) {
        return Math.subtractExact(a, b);
    }
}
