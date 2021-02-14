package ru.GeekBrains.Rostislav.Java2Group3.lesson4;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {

            private String chat = "";

       public ChatWindow() {
            setTitle("Test chat");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBounds(300, 100, 500, 400);

            JPanel talk = new JPanel();
            JPanel myMessage = new JPanel();
            myMessage.setPreferredSize(new Dimension(ChatWindow.WIDTH, 50));

            JTextArea jta = new JTextArea(10, 10);
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

            JTextField message = new JTextField();
            message.setFont(font);
            myMessage.add(message, BorderLayout.CENTER);
            add(myMessage, BorderLayout.SOUTH);

            send.addActionListener(e -> {
                chat = jta.getText();
                jta.setText(chat + message.getText() + "\n"); // новое сообщение добавляется к старым (не удаляя их)
                message.setText("");
                });

            message.addActionListener(e -> {
                chat = jta.getText();
                jta.setText(chat + message.getText() + "\n");
                message.setText("");
            });
            setVisible(true);
        }
    }
