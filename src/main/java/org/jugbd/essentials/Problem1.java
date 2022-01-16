package org.jugbd.essentials;

public class Problem1 {

    public static void main(String[] args) throws InterruptedException {

        var counter = new Counter();
        var t1 = new Thread(() -> {
            for (int i = 0; i < 1_000; i++) {
                counter.increment();
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1_000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int count = counter.get();
        System.out.println("count = " + count);

    }
}

class Counter {
    private int count;

    public void increment() {
        count++;
    }

    public int get() {

        return count;
    }
}
