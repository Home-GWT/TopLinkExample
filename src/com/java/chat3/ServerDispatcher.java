package com.java.chat3;

/**
 * Класс ServerDispatcher предназначен для прослушивания полученных сообщений
 * От клиентов и отправлять их всем клиентам, подключенным к
 * Чат-сервер.
 */

import java.net.*;
import java.util.*;

public class ServerDispatcher extends Thread {
    private Vector mMessageQueue = new Vector();
    private Vector mClients = new Vector();

    /**
     * Добавляет данный клиент в список клиентов сервера.
     */
    public synchronized void addClient(ClientInfo aClientInfo) {
        mClients.add(aClientInfo);
    }

    /**
     * Удаляет данный клиент из списка клиентов сервера
     * Если клиент находится в списке.
     */
    public synchronized void deleteClient(ClientInfo aClientInfo) {
        int clientIndex = mClients.indexOf(aClientInfo);
        if (clientIndex != -1)
            mClients.removeElementAt(clientIndex);
    }

    /**
     * Добавляет данное сообщение в очередь сообщений диспетчера и уведомляет об этом
     * Thread, чтобы разбудить читателя очереди сообщений (метод getNextMessageFromQueue).
     * Метод dispatchMessage вызывается другими потоками (ClientListener), когда
     * Сообщение прибывает.
     */
    public synchronized void dispatchMessage(ClientInfo aClientInfo, String aMessage) {
        Socket socket = aClientInfo.mSocket;
        String senderIP = socket.getInetAddress().getHostAddress();
        String senderPort = "" + socket.getPort();
        aMessage = senderIP + ":" + senderPort + " : " + aMessage;
        mMessageQueue.add(aMessage);
        notify();
    }

    /**
     * @return и удалит следующее сообщение из очереди сообщений. Если нет
     * Сообщения в очереди, попадает во сне, пока не будет уведомлен методом dispatchMessage.
     */
    private synchronized String getNextMessageFromQueue()
            throws InterruptedException {
        while (mMessageQueue.size()==0)
            wait();
        String message = (String) mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
        return message;
    }

    /**
     * Отправляет данное сообщение всем клиентам в списке клиентов. Фактически
     * Сообщение добавляется в очередь сообщений потока отправителя клиента, и это
     * Уведомление отправителя клиента.
     */
    private synchronized void sendMessageToAllClients(String aMessage) {
        for (int i=0; i<mClients.size(); i++) {
            ClientInfo clientInfo = (ClientInfo) mClients.get(i);
            clientInfo.mClientSender.sendMessage(aMessage);
        }
    }

    /**
     * Бесконечно читает сообщения из очереди и отправляет их
     * Всем клиентам, подключенным к серверу.
     */
    public void run() {
        try {
            while (true) {
                String message = getNextMessageFromQueue();
                sendMessageToAllClients(message);
            }
        } catch (InterruptedException ie) {
            // Thread interrupted. Stop its execution
        }
    }
}
