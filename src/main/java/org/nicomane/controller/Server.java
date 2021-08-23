package org.nicomane.controller;

import org.nicomane.utils.DefaultValues;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server  implements Launcher{

    private ExecutorService     executor;
    private int                 port;
    private ServerSocket        serverSocket;

    public Server() {
        this.executor             = Executors.newCachedThreadPool();
        port = DefaultValues.port;
    }

    public Server(int port)
    {
        this.executor             = Executors.newCachedThreadPool();
        this.port = port;
    }

    /**
     * Start to listen and wait for clients to connect
     */
    public void start()
    {
        this.serverSocketInitialization();
        this.waitNewClient();
    }

    /**
     * initialize server socket with the args port or default port
     */
    public void serverSocketInitialization()
    {
        try {
            this.serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * wait for new client to connect
     */
    public void waitNewClient()
    {
        while(true)
        {
            System.out.println("Waiting client");
            acceptClient();
        }
    }

    /**
     * when a client finaly connect this function create a new client handler thread to handle its operations
     */
    private void acceptClient() {
        try {
            Socket socket = serverSocket.accept();
            executor.submit(new ClientHandler(socket));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
