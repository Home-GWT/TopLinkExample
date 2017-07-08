package com.java.chat3;

/**
 * Класс ClientInfo содержит информацию о клиенте, подключенном к серверу.
 */

import java.net.Socket;

public class ClientInfo {
    public Socket mSocket = null;
    public ClientListener mClientListener = null;
    public ClientSender mClientSender = null;
}
