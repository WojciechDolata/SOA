package soa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "FiveIntegers", value = "/FiveIntegers")
public class FiveIntegers extends HttpServlet {

    ResponseFormatter responseFormatter = new ResponseFormatter();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var iterator = request.getParameterNames();
        List<Integer> list = new LinkedList<>();
        while(iterator.hasMoreElements()) {
            Integer num;
            try {
                num = Integer.parseInt(request.getParameter(iterator.nextElement()));
                list.add(num);
            } catch (NumberFormatException ex) {
                responseFormatter.setResponse("wrong data", response);
            }
        }
        list.sort(Integer::compareTo);
        responseFormatter.setResponse(list.toString(), response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int first = Integer.parseInt(request.getParameter("first"));
        int second = Integer.parseInt(request.getParameter("second"));
        int third = Integer.parseInt(request.getParameter("third"));
        int fourth = Integer.parseInt(request.getParameter("fourth"));
        int fifth = Integer.parseInt(request.getParameter("fifth"));
        Float sum = new Float(first + second + third + fourth + fifth);
        sum = sum/5;
        responseFormatter.setResponse(sum.toString(), response);
    }
}