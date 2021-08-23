package org.nicomane.controller;

import org.nicomane.utils.DefaultValues;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client implements Launcher {

    private DataInputStream         input;
    private OutputStream    output;

    public Client(){
    }



    /**
     * Initialize the Input stream of the socket
     * @param s
     */
    public void initializeReader(Socket s)
    {
        try {
            this.input = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            //this.input  = new Scanner(s.getInputStream());
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
            this.output = s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send an array of byte through the network
     * @param data          array of byte to send
     * @throws IOException  if something goes wrong during the streaming of data
     */
    public void sendByteThroughSocket(byte[] data) throws IOException {
        this.output.write(data);
        this.output.flush();
    }

    /**
     * Send a specified file through network as a byte array stream of a fixed size packets
     * @param filePath      path of file to send
     * @throws IOException  if something goes wrong during the streaming of data
     */
    public void sendFileData(String filePath) throws IOException {

        byte[] data = readFile(filePath);
        this.sendByteThroughSocket(data);
    }

    public void sendFileDataFixed(String filePath) throws IOException {
        BufferedReader reader =  new BufferedReader(new FileReader(filePath));

        int count;
        byte[] buffer = new byte[DefaultValues.defaultFileSegmentSize]; // or 4096, or more

        while((count = this.input.read(buffer)) > 0)
        {
            this.output.write(buffer);
        }
        reader.close();
    }

    /**
     * Read a file and convert it into a byte array
     * @param filePath file to read
     * @return an array of bytes
     * @throws IOException if something goes wrong during the load of files or conversion
     */
    public byte[] readFile(String filePath) throws IOException
    {
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        return data;
    }

    @Override
    public void start() {
        try {
            String ip = "192.168.1.143";
            int port = 1234;

            Socket s = new Socket(ip,port);
            initializeReader(s);
            initializeWriter(s);

            sendFileData(DefaultValues.test_file_name);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
