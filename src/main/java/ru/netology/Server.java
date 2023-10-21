package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final Integer LOCALHOST_PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(LOCALHOST_PORT)) {
            System.out.println("Сервер запущен и ожидает соединений на порту " + LOCALHOST_PORT);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String infoFromClient = in.readLine();
                    String lastCharacter = String.valueOf(infoFromClient.charAt(infoFromClient.length() - 1));
                    System.out.println("Клиент ввел город " + infoFromClient + ". Введите город на " + lastCharacter.toUpperCase());

                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    out.println(line);
                } catch (IOException e) {
                    System.err.println("Ошибка взаимодействия с клиентом: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании серверного сокета: " + e.getMessage());
        }
    }
}