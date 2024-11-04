package edu.seg2105.edu.server.ui;

import java.io.*;
import java.util.Scanner;

//Import classes outside of package
import edu.seg2105.client.common.ChatIF; //Import ChatIF
import edu.seg2105.edu.server.backend.EchoServer; //Import EchoServer 

/*
 * @author Avery Ng
 * NOTE : Some code is copied from the ClientConsole class
 * This class is used to create a console on the server side which allows the server user to perform similar commands as the client console
 */

public class ServerConsole implements ChatIF {

    private EchoServer server; //Instance of EchoServer to which the console is associated
    private Scanner fromConsole; //Scanner to read from the console

    //Constructor for ServerConsole
    public ServerConsole(int port) {
        server = new EchoServer(port, this);
        fromConsole = new Scanner(System.in); //Create scanner object to read from console
    }

    /*
     * Waits for user input from the console
     * If the input is a command, it is handled by the handleCommand() method
     * Otherwise, the message is sent to all clients and displayed on the console
     */
    public void accept() {
        try {

        String message;

        while (true) {
            message = fromConsole.nextLine();
            if (message.charAt(0) == '#') {
                handleCommand(message); //Call handleCommand() method if message begins with #
            } 
            else {
                server.sendToAllClients("SERVER MSG>" + message); //Send message to all clients
                display("SERVER MSG>" + message); //Display message on console
            }
        }
        } catch (Exception ex) {
            System.out.println("Unexpected error while reading from console!");
        }
    }
   
    /*
   * This method handles commands input from the console, as described by the assignment :
   * 
   * #quit : allows server to quit 
   * #stop : stops the server from listening for connections
   * #close : stops the server from listening for connections and disconnects all clients
   * #setport : calls the setPort() method in EchoServer to change the port if the server is closed
   * #start : starts the server listening for connections
   * #getport : display the current port
   */
    public void handleCommand(String message) {
        String[] subStrings = message.split(" "); // Split the message into command and arguments (substrings)
        String command = subStrings[0]; // First substring in the message
    
        switch (command) {
    
          case "#quit":
            try {
                server.close(); //Close the server
            } catch (IOException e) {
                display("Error closing server."); //Catch unexpected error and display error message
            }
            System.exit(0); //Terminate the server
            break;
    
          case "#stop":
            server.stopListening();
            display("Server stopped listening for connections.");
            break;

          case "#close":
            try {
                server.close();
                display("Server stopped listening for connections and disconnected all clients.");
            } catch (IOException e) {
                display("Error closing server.");
            }
            break;
    
          case "#setport":
            if (!server.isListening()) {
                if (subStrings.length > 1) {
                    server.setPort(Integer.parseInt(subStrings[1]));
                    display("Port set to " + subStrings[1]); //Display success message
                } else {
                    display("No port specified."); //If command is called with no port, display error message
                }
            } else {
                display("Cannot set port while server is listening."); //If server is already listening, display error message
            }
            break;
    
          case "#start":
            if (!server.isListening()) {
                try {
                    server.listen(); // Start listening for connections and display success message
                    display("Server listening for connections.");
                } catch (Exception ex) {
                    display("Error : Could not listen for clients."); //Catch unexpected error and display error message
                }
            } else {
                display("Server is already listening for connections."); //If server is already listening, display error message
            }
            break;

          case "#getport":
            display("Port : " + server.getPort()); //Display the current port
            break;
        }
      }

    /*
     * This method overrides the display method in ChatIF and displays the server message sent to all clients and the result of commands
     */
    @Override
    public void display(String message) {
        System.out.println(">  " + message);
    }
    public static void main(String[] args) {
        int port = 5555; // Default port

        try {
            port = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No port specified. Using default port: " + port);
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number. Using default port: " + port);
        }

        ServerConsole serverConsole = new ServerConsole(port);
        serverConsole.accept(); // Wait for console data
    }
}
