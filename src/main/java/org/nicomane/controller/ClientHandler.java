package org.nicomane.controller;

import org.nicomane.utils.DefaultValues;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * manage the connection with a specific client
 */
public class ClientHandler implements Runnable{

    private Socket          clientSocket;
    private DataInputStream input;
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
        FileOutputStream fos = null;

        try {
             fos = new FileOutputStream(DefaultValues.test_file_destination);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



       // while(true)
       // {
            byte[] buffer = new byte[DefaultValues.defaultFileSegmentSize]; // or 4096, or more
            byte[] quit   = new byte[4];
            quit = "quit".getBytes(StandardCharsets.UTF_8);
            try{
                int count;
                while ((count = this.input.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, count);
                    System.out.println(buffer.toString() + " -> " + count);
                    fos.flush();

                    if(Arrays.equals(buffer, quit))
                    {
                        System.out.println(buffer + " -> " + count);
                        System.out.println("QUIIIT");
                    }
                }
                fos.close();
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

            /*if (message.equals("quit")) {
                System.out.println("Client " + clientSocket.getInetAddress() + " Exited the server");

                try {
                    fos.close();
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("MSG: " + message);
                message = message + "\n";
                try {
                    fos.write(message.getBytes(StandardCharsets.UTF_8));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        //}

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
            //this.input  = new Scanner(s.getInputStream());
            this.input = new DataInputStream(new BufferedInputStream(s.getInputStream()));
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
