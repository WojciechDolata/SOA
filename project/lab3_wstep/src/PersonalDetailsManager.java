import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "PersonalDetailsManager")
@ApplicationScoped
public class PersonalDetailsManager {

    private List<PersonalDetails> responses = new LinkedList<>();
    private Integer advertisementCount = 0;

    public String add(PersonalDetails personalDetails) {
        responses.add(personalDetails);
        System.out.println(responses);
        return "wynik";
    }

    public String increment() {
        advertisementCount++;
        System.out.println("Add has been clicked: " + advertisementCount.toString() + " times.");
        return "";
    }

    public PersonalDetails getLast() {
        return responses.get(responses.size() - 1);
    }

    public PersonalDetails getByIndex(Integer id) {
        return responses.get(id);
    }

}
