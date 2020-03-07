package soa.ksiegaGosci;

import soa.ResponseFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    static List<DaneOsobowe> users = new DaneOsobowe("", "", "", "").getMockData();
    static private List<Comment> comments = new LinkedList<>();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var currentUser = findUser(request.getParameter("login"));
        if (currentUser != null && request.getParameter("password").equals(currentUser.password)) {
            request.setAttribute("imie", currentUser.imie);
            request.setAttribute("nazwisko", currentUser.nazwisko);
            response.addCookie(new Cookie("user", currentUser.login));
            request.getRequestDispatcher("/ksiega.jsp").forward(request, response);
        } else {
            responseFormatter.setResponse("Złe dane logowania. Brak podanego użytkownika.", response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("comment");
        String login = "";
        for (Cookie a : request.getCookies()) {
            if (a.getName().equals("user")) {
                login = a.getValue();
            }
        }
        var currentUser = findUser(login);
        if(text != null) comments.add(new Comment(text, currentUser.login, currentUser.imie, currentUser.nazwisko));
        request.setAttribute("imie", currentUser.imie);
        request.setAttribute("nazwisko", currentUser.nazwisko);
        request.getRequestDispatcher("/ksiega.jsp").forward(request, response);
    }
}
