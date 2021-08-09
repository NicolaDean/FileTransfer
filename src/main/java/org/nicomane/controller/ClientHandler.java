package org.nicomane.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * manage the connection with a specific client
 */
public class ClientHandler implements Runnable{

    private Socket          clientSocket;
    private Scanner         input;
    private PrintWriter     output;

    private Object          lock;
    private int             currentSegmentSize; //4096 - 8192 - ...

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;

        this.initializeReader(clientSocket);
        this.initializeWriter(clientSocket);
        lock = new Object();

    }

    @Override
    public void run() {

        while(true)
        {
            String message = this.input.nextLine();
            if (message.equals("quit")) {
                System.out.println("Client " + clientSocket.getInetAddress() + " Exited the server");
                break;
            } else {
                System.out.println("MSG: " + message);
            }
        }
    }

    public void waitMessage()
    {

    }

    public void readFile()
    {

    }

    public void readFileSegment()
    {

    }

    /**
     * set the size of file segment sended from client
     * @param segmentSize the size in byte of the segment
     */
    public void setCurrentSegmentSize(int segmentSize)
    {
        this.currentSegmentSize = segmentSize;
    }

    /**
     * Initialize the Input stream of the socket
     * @param s
     */
    public void initializeReader(Socket s)
    {
        try {
            this.input  = new Scanner(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the Output stream of the socket
     * @param s
     */
    public void initializeWriter(Socket s)
    {
        try {
            this.output = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
