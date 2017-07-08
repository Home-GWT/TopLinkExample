package com.java.chat3;

/**
 * @see http://inetjava.sourceforge.net/lectures/part1_sockets/InetJava-1.9-Chat-Client-Server-Example.html
 *      ***************************************************************************************************
 * NakovChatClient подключается к серверу Nakov Chat и распечатывает все сообщения
 * Получен с сервера. Он также позволяет пользователю отправлять сообщения на
 * Сервер. NakovChatClient поток читает сообщения и печатает их по стандарту
 * вывод. Поток отправителя считывает сообщения со стандартного ввода и отправляет их
 * На сервер.
 */

import java.io.*;
import java.net.*;

public class NakovChatClient {
    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 2002;

    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // Connect to Nakov Chat Server
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connected to server " + SERVER_HOSTNAME + ":" + SERVER_PORT);
        } catch (IOException ioe) {
            System.err.println("Can not establish connection to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
            ioe.printStackTrace();
            System.exit(-1);
        }

        // Create and start Sender thread
        Sender sender = new Sender(out);
        sender.setDaemon(true);
        sender.start();
        try {
            // Read messages from the server and print them
            String message;
            while ((message=in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException ioe) {
            System.err.println("Connection to server broken.");
            ioe.printStackTrace();
        }
    }
}

class Sender extends Thread {
    private PrintWriter mOut;

    public Sender(PrintWriter aOut) {
        mOut = aOut;
    }

    /**
     * До прерывания чтения сообщений со стандартного ввода (клавиатуры)
     * И отправляет их на сервер чата через сокет.
     */
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (!isInterrupted()) {
                String message = in.readLine();
                mOut.println(message);
                mOut.flush();
            }
        } catch (IOException ioe) {
            // Communication is broken
        }
    }
}
