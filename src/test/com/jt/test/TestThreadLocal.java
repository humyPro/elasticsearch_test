package com.jt.test;

public class TestThreadLocal {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal test = new TestThreadLocal();


        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        System.out.println("main中test实例的地址"+test);
        System.out.println("main中longLocat实例的地址"+test.longLocal);
        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
                System.out.println("线程中test实例的地址"+test);
                System.out.println("t1中longLocat实例的地址"+test.longLocal);
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }

}
