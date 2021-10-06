package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.io.IOException;

public class User implements Serializable {
    private String login;
    private String pass;
    private boolean online;

    public User() {
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public int registration() throws IOException {
        return Utils.reg(this);
    }

    public boolean login() throws IOException {
        return Utils.log(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", online=" + online +
                '}';
    }
}
