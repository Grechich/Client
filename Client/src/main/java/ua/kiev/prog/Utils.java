package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Utils {
    private static final String URL = "http://localhost";
    private static final int PORT = 8080;
    static Scanner scanner = new Scanner(System.in);

    public static String getURL() {
        return URL + ":" + PORT;
    }

    public static int reg(User user) throws IOException {
        System.out.println("Enter your login: ");
        user.setLogin( scanner.nextLine());
        System.out.println("Enter your password: ");
        user.setPass( scanner.nextLine());
        String url = Utils.getURL() + "/reg";
        java.net.URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            String json = user.toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        }
    }

    public static boolean log (User user) throws IOException {
        System.out.println("Enter your login: ");
        user.setLogin( scanner.nextLine());
        System.out.println("Enter your password: ");
        user.setPass( scanner.nextLine());
        String url = Utils.getURL() + "/login?login=" + user.getLogin() + "&pass=" + user.getPass();
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            String json = user.toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            int rez = conn.getResponseCode();
            if (rez == 200){
                System.out.println("Login accepted");
                user.setOnline( true);
                return false;
            }else {
                System.out.println("Try again");
            }
            return true;
        }
    }

}
