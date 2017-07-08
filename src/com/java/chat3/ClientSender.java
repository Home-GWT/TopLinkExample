package com.java.chat3;

/**
 * Отправляет сообщения клиенту. Сообщения сохраняются в очереди сообщений. когда
 * Очередь пуста, ClientSender засыпает до тех пор, пока не появится новое сообщение
 * Прибыл в очередь. Когда очередь не пуста, ClientSender отправляет
 * Сообщения из очереди в клиентский сокет.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSender extends Thread {
    private Vector mMessageQueue = new Vector();
    private ServerDispatcher mServerDispatcher;
    private ClientInfo mClientInfo;
    private PrintWriter mOut;

    public ClientSender(ClientInfo aClientInfo, ServerDispatcher aServerDispatcher)
            throws IOException {
        mClientInfo = aClientInfo;
        mServerDispatcher = aServerDispatcher;
        Socket socket = aClientInfo.mSocket;
        mOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Добавляет заданное сообщение в очередь сообщений и уведомляет об этом потоке
     * (Фактически getNextMessageFromQueue метод), что сообщение получено.
     * SendMessage вызывается другими потоками (ServeDispatcher).
     */
    public synchronized void sendMessage(String aMessage) {
        mMessageQueue.add(aMessage);
        notify();
    }

    /**
     * @return и удаляет следующее сообщение из очереди сообщений. Если очередь
     * Пуст, засыпает до тех пор, пока не будет сообщено о приходе сообщения sendMessage
     * Способ.
     */
    private synchronized String getNextMessageFromQueue() throws InterruptedException {
        while (mMessageQueue.size()==0)
            wait();
        String message = (String) mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
        return message;
    }

    /**
     * Отправляет данное сообщение в сокет клиента.
     */
    private void sendMessageToClient(String aMessage) {
        mOut.println(aMessage);
        mOut.flush();
    }

    /**
     * До прерывания чтения сообщений из очереди сообщений
     * И отправляет их в гнездо клиента.
     */
    public void run() {
        try {
            while (!isInterrupted()) {
                String message = getNextMessageFromQueue();
                sendMessageToClient(message);
            }
        } catch (Exception e) {
            // Commuication problem
        }

        // Communication is broken. Interrupt both listener and sender threads
        mClientInfo.mClientListener.interrupt();
        mServerDispatcher.deleteClient(mClientInfo);
    }
}
