package ru.GeekBrains.Rostislav.Java2Group3.lesson7.ServerSide;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}