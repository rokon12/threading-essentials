package org.jugbd.essentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.stream.Collectors;

public class MultiThreadedWebServer {
    private final MostFrequentWordService mostFrequentWordService = new MostFrequentWordService();

    public MultiThreadedWebServer(int port) throws IOException {
        var serverSocket = new ServerSocket(port);
        while (true) {
            var socket = serverSocket.accept();
            Thread thread = new Thread(()-> {
                handle(socket);
            });
            thread.start();
        }
    }

    private void handle(Socket socket) {
        System.out.println("Client connected: " + socket);
        try (var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var out = new PrintWriter(socket.getOutputStream(), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                if (isValid(line)) {
                    var wordCount = mostFrequentWordService.mostFrequentWord(line)
                            .stream()
                            .map(counter -> counter.word() + ": " + counter.count())
                            .collect(Collectors.joining("\n"));
                    out.println(wordCount);
                } else if (line.contains("quit")) {
                    out.println("Goodbye!");
                    socket.close();
                } else {
                    out.println("Malformed URL");
                }
            }
        } catch (IOException e) {
            System.out.println("Was unable to establish or communicate with client socket:" + e.getMessage());
        }
    }

    private static boolean isValid(String stringURL) {
        try {
            new URL(stringURL);
        } catch (MalformedURLException e) {
            System.out.println("invalid url: " + stringURL);
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new MultiThreadedWebServer(2222);
    }
}
