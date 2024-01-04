import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    private PrintStream out;         // Output stream for sending messages to the server
    private BufferedReader in;       // Input stream for receiving messages from the server
    private Socket socketCreator;    // Socket for creating a connection with the server

    // Method to connect the client to the server
    public void connectClient(String ip, int port) throws IOException {
        socketCreator = new Socket(ip, port);  // Create a socket with the given IP address and port
        out = new PrintStream(socketCreator.getOutputStream(), true);   // Initialize output stream for sending messages
        in = new BufferedReader(new InputStreamReader(socketCreator.getInputStream()));  // Initialize input stream for receiving messages
    }

    // Method to send a message to the server and receive a response
    public String sendMessage(String msg) throws IOException {
        out.println(msg);              // Send the message to the server
        String resp = in.readLine();   // Receive the server's response
        return resp;                   // Return the response to the caller
    }

    // Method to stop the connection by closing streams and the socket
    public void stopConnection() throws IOException {
        in.close();                 // Close the input stream
        out.close();                // Close the output stream
        socketCreator.close();      // Close the socket and terminate the connection
    }
}
