package com.yezp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * Created on 2020/11/10 22:08.
 *
 * @author yezp
 */
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long end = 0;
        // 使用future
        // end = useFuture();

        // 使用futureTask
        // end = useFutureTask();

        // 使用completableFuture
        // end = useCompletableFuture();

        // 使用CountDownLatch
        // end = useCountDownLatch();

        // 使用循环等待
        // end = useWhile();

        // 使用semaphore
        // end = useSemaphore();

        // 使用CyclicBarrier
        // end = useCyclicBarrier();

        // 使用join
        // end = useThreadJoin();

        // 使用wait notify
        end = useObjectWaitAndNotify();

        // 然后退出main线程
        System.out.println("使用时间：" + (end - start) + " ms");
    }

    private static long useObjectWaitAndNotify() {
        List<Integer> list = new ArrayList<>(1);
        Thread thread = new Thread(() -> {
            synchronized (list) {
                list.add(sum());
                list.notify();
            }
        });
        thread.start();

        try {
            synchronized (list) {
                list.wait();
            }
            // 确保  拿到result 并输出
            System.out.println("useObjectWaitAndNotify 异步计算结果为：" + list.get(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    private static long useThreadJoin() {
        Thread thread = new Thread(() -> {
            // 确保  拿到result 并输出
            System.out.println("useThreadJoin 异步计算结果为：" + sum());
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    private static long useCyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(() -> {
            try {
                // 确保  拿到result 并输出
                System.out.println("useCyclicBarrier 异步计算结果为：" + sum());

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    private static long useSemaphore() {
        Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
            Thread thread = new Thread(() -> {
                // 确保  拿到result 并输出
                System.out.println("useSemaphore 异步计算结果为：" + sum());
                semaphore.release();
            });
            thread.start();

            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    private static long useWhile() {
        // 异步执行 下面方法
        List<Integer> list = new ArrayList<>(1);
        Thread thread = new Thread(() -> {
            list.add(sum());
        });

        thread.start();
        while (list.size() == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 确保  拿到result 并输出
        System.out.println("useWhile 异步计算结果为：" + list.get(0));
        return System.currentTimeMillis();
    }

    private static long useCountDownLatch() {
        // 异步执行 下面方法
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                // 确保  拿到result 并输出
                System.out.println("useCountDownLatch 异步计算结果为：" + sum());
            } finally {
                countDownLatch.countDown();
            }
        });
        executor.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    private static long useCompletableFuture() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> sum());

        try {
            // 确保  拿到result 并输出
            System.out.println("useCompletableFuture 异步计算结果为：" + completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    private static long useFutureTask() {
        FutureTask<Integer> task = new FutureTask<Integer>(() -> sum());
        new Thread(task).start();
        try {
            // 确保  拿到result 并输出
            System.out.println("futureTask 异步计算结果为：" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    private static long useFuture() {
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(() -> sum());
        executor.shutdown();

        try {
            // 确保  拿到result 并输出
            System.out.println("future 异步计算结果为：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
