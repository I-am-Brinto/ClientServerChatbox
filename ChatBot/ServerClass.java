package ChatBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is waiting for client....");

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected Successfully...");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage, clientMessage;

            while (true) {
                clientMessage = input.readLine();

                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client Disconnected");
                    break;
                }
                System.out.println("Client : " + clientMessage);
                System.out.print("You: ");

                serverMessage = console.readLine();
                output.println(serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server Disconnected");
                    break;
                }
            }
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
