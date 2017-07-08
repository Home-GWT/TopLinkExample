package com.java.chat3;

/**
 * Класс ClientListener предназначен для прослушивания клиентских сообщений и
 * Переслать их серверу ServerDispatcher.
 */

import java.io.*;
import java.net.*;

public class ClientListener extends Thread {
    private ServerDispatcher mServerDispatcher;
    private ClientInfo mClientInfo;
    private BufferedReader mIn;

    public ClientListener(ClientInfo aClientInfo, ServerDispatcher aServerDispatcher)
            throws IOException {
        mClientInfo = aClientInfo;
        mServerDispatcher = aServerDispatcher;
        Socket socket = aClientInfo.mSocket;
        mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Пока прервано, читает сообщения из клиентского сокета, пересылает их
     * В очередь диспетчера сервера и уведомляет диспетчера сервера.
     */
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = mIn.readLine();
                if (message == null)
                    break;
                mServerDispatcher.dispatchMessage(mClientInfo, message);
            }
        } catch (IOException ioex) {
            // Problem reading from socket (communication is broken)
        }

        // Communication is broken. Interrupt both listener and sender threads
        mClientInfo.mClientSender.interrupt();
        mServerDispatcher.deleteClient(mClientInfo);
    }
}
