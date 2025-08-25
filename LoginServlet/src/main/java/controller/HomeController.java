package controller;

import javax.servlet.http.*;
import java.io.IOException;

public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession s = req.getSession(false);
        String user = (s != null) ? (String) s.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.html?error=Please+login+first");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home.html?user=" + user);
        }
    }
}
