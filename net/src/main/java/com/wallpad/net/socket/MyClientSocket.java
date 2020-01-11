package com.wallpad.net.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class MyClientSocket {
    private Socket socket;
    private Scanner scanner;
    private MyClientSocket(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }

    public static void main(String[] args) throws Exception {
        MyClientSocket client = new MyClientSocket(
                InetAddress.getByName("124.111.208.72"),
               8800);
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}