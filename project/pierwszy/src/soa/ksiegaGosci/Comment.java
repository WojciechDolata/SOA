package soa.ksiegaGosci;

import java.time.LocalDate;

public class Comment {
    String text;
    String authorLogin;
    String name;
    String surname;

    public Comment(String text, String authorLogin, String name, String surname) {
        this.text = text;
        this.authorLogin = authorLogin;
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return this.text + " (przez: " + this.name + " " + this.surname + ", " + LocalDate.now() + ")";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public void setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
    }
}
