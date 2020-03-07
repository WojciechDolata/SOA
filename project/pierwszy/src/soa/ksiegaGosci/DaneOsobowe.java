package soa.ksiegaGosci;

import java.util.LinkedList;
import java.util.List;

public class DaneOsobowe {
    String login;
    String password;
    String imie;
    String nazwisko;

    public DaneOsobowe(String login, String password, String imie, String nazwisko) {
        this.login = login;
        this.password = password;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public List<DaneOsobowe> getMockData() {
        List<DaneOsobowe> list = new LinkedList<>();
        list.add(new DaneOsobowe("jan", "kowal00", "Jan", "Kowalski"));
        list.add(new DaneOsobowe("ania20", "haslo", "Anna", "Kowalska"));
        list.add(new DaneOsobowe("panTadeusz", "Robak", "Tadeusz", "Kowalski"));
        return list;
    }
}
