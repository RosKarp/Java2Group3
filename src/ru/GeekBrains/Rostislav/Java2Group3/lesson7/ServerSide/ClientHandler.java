package ru.GeekBrains.Rostislav.Java2Group3.lesson7.ServerSide;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
        private MyServer myServer;
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;

        private String name;
        public String getName() {
            return name;
        }
        private static final long authorizationBlockingTime = 180000; // 3 минуты блокировки при неправильном логине или пароле

        public ClientHandler(MyServer myServer, Socket socket) {
            try {
                this.myServer = myServer;
                this.socket = socket;
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
                this.name = "";
                new Thread(() -> {
                    try {
                        authentication();
                        readMessages();
                    } catch (IOException | InterruptedException ignored) { }
                    finally {
                        closeConnection();
                    }
                }).start();
            } catch (IOException e) {
                throw new RuntimeException("Проблемы при создании обработчика клиента");
            }
        }

        public void authentication() throws IOException, InterruptedException {
            int countOfFault = 1;
            while (true) {
                String str = in.readUTF();
                if (str.startsWith("/auth")) {
                    String[] parts = str.split("\\s");
                    if(parts.length == 3) {
                        String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                        if (nick != null) {
                            if (!myServer.isNickBusy(nick)) {
                                sendMsg("/authok " + nick);
                                name = nick;
                                myServer.broadcastMsg(name + " зашел в чат");
                                myServer.subscribe(this);
                                return;
                            } else {
                                sendMsg("Учетная запись уже используется");
                                countOfFault++;
                            }
                        } else {
                            sendMsg("Неверные логин/пароль");
                            countOfFault++;
                        }
                        if(countOfFault == 6) {                                                                      // 3 попытки до временной блокировки
                            sendMsg("Ввод логина/пароля заблокирован на " + authorizationBlockingTime/1000 + " cek");
                            sendMsg("Ожидайте разрешения на ввод логина/пароля");
                            Thread.sleep(authorizationBlockingTime);
                            sendMsg("Введите верный пароль");
                        }
                    }
                    countOfFault++;
                    if(countOfFault == 6) {
                        sendMsg("Ввод логина/пароля заблокирован на " + authorizationBlockingTime/1000 + " cek");
                        sendMsg("Ожидайте разрешения на ввод логина/пароля");
                        Thread.sleep(authorizationBlockingTime);
                        sendMsg("Введите верный пароль");
                    }
                }
            }
        }

        public void readMessages() throws IOException {
            while (true) {
                String strFromClient = in.readUTF();
                System.out.println("от " + name + ": " + strFromClient);
                if (strFromClient.equals("/end")) {
                    return;
                }
                if(strFromClient.startsWith("/w ")) {
                    String[] toOne = strFromClient.split("\\s");
                    String nick = toOne[1];
                    StringBuilder builder = new StringBuilder();
                    for (int i = 2; i < toOne.length; i++) {
                        builder.append(toOne[i]).append(" ");
                    }
                    String message = name + ": " + builder.toString();
                    myServer.toOneMsg(nick, message);                        // персональное сообщение
                } else {
                    myServer.broadcastMsg(name + ": " + strFromClient);
                }
            }
        }

        public void sendMsg(String msg) {
            try {
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void closeConnection() {
            myServer.unsubscribe(this);
            myServer.broadcastMsg(name + " вышел из чата");
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }