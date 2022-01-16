package org.jugbd.essentials;

public class ThreadCreationDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Hello world :" + Thread.currentThread().getName());
                }
            }
        });
        t1.setName("MyThread");
        t1.start();


    }
}
