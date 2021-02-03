package cn.xutingyin.controller;

import java.util.concurrent.*;

/**
 * @description: Synchronized测试
 * @author: Tingyin.Xu
 * @email : sunshinexuty@163.com
 * @create: 2020-03-14 16:16
 **/
public class SynchronizedTest {
    synchronized static void hello() {
        System.out.println("Hello");
    }

    synchronized void noStatic() {
        System.out.println("no Static ");

    }

    Object obj = new Object();

    void test() {
        synchronized (SynchronizedTest.class) {
            System.out.println("This is " + obj);
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> 1 + 2);
        // 手动创建线程池
        ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        // Executors工具类创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        threadPoolExecutor.submit(futureTask);
        Integer integer = futureTask.get();
        System.out.println(integer);
    }
}
