package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Giả định tài khoản đăng nhập
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123";

    // doGet được gọi khi vào URL /login hoặc forward từ nơi khác
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Kiểm tra cookie "remember me" có tồn tại từ lần đăng nhập trước không
        Cookie[] cookies = req.getCookies();
        String rememberedUser = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("rememberedUser")) {
                    rememberedUser = c.getValue();
                    break;
                }
            }
        }

        // Ghi giá trị cookie ra request scope để sử dụng trên login.jsp
        req.setAttribute("rememberedUser", rememberedUser);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    // doPost được gọi khi submit form đăng nhập (method=POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember"); // "on" nếu có check

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Đăng nhập thành công
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // Ghi nhớ đăng nhập bằng cookie
            if ("on".equals(remember)) {
                Cookie cookie = new Cookie("rememberedUser", username);
                cookie.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
                cookie.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
                resp.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("rememberedUser", "");
                cookie.setMaxAge(0); // xóa cookie
                cookie.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
                resp.addCookie(cookie);
            }

            resp.sendRedirect(req.getContextPath() + "/adminHome");

        } else {
            // Sai username hoặc password -> forward lại login.jsp kèm thông báo lỗi
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
