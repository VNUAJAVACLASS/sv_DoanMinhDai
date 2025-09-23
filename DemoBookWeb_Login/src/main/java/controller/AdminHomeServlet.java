package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminHomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    // Kiểm tra trạng thái đã đăng nhập chưa
	    HttpSession session = req.getSession(false);
	    String user = (session != null) ? (String) session.getAttribute("username") : null;

	    if (user == null) {
	        // chuyển hướng lại trang login nếu chưa đăng nhập
	        resp.sendRedirect(req.getContextPath() + "/login");
	        return;
	    }

	    req.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html;charset=UTF-8");

	}

}
