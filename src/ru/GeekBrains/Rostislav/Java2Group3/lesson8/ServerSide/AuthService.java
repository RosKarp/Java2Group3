package ru.GeekBrains.Rostislav.Java2Group3.lesson8.ServerSide;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}