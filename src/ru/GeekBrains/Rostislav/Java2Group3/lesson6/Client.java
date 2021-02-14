package ru.GeekBrains.Rostislav.Java2Group3.lesson6;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame {

    DataInputStream inputStream;
    DataOutputStream outputStream;

    JTextArea jta = new JTextArea(10, 10);
    JTextField message = new JTextField();

    public Client() {
            setTitle("Test chat");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBounds(300, 100, 500, 400);

            JPanel talk = new JPanel();
            JPanel myMessage = new JPanel();
            myMessage.setPreferredSize(new Dimension(Client.WIDTH, 50));

            Font font = new Font("monospaced", Font.PLAIN, 30);
            jta.setFont(font);
            jta.setBackground(new Color(220, 250, 220));
            jta.setEditable(false);  // запрет ввода напрямую в основное окно чата

            JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            talk.setLayout(new BorderLayout());
            talk.add(jsp, BorderLayout.CENTER);
            add(talk, BorderLayout.CENTER);

            myMessage.setLayout(new BorderLayout());
            JButton send = new JButton("Send");
            send.setBackground(new Color(200, 200, 250));
            myMessage.add(send, BorderLayout.EAST);
            message.setFont(font);
            myMessage.add(message, BorderLayout.CENTER);
            add(myMessage, BorderLayout.SOUTH);

            send.addActionListener(e -> send());
            message.addActionListener(e -> send());

            try {
                connection();
            } catch (IOException ignored) { }

            setVisible(true);
    }

    public void connection() throws IOException {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 8189;
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                String messageFromServer;
                while (true){
                    messageFromServer = inputStream.readUTF();
                    if(messageFromServer.equalsIgnoreCase("/end")){
                        this.dispose();
                        break;
                    }
                    jta.append(messageFromServer + System.lineSeparator()); // печать в окно чата сообщений сервера
                }
            } catch (IOException ignored){ }
        }).start();
    }
    public void send() {
        if(message.getText() != null && !message.getText().trim().isEmpty()) {
            try {
                outputStream.writeUTF(message.getText()); // отправка сообщений на сервер
                message.setText("");
            } catch (IOException ignored) { }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }
}

