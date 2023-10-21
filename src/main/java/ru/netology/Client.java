package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String IP = "localhost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (true) {
            try (Socket clientSocket = new Socket(IP, Server.LOCALHOST_PORT);
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                if (line.equals("")) {
                    System.out.println("Введите город или для завершения игры введите \"конец\"");
                } else if (line.equals("конец")) {
                    System.out.println("Игра завершена");
                    break;
                }
                line = scanner.nextLine();
                writer.println(line);
                String text = reader.readLine();

                if(text.equals("")){
                    System.out.println("Сервер ввел не корректные данные. Игра завершена");
                    break;
                }

                String lastCharacter = String.valueOf(text.charAt(text.length() - 1));
                System.out.println("Сервер ввел город " + text + ". Введите город: " + lastCharacter.toUpperCase());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}