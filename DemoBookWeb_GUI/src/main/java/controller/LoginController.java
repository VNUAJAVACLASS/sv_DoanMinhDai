package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    private static List<User> userlist = new ArrayList<>();

    @Override
    public void init() {
        userlist.add(new User("daidoan", "123456"));
        userlist.add(new User("haketu", "654321"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Nếu đã login thì đi thẳng đến trang quản lý sách
            resp.sendRedirect(req.getContextPath() + "/books");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (isValid(username, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", username);

            // Sau khi login thành công → đi đến danh sách sách
            resp.sendRedirect(req.getContextPath() + "/books");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error="
                    + URLEncoder.encode("Sai username hoặc password", StandardCharsets.UTF_8));
        }
    }

    private boolean isValid(String u, String p) {
        for (User user : userlist) {
            if (user.getUsername().equals(u) && user.getPassword().equals(p)) {
                return true;
            }
        }
        return false;
    }
}
