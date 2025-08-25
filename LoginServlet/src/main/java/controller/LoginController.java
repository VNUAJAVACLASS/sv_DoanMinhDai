package controller;

import javax.servlet.http.*;

import model.User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoginController extends HttpServlet {

	private static List<User> userlist = new ArrayList<>();

	@Override
	public void init() {
		userlist.add(new User("daidoan", "123456"));
		userlist.add(new User("haketu", "654321"));
	}


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");

        if (isValid(u,p)) {
            req.getSession().setAttribute("user", u);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.html?error=" +
            	    URLEncoder.encode("Sai username hoặc password", StandardCharsets.UTF_8));

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
