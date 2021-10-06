package ua.kiev.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String login = "login";
            boolean b = true;
            do {
                System.out.println("For authorization enter: /auth");
                System.out.println("For registration enter: /reg");
                String menu = scanner.nextLine();

                if (menu.equals("/auth")) {
                    User user = new User();
                    b = user.login();
                    login = user.getLogin();
                }
                if (menu.equals("/reg")) {
                    User user = new User();
                    System.out.println(user.registration());
                }
            } while (b);

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();
            System.out.println("To see all users enter: /all");
            System.out.println("To see users online enter: /online");
            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(login, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
