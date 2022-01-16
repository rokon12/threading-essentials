package org.jugbd.essentials;

public class Playground {
    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println("currentThread = " + currentThread.getName());
    }
}
