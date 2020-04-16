import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "PersonalDetails")
@ViewScoped
public class PersonalDetails {
    private String name = "";
    private String email;
    private Integer age;
    private String sex;
    private String education;
    private Integer height;

    private String breastCircumference;
    private String cupSize;
    private String waist;
    private String hips;

    private String belt;
    private String chest;

    private String errorMessage = "";

    private boolean show = false;

    private String cash;
    private String howOften;
    private List<String> colors = new LinkedList<>();
    private List<String> types = new LinkedList<>();

    public boolean validate(){
        try {
            if (getSex().equals("male")) {
                if (getHeight() < 165 || getHeight() > 200) {
                    setShow(false);
                    setErrorMessage("Wrong height");
                    return false;
                }
            } else {
                if (getHeight() < 150 || getHeight() > 185) {
                    setShow(false);
                    setErrorMessage("Wrong height");
                    return false;
                }
            }

            if (getAge() < 10 || getAge() > 100) {
                setShow(false);
                setErrorMessage("Wrong age");
                return false;
            }

            boolean emailOk = getEmail().matches("^(.+)@(.+)$");
            setShow(emailOk);
            if (!emailOk) setErrorMessage("Wrong email");
            return emailOk;
        } catch (NullPointerException ex) {
            setErrorMessage("Incomplete data");
            setShow(false);
            return false;
        }
    }

    public PersonalDetails() {

    }

    public PersonalDetails(String name, String email, Integer age, String sex, String education, Integer height, String breastCircumference, String cupSize, String waist, String hips, String belt, String chest) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.education = education;
        this.height = height;
        this.breastCircumference = breastCircumference;
        this.cupSize = cupSize;
        this.waist = waist;
        this.hips = hips;
        this.belt = belt;
        this.chest = chest;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getBreastCircumference() {
        return breastCircumference;
    }

    public void setBreastCircumference(String breastCircumference) {
        this.breastCircumference = breastCircumference;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", education='" + education + '\'' +
                ", height=" + height +
                ", breastCircumference='" + breastCircumference + '\'' +
                ", cupSize='" + cupSize + '\'' +
                ", waist='" + waist + '\'' +
                ", hips='" + hips + '\'' +
                ", belt='" + belt + '\'' +
                ", chest='" + chest + '\'' +
                ", show=" + show +
                ", cash='" + cash + '\'' +
                ", howOften='" + howOften + '\'' +
                ", colors='" + colors + '\'' +
                ", types='" + types + '\'' +
                '}';
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
