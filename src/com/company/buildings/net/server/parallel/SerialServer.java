package com.company.buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Opsymonroe on 04.11.2017.
 */
public class SerialServer {

    public static void main(String[] args) {
        int port = 7777;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(7777);
            while (true) {
                Thread thread = new Thread(new SerialClientThread(serverSocket.accept()));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
