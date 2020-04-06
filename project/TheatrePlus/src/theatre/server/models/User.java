package theatre.server.models;

import java.io.Serializable;

public class User implements Serializable {
    private static int lastId = 0;

    private Integer id;
    private String nick;
    private Integer passwordHash;
    private Integer money;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(Integer passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public User(String nick, String password, Integer money) {
        this.id = lastId;
        this.nick = nick;
        this.passwordHash = password.hashCode();
        this.money = money;
        lastId += 1;
    }
}
