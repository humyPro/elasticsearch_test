package com.jt.test;

import org.junit.Test;

public class MyTestClass {


    @Test
    public void f(){
        Thread t1=new Thread(new T());
//        Thread t2=new Thread(new T());
        System.out.println("start");
//        System.out.println(t1.getName());
//        System.out.println(t2.getName());
//        t1.start();
//        t2.start();
        t1.start();
       for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}


    /*
     * 定义一个可重入锁
     */
class ReentryLock{
    private boolean isLocked=false;
    private Thread lockBy=null;
    private int count=0;
    public synchronized void lock() throws InterruptedException {
            Thread thread=Thread.currentThread();
        while (isLocked && lockBy!=thread){
            wait();
        }
            isLocked=true;
            count++;
            lockBy=thread;
    }

    public synchronized void unlock(){
        if(this.lockBy==Thread.currentThread()){
            count--;
            if(count==0) {
                isLocked = false;
                notify();
            }
        }

    }

}

class T extends Exception implements Runnable{

    ReentryLock lock=new ReentryLock();
    public void f1() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread()+"f1");
        System.out.println(super.getClass());
        lock.unlock();
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                System.out.println("t1线程");
                f1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}





