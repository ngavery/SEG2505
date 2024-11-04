package edu.seg2105.client.ui;
// This file contains material supporting section 3.7 of the textbook:

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.Scanner;

import edu.seg2105.client.backend.ChatClient;
import edu.seg2105.client.common.*;

/**
 * This class constructs the UI for a chat client. It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 */
public class ClientConsole implements ChatIF {
  // Class variables *************************************************

  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;

  // Instance variables **********************************************

  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client;

  /**
   * Scanner to read from the console
   */
  Scanner fromConsole;

  // Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public ClientConsole(String loginID, String host, int port) {
    try {
      client = new ChatClient(loginID, host, port, this);

    } catch (IOException exception) {
      System.out.println("Error: Can't setup connection!"
          + " Terminating client.");
      System.exit(1);
    }

    // Create scanner object to read from console
    fromConsole = new Scanner(System.in);
  }

  // Instance methods ************************************************

  /**
   * This method waits for input from the console. Once it is
   * received, it sends it to the client's message handler.
   * 
   * Modified by Avery Ng :
   * Only sends message to server if client is connected, otherwise the client would terminate itself if attempting to login after being disconnected
   * Calls handleCommand() method to handle # commands input from the console
   */
  public void accept() {
    try {

      String message;

      while (true) {
        message = fromConsole.nextLine();
        if (message.charAt(0) == '#') {
          handleCommand(message);
        } else {
          if (client.isConnected()) { 
          } else {
            System.out.println("Not connected to server. Please use #login to connect.");
          }
        }
      }
    } catch (Exception ex) {
      System.out.println("Unexpected error while reading from console!");
    }
  }

  /*
   * Written by Avery Ng
   * 
   * This method handles commands input from the console, as described by the assignment :
   * 
   * #quit : close connection and terminate client
   * #logoff : close connection without terminating client
   * #sethost : set the host to connect to
   * #setport : set the port to connect to
   * #login : open connection to server
   * #gethost : display the current host
   * #getport : display the current port
   */
  public void handleCommand(String message) {
    String[] subStrings = message.split(" "); // Split the message into command and arguments (substrings)
    String command = subStrings[0]; // First substring in the message

    switch (command) {

      case "#quit":
        client.quit();
        try {
          client.closeConnection(); // Close the connection
        } catch (IOException e) {
          System.out.println("Error: Could not quit."); // If error occurs, print error message
        }
        System.exit(0); // Terminate the client
          break;

      case "#logoff":
        try {
          client.closeConnection(); // Close the connection
          System.out.println("Client logged off."); // Print success message
        } catch (IOException e) {
          System.out.println("Error: Could not log off."); // If error occurs, print error message
        }
          break;

      case "#sethost":
        if (!client.isConnected()) {
          if (subStrings.length > 1) {
            client.setHost(subStrings[1]); // Call super method setHost() and pass the host (second substring) as an argument
          } else {
            System.out.println("No host specified."); // If command is called with no host, print error message
          }
        }
        else{
          System.out.println("Cannot set host while connected to the server."); // If client is already connected, print error message
        }
        break;

      case "#setport":
        if (!client.isConnected()) {
          if (subStrings.length > 1) {
            client.setPort(Integer.parseInt(subStrings[1])); // Call super method setPort() and pass the port (second subtstring converted to int) as an argument 
          } else {
            System.out.println("No port specified."); // If command is called with no port, print error message
          }
        }
        else {
          System.out.println("Cannot set port while connected to the server."); // If client is already connected, print error message
        }
        break;

      case "#login":
        if (!client.isConnected()) {
          try {
            client.openConnection();
            System.out.println("Connected to server.");  // Open the connection and print success message
        } catch (IOException e) {
            System.out.println("Error: Connection failed."); // If connection fails, print error message
            }
        }
        else {
          System.out.println("Client already connected to server."); // If client is already connected, print error message
          }
          break;
        
      case "#gethost":
          System.out.println("Host : " + client.getHost()); // Display current host
          break;

      case "#getport":
          System.out.println("Port : " + client.getPort()); // Display current port
          break;
    }
  }
  /**
   * This method overrides the method in the ChatIF interface. It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message) {
    System.out.println("> " + message);
  }

  // Class methods ***************************************************

  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  public static void main(String[] args) {
    String loginID;
    String host = "";
    int port = DEFAULT_PORT;

    try {
      loginID = args[0]; // Assign login ID if provided
  } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("ERROR - No login ID specified. Connection aborted."); // If no login ID is provided, print error message
      System.exit(1);
      return;
  }

  // Check if hostname and port are provided
  if (args.length > 1) {
      host = args[1]; // Assign hostname if provided
  }

  if (args.length > 2) {
      try {
          port = Integer.parseInt(args[2]); // Parse port only if explicitly provided
      } catch (NumberFormatException e) {
          System.out.println("Invalid port number. Terminating client.");
          System.exit(1);
      }
  }

    ClientConsole chat = new ClientConsole(loginID, host, port);
    chat.accept(); // Wait for console data
  }
}
// End of ConsoleChat class
