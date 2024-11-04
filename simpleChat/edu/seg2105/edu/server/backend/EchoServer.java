package edu.seg2105.edu.server.backend;
// This file contains material supporting section 3.7 of the textbook:

import java.io.IOException;

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import com.lloseng.ocsf.server.*;

import edu.seg2105.client.common.ChatIF; //Import ChatIF - Avery Ng
import edu.seg2105.edu.server.ui.ServerConsole; //Import ServerConsole - Avery Ng

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 */
public class EchoServer extends AbstractServer {
  // Class variables *************************************************

  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;

  private ChatIF serverUI; //Instance of ChatIF - Avery Ng

  // Constructors ****************************************************

  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port, ChatIF serverUI) {
    super(port);
    this.serverUI = serverUI; //Allows EchoServer to interact with server console - Avery Ng
  }

  // Instance methods ************************************************

  /**
   * This method handles any messages received from the client.
   *
   * @param msg    The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient(Object msg, ConnectionToClient client) {
    String message = msg.toString();
    System.out.println("Message received: " + msg + " from " + client);
    this.sendToAllClients(msg);

    if (client.getInfo("loginID") == null) { //If client is not logged in
      
      if (message.startsWith("#login")) {
        client.setInfo("loginID", message.substring(7).trim()); //Extract loginID from message (by starting at char 7) and set it as client info

        try {
          client.sendToClient(client.getInfo("loginID") + " has logged on.");
        } catch (IOException e) {
          System.out.println("Error: Could not send login confirmation message to client.");
        }
      } else { //If client is not logged in and the message is not a login message
        try {
          client.sendToClient("Error: You must log in first."); 
          client.close();
        } catch (IOException e) {
          System.out.println("Error closing client connection.");
        }
      }
    }
    else { //If client is already logged in
      String loginID = client.getInfo("loginID").toString();
      String updatedMessage = loginID + ": " + message;
      System.out.println("Message received: " + updatedMessage + " from " + client);
      this.sendToAllClients(updatedMessage);
    }
  }

  /**
   * This method overrides the one in the superclass. Called
   * when the server starts listening for connections.
   */
  protected void serverStarted() {
    System.out.println("Server listening for connections on port " + getPort());
  }

  /**
   * This method overrides the one in the superclass. Called
   * when the server stops listening for connections.
   */
  protected void serverStopped() {
    System.out.println("Server has stopped listening for connections.");
  }

  /*
   * Written by Avery Ng
   */
  @Override
  protected void clientConnected(ConnectionToClient client) {
    System.out.println("Client connected : " + client);
  }
  /*
   * Written by Avery Ng
   */
  @Override
  protected void clientDisconnected(ConnectionToClient client) {
    System.out.println("Client disconnected : " + client);
  }

  // Class methods ***************************************************

  /**
   * This method is responsible for the creation of
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on. Defaults to 5555
   *                if no argument is entered.
   */
  public static void main(String[] args) {
    int port = 0; // Port to listen on

    try {
      port = Integer.parseInt(args[0]); // Get port from command line
    } catch (Throwable t) {
      port = DEFAULT_PORT; // Set port to 5555
    }

    ServerConsole serverUI = new ServerConsole(port); //Instantiate ServerConsole - Avery Ng
    EchoServer sv = new EchoServer(port, serverUI); //Instantiate EchoServer - Avery Ng

    try {
      sv.listen(); // Start listening for connections
    } catch (Exception ex) {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
// End of EchoServer class
