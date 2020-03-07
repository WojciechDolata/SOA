package soa.doradca;

public class EkspertPiwny {
    public String przydzielPiwo(String color) {
        switch (color.toUpperCase()){
            case "JASNE":
                return "Lech";
            case "CIEMNE":
                return "Guiness";
            case "CZERWONE":
                return "Ksiazece";
            case "BIAŁE":
                return "Żywiec";
            default:
                return "Perła";
        }
    }
}
