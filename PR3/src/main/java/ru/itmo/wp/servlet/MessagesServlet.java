package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;


public class MessagesServlet extends HttpServlet {
    private class MessagePair {
        String user;
        String text;

        MessagePair(String us, String msg) {
            this.user = us;
            this.text = msg;
        }

    }

    private void printResult(HttpServletResponse response, Object toPrint) throws IOException {
        response.setContentType("application/json");
        String json = new Gson().toJson(toPrint);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    private ArrayList<MessagePair> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(true);
        switch (uri) {
            case "/message/auth":
                String username = request.getParameter("user");
                String currentName = (String) session.getAttribute("user");
                if (username != null) {
                    session.setAttribute("user", username);
                } else {
                    username = (currentName == null ? "" : currentName);
                }
                printResult(response, username);
                break;
            case "/message/findAll":
                printResult(response, messages);
                break;
            case "/message/add":
                messages.add(new MessagePair((String) session.getAttribute("user"), request.getParameter("text")));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
