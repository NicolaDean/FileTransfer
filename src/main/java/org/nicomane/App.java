package org.nicomane;

import org.nicomane.controller.Server;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Server s = new Server();

        s.start();
        System.out.println( "Hello World!" );
    }
}
