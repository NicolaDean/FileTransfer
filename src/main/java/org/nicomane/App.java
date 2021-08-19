package org.nicomane;

import org.nicomane.controller.Client;
import org.nicomane.controller.Launcher;
import org.nicomane.controller.Server;
import org.nicomane.utils.DefaultValues;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        Launcher app = new Server();

        for(String arg: args)
        {
            //CHECK IF IS CLI OR GUI
            if(arg.equals(DefaultValues.cli_arg_1) || arg.equals(DefaultValues.cli_arg_2))
            {

            }else if(arg.equals(DefaultValues.gui_arg_1) || arg.equals(DefaultValues.gui_arg_2))
            {

            }

            //CHECK IF IS SERVER OR CLIENT
            if(arg.equals(DefaultValues.client_arg_1) || arg.equals(DefaultValues.client_arg_2))
            {
                app = new Client();
            } else if(arg.equals(DefaultValues.server_arg_1) || arg.equals(DefaultValues.server_arg_2))
            {
                app = new Server();
            }
        }

        app.start();
        System.out.println( "Hello World!" );
    }
}
