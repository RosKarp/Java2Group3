package ru.GeekBrains.Rostislav.Java2Group3.lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {

    public static void main(String[] args) {

        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(8189);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Server start...");
            socket = serverSocket.accept();
            System.out.println("Client join.");

            DataInputStream inputStreamServer = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStreamServer = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    String str;
                    while (true) {
                        str = inputStreamServer.readUTF();
                        System.out.println(str);                            // сообщения клиента
                        if (str.equalsIgnoreCase("/end")) {    // если клиент отключается, то выход из потока общения с ним
                            outputStreamServer.writeUTF(str);
                            break;
                        }
                        outputStreamServer.writeUTF(str); // в окне клиента отображаются только те его сообщения, которые были приняты сервером
                    }
                }
                 catch (IOException ignored) { }
            }).start();

            String console;

            while(true) {
                 console = br.readLine();
                 if(console.equalsIgnoreCase("/end\r\n")) {  // способ завершения работы сервера - ввод в консоль /end
                    break;
                 }
                 if(!(console.trim().isEmpty())) {
                    outputStreamServer.writeUTF(console);  // отсылка сообщения клиенту
                 }
            }
        }
        catch (IOException ignored){ }
    }
}