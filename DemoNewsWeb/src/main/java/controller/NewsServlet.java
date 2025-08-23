package controller;

import model.News;
import service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.*;

public class NewsServlet extends HttpServlet {
	private NewsService newsService;

	public NewsServlet() {
		newsService = new NewsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String idStr = req.getParameter("id");

		if (action == null)
			action = "list";
		switch (action) {
		case "create":
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "edit":
			int idEdit = Integer.parseInt(idStr);
			News editNews = newsService.getNewsById(idEdit);
			req.setAttribute("news", editNews);
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "delete":
			int idDelete = Integer.parseInt(idStr);
			newsService.deleteNews(idDelete);
			resp.sendRedirect("news");
			break;
		case "detail":
			int idDetail = Integer.parseInt(idStr);
			News detailNews = newsService.getNewsById(idDetail);
			req.setAttribute("news", detailNews);
			req.getRequestDispatcher("detail.jsp").forward(req, resp);
			break;
		default:
			List<News> newsList = newsService.getAllNews();
			req.setAttribute("newsList", newsList);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String idStr = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
        // Validate đơn giản
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            req.setAttribute("error", "Title và Content không được để trống.");
            // Giữ lại dữ liệu người dùng đã nhập
            News n = new News();
            n.setTitle(title);
            n.setContent(content);
            req.setAttribute("news", n);
            req.getRequestDispatcher("form.jsp").forward(req, resp);
            return;
        }

        // Tạo mới
        if (idStr == null || idStr.isEmpty()) {
            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            // addNews nên KHÔNG nhận id và để DB tự tăng
            boolean ok = newsService.addNews(news);
            // Có thể lấy id sinh ra nếu cần: int newId = newsService.addNewsAndReturnId(news);
            // PRG pattern
            resp.sendRedirect("news");
            return;
        }

        // Cập nhật
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID không hợp lệ.");
            req.getRequestDispatcher("form.jsp").forward(req, resp);
            return;
        }

        News existing = newsService.getNewsById(id);
        if (existing == null) {
            req.setAttribute("error", "Bản ghi không tồn tại.");
            req.getRequestDispatcher("form.jsp").forward(req, resp);
            return;
        }

        existing.setTitle(title);
        existing.setContent(content);
        newsService.updateNews(existing);

        resp.sendRedirect("news");
	}
}