package com.java.chat1;

/**
 * @see https://www.youtube.com/watch?v=YEZx_Bz_OPA
 */

import java.io.*;
import java.net.*;

public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask = null;
        do {
            System.out.print("wait command ...");
            ask = in.readLine();
            System.out.print(ask);
            if ("Hello oracle".equals(ask)) {
                out.println("Hello dear friend, I'm a oracle.");
                out.println();
            } else if (!("exit".equals(ask))) {
                out.println("I don't understand");
                out.println();
            }
        } while (!("exit".equals(ask)));
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(1111).accept()) {
            new Server(socket);
        }
    }
}
