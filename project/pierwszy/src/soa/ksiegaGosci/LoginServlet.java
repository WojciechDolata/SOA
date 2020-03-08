package soa.ksiegaGosci;

import soa.ResponseFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    static List<DaneOsobowe> users = new DaneOsobowe("", "", "", "").getMockData();
    static private List<Comment> comments = new LinkedList<>();
    static private Map<String, DaneOsobowe> loggedUsers = new HashMap<>();
    private ResponseFormatter responseFormatter = new ResponseFormatter();

    public static List<Comment> getComments() {
        return comments;
    }

    private DaneOsobowe findUser(String login) {
        DaneOsobowe currentUser = null;
        for (var u : users) {
            if (u.login.equals(login)) {
                currentUser = u;
            }
        }
        return currentUser;
    }

    private void addUser(String sid, DaneOsobowe user) {
        if(loggedUsers.containsValue(user)){
            logoutUser(sid);
            throw new RuntimeException("Juz jestes zalogowany");
        } else {
            loggedUsers.put(sid, user);
        }
    }

    private void logoutUser(String sid) {
        loggedUsers.remove(sid);
    }

    private DaneOsobowe userForSID(String sid) {
        return loggedUsers.get(sid);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var currentUser = findUser(request.getParameter("login"));
        if (currentUser != null && request.getParameter("password").equals(currentUser.password)) {
            request.setAttribute("imie", currentUser.imie);
            request.setAttribute("nazwisko", currentUser.nazwisko);
            String sid = request.getSession().getId();
            try {
                addUser(sid, currentUser);
            } catch (RuntimeException ex) {
                responseFormatter.setResponse(ex.getMessage(), response);
            }
            response.addCookie(new Cookie("sid", sid));
            request.getRequestDispatcher("/ksiega.jsp").forward(request, response);
        } else {
            responseFormatter.setResponse("Złe dane logowania. Brak podanego użytkownika.", response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameterMap().containsKey("logout")) {
            for (Cookie a : request.getCookies()) {
                if (a.getName().equals("sid")) {
                    logoutUser(a.getValue());
                    a.setMaxAge(0);
                }
            }
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            String text = request.getParameter("comment");
            String sid = "";
            for (Cookie a : request.getCookies()) {
                if (a.getName().equals("sid")) {
                    sid = a.getValue();
                }
            }
            if (userForSID(sid) == null) {
                responseFormatter.setResponse("Błędne dane logowania", response);
            }
            var currentUser = userForSID(sid);
            if (text != null)
                comments.add(new Comment(text, currentUser.login, currentUser.imie, currentUser.nazwisko));
            request.setAttribute("imie", currentUser.imie);
            request.setAttribute("nazwisko", currentUser.nazwisko);
            request.getRequestDispatcher("/ksiega.jsp").forward(request, response);
        }
    }
}
