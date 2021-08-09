package org.nicomane.testing;

import org.nicomane.controller.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Scanner         input;
    private PrintWriter     output;

    public static void main( String[] args ) {
        Client c = new Client();
    }


    public Client(){
        String ip = "192.168.1.32";
        int port = 1234;

        try {
            Socket s = new Socket(ip,port);
            initializeReader(s);
            initializeWriter(s);

            output.println("BABBANOOOOO");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
