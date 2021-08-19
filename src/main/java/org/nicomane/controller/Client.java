package org.nicomane.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Launcher {

    private Scanner         input;
    private PrintWriter     output;

    public Client(){
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

    public void readFile() throws FileNotFoundException {

        File myObj = new File("D:\\FileTransferServer\\src\\main\\java\\org\\nicomane\\AM12.pem");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.output.println(data);
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
