import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Server details
        final String SERVER_ADDRESS = "172.20.10.2";
        final int SERVER_PORT = 6666;
        boolean isConnect = true;

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Set up input and output streams for communication
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            while (isConnect) {
                // send to server
                System.out.println("Client write");
                String msg  = scanner.next();


                if (!msg.equals("quit")) {
                    // Send user input to the server
                    writer.println(msg);

                    // Receive and print the server's response
                    String serverResponse = reader.readLine();
                    System.out.println("Server response: " + serverResponse);
                }

                if (msg.equals("quit")) {
                    System.out.println("Exiting...");

                    // Close the socket and input/output streams
                    reader.close();
                    writer.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }
}
