// Client2 и Client3 - решение п.2 и п.3 ДЗ
// Client1 - решение п.1 и п.3 ДЗ
// причины этого в заголовке Client1

package ru.GeekBrains.Rostislav.Java2Group3.lesson8.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client3 extends JFrame {

    JTextArea chatArea = new JTextArea(10, 10);
    JTextField message = new JTextField();

    private DataInputStream in;
    private DataOutputStream out;

    private boolean isAuthorized = false;

    public Client3() {
        setTitle("Chat nick3");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 100, 500, 400);

        JPanel talk = new JPanel();
        JPanel myMessage = new JPanel();
        myMessage.setPreferredSize(new Dimension(Client3.WIDTH, 50));

        Font font = new Font("monospaced", Font.PLAIN, 30);
        chatArea.setFont(font);
        chatArea.setBackground(new Color(220, 250, 220));
        chatArea.setEditable(false);  // запрет ввода напрямую в основное окно чата

        JScrollPane jsp = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

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
            Socket socket = new Socket("localhost", 8189);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if(strFromServer.startsWith("/authok")) {
                            isAuthorized = true;
                            chatArea.append(strFromServer + "\n");
                            break;
                        }
                        chatArea.append(strFromServer + "\n");
                    }

                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }
                        chatArea.append(strFromServer);
                        chatArea.append("\n");
                    }
                } catch (Exception ignored) { }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            System.out.println("Подключение к серверу не удалось.");
        }
        setVisible(true);
    }

    public void onAuthClick() {
        try {
            out.writeUTF("/auth " + message.getText());
            message.setText("");
            } catch (Exception ignored) { }
    }

    public void send() {
        if(message.getText() != null && !message.getText().trim().isEmpty() && isAuthorized) {
            try {
                out.writeUTF(message.getText()); // отправка сообщений на сервер
                message.setText("");
            } catch (IOException ignored) { }
        }
        if(!isAuthorized) {
            onAuthClick();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client3::new);
    }
}