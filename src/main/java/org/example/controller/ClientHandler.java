package org.example.controller;

/**
 * manage the connection with a specific client
 */
public class ClientHandler implements Runnable{

    private int currentSegmentSize; //4096 - 8192 - ...

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
