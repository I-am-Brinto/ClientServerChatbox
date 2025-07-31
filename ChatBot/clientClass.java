package ChatBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientClass {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to Server");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage, clientMessage;

            while (true) {
                System.out.print("You: ");
                clientMessage = console.readLine();
                output.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) break;

                serverMessage = input.readLine();
                System.out.println("Brinto: " + serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) break;
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
