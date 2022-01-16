package org.jugbd.essentials;

public class ThreadCreationDemo2 {
    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunnable());
        t1.start();
        Thread t2 = new Thread(new MyRunnable());
        t2.start();
    }
}


class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
        }
    }
}