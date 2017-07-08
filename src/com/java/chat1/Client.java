package com.java.chat1;

/**
 * @see https://www.youtube.com/watch?v=YEZx_Bz_OPA
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private final Socket socket;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String request = null;
        String response = null;
        do {
            // Отсылаем сообщение на сервер
            request = console.nextLine();
            out.print(request);
            // Читаем из потока
            if (!("exit".equals(request))) {
                while (!(response = in.readLine()).isEmpty()) {
                    System.out.println(response);
                }
            }
        } while (!("exit".equals(request)));
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1111)) {
            new Client(socket);
        }
    }
}