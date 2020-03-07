package soa.doradca;

import soa.ResponseFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "WyborPiwa", value = "/WyborPiwa")
public class WyborPiwa extends HttpServlet {
    private EkspertPiwny ekspertPiwny = new EkspertPiwny();
    private ResponseFormatter responseFormatter = new ResponseFormatter();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate birth = LocalDate.now();
        try {
            birth = LocalDate.parse(request.getParameter("birth"));
        } catch (DateTimeParseException ex) {
            responseFormatter.setResponse("Wrong date format", response);
        }

        if (birth.plusYears(18).isAfter(LocalDate.now())) {
            responseFormatter.setResponse("Jestes niepelnoletni, przykro mi", response);
        } else {
            String kolor = request.getParameter("color");
            String piwo = ekspertPiwny.przydzielPiwo(kolor);
            request.setAttribute("piwo", piwo);
            request.getRequestDispatcher("/wynik.jsp").forward(request, response);
        }

    }
}
