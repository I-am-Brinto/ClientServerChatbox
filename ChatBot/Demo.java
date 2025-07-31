package ChatBot;
import java.io.*;

import java.net.*;

public class Demo {
    public static void main(String[] args) {
        try {
            // ১. সার্ভারে কানেক্ট হওয়া (server IP & port)
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server.");

            // ২. ইনপুট স্ট্রিম: সার্ভার থেকে মেসেজ পড়া
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ৩. আউটপুট স্ট্রিম: সার্ভারে মেসেজ পাঠানো
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // ৪. ইউজারের কনসোল থেকে ইনপুট নেওয়া
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage, clientMessage;

            // ৫. মেসেজিং লুপ শুরু
            while (true) {
                System.out.print("You: ");
                clientMessage = console.readLine();
                output.println(clientMessage);    // সার্ভারে পাঠানো

                if (clientMessage.equalsIgnoreCase("exit")) break; // চ্যাট শেষ

                serverMessage = input.readLine(); // সার্ভার থেকে মেসেজ পড়া
                System.out.println("Server: " + serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) break; // চ্যাট শেষ
            }

            // ৬. কানেকশন বন্ধ করা
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
