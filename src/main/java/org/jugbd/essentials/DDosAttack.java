package org.jugbd.essentials;

import java.io.IOException;
import java.net.Socket;

public class DDosAttack {
    public static void main(String[] args) throws IOException, InterruptedException {
        var sockets = new Socket[1000];
        for (int i = 0; i < sockets.length; i++) {
            sockets[i] = new Socket("localhost", 2222);
        }
        Thread.sleep(100_000);
    }
}
