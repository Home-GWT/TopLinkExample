package com.java.chat3;

/**
 * @see http://inetjava.sourceforge.net/lectures/part1_sockets/InetJava-1.9-Chat-Client-Server-Example.html
 *      ***************************************************************************************************
 * Nakov Chat Server - многопоточный чат-сервер. Он принимает несколько клиентов
 * Одновременно и обслуживает их. Клиенты могут отправлять сообщения на сервер.
 * Когда какой-либо клиент отправляет сообщение на сервер, это сообщение отправляется
 * Всем клиентам, подключенным к серверу.
 *
 * Сервер состоит из двух компонентов: «серверное ядро» и «клиентские обработчики».
 *
 * «Ядро сервера» состоит из двух потоков:
 * - NakovChatServer - принимает клиентские соединения, создает потоки клиента для
 * Обрабатывать их и запускать эти потоки
 * - ServerDispatcher - ожидает сообщений и отправляет полученные сообщения в
 * Все клиенты, подключенные к серверу
 *
 * «Обработчики клиентов» состоят из двух потоков:
 * - ClientListener - прослушивает сообщения, поступающие из сокета и
 * Пересылает их в поток ServerDispatcher
 * - ClientSender - отправляет сообщения клиенту
 *
 * Для каждого принятого клиента потоки ClientListener и ClientSender
 * Создан и запущен. Объект ClientInfo также создается, чтобы содержать
 * Информация о клиенте. Также объект ClientInfo добавляется к
 * Список клиентов ServerDispatcher. Когда какой-либо клиент отключен, это
 * Удалены из списка клиентов и как ClientListener, так и ClientSender
 * Потоки прерываются.
 *
 *
 * Класс NakovChatServer является точкой входа для программы. Он открывает сервер
 * Socket, запускает поток диспетчера и бесконечно принимает клиентские соединения,
 * Создает потоки для их обработки и запускает эти потоки.
 */

import java.net.*;
import java.io.*;

public class NakovChatServer {
    public static final int LISTENING_PORT = 2002;

    public static void main(String[] args) {
        // Open server socket for listening
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            System.out.println("NakovChatServer started on port " + LISTENING_PORT);
        } catch (IOException se) {
            System.err.println("Can not start listening on port " + LISTENING_PORT);
            se.printStackTrace();
            System.exit(-1);
        }

        // Start ServerDispatcher thread
        ServerDispatcher serverDispatcher = new ServerDispatcher();
        serverDispatcher.start();

        // Accept and handle client connections
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientInfo clientInfo = new ClientInfo();
                clientInfo.mSocket = socket;
                ClientListener clientListener = new ClientListener(clientInfo, serverDispatcher);
                ClientSender clientSender = new ClientSender(clientInfo, serverDispatcher);
                clientInfo.mClientListener = clientListener;
                clientInfo.mClientSender = clientSender;
                clientListener.start();
                clientSender.start();
                serverDispatcher.addClient(clientInfo);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
