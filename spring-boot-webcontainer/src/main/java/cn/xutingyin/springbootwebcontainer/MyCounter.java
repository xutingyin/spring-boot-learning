package cn.xutingyin.springbootwebcontainer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import cn.xutingyin.springbootwebcontainer.thread.NamedThreadFactory;

/**
 * 计数器
 */
public class MyCounter {
    private static volatile long count = 1;

    public void increatMent() {
        int idx = 0;
        AtomicInteger atomicInteger = new AtomicInteger();
        while (idx++ < 10000) {
            count = atomicInteger.incrementAndGet();
        }
    }

    public static long calc() throws InterruptedException {
        MyCounter myCounter = new MyCounter();
        Thread t1 = new Thread(() -> {
            myCounter.increatMent();
        });

        Thread t2 = new Thread(() -> {
            myCounter.increatMent();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        return count;
    }

    public static int getPrice1() {
        int price = 0;
        // TODO ...
        return price;
    }

    public static int getPrice2() {
        int price = 0;
        // TODO ...
        return price;
    }

    public static int getPrice3() {
        int price = 0;
        // TODO ...
        return price;
    }

    public static void saveDB(int price) {
        // TODO..
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executorOne = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
            new NamedThreadFactory("测试"));

        ThreadPoolExecutor executorTwo = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
            new NamedThreadFactory("TEST"));

        BlockingQueue blockingQueue = new LinkedBlockingQueue();
        // 生产
        blockingQueue.put("");
        // 消费
        blockingQueue.take();

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 不指定 completionQueue ,默认使用无界队列 LinkedBlockingQueue
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        // 异步获取电商1价格
        completionService.submit(() -> getPrice1());
        // 异步获取电商2价格
        completionService.submit(() -> getPrice2());
        // 异步获取电商3价格
        completionService.submit(() -> getPrice3());

        // 异步进行保存价格
        for (int i = 0; i < 3; i++) {
            Integer price = completionService.take().get();
            executorService.execute(() -> saveDB(price));
        }

    }
}
