package org.nicomane.controller;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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


    public void readImage()
    {

    }

    public void readVideo()
    {

    }

    public void readFile() throws IOException {

        File myObj = new File("D:\\FileTransferServer\\src\\main\\java\\org\\nicomane\\0d6730a87776461f171f85ba791ca768.pdf");
        Scanner myReader = new Scanner(myObj);
        byte[] array = Files.readAllBytes(Paths.get("D:\\FileTransferServer\\src\\main\\java\\org\\nicomane\\partenza1.JPG"));
        /*while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.output.println(data);
        }*/

        this.output.write(array);
        this.output.flush();
        this.output.write("quit".getBytes(StandardCharsets.UTF_8));
        this.output.flush();
        myReader.close();
    }

    @Override
    public void start() {
        try {
            String ip = "192.168.1.143";
            int port = 1234;


            Socket s = new Socket(ip,port);
            initializeReader(s);
            initializeWriter(s);

            readFile();
            output.flush();

            output.write("\n".getBytes(StandardCharsets.UTF_8));
            output.write("quit".getBytes(StandardCharsets.UTF_8));
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
