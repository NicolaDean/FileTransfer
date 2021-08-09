package org.nicomane.controller;

import java.net.Socket;

/**
 * manage the connection with a specific client
 */
public class ClientHandler implements Runnable{

    private Socket  clientSocket;
    private int     currentSegmentSize; //4096 - 8192 - ...

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {

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

}
