package ru.GeekBrains.Rostislav.Java2Group3.lesson7.ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
        private final int PORT = 8189;
        private final BufferedReader stopServer = new BufferedReader(new InputStreamReader(System.in)); // для остановки сервера из консоли

        private List<ClientHandler> clients;
        private AuthService authService;

        public AuthService getAuthService() {
            return authService;
        }

        public MyServer() {
            try (ServerSocket server = new ServerSocket(PORT)) {
                authService = new BaseAuthService();
                authService.start();
                clients = new ArrayList<>();
                new Thread(() -> {
                    while (true) {
                        try {
                            if(stopServer.readLine().equals("/stop")) {   // для остановки сервера из консоли набрать /stop
                                broadcastMsg("Сервер выключен администратором.");
                                System.exit(0);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                while (true) {
                    System.out.println("Сервер ожидает подключения");
                    Socket socket = server.accept();
                    System.out.println("Клиент подключился");
                    new ClientHandler(this, socket);
                }
            } catch (IOException e) {
                System.out.println("Ошибка в работе сервера");
            } finally {
                if (authService != null) {
                    authService.stop();
                }
            }
        }

        public synchronized boolean isNickBusy(String nick) {
            for (ClientHandler o : clients) {
                if (o.getName().equals(nick)) {
                    return true;
                }
            }
            return false;
        }

        public synchronized void broadcastMsg(String msg) {
            for (ClientHandler o : clients) {
                    o.sendMsg(msg);
                }
        }
        public synchronized void toOneMsg(String nick, String msg) {            // персональное сообщение
            for (ClientHandler o : clients) {
                if(o.getName().equals(nick) && !nick.equals("")) {
                    o.sendMsg(msg);
                }
            }
        }

        public synchronized void unsubscribe(ClientHandler o) {
            clients.remove(o);
        }

        public synchronized void subscribe(ClientHandler o) {
            clients.add(o);
        }
    }