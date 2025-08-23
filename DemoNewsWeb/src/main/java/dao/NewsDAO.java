package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.News;

public class NewsDAO {
	private Connection connection;

	public NewsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/news";
			String dbUser = "root";
			String dbPassword = "root";
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
	        System.out.println("✅ Kết nối DB thành công!");
	    } catch (ClassNotFoundException e) {
	        System.err.println("Không tìm thấy MySQL Driver");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.err.println("❌ Kết nối DB thất bại");
	        e.printStackTrace();
	    }
	}

	public List<News> findAllNews() {
		List<News> listNews = new ArrayList<>();
		String query = "SELECT * FROM news";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");

				News news = new News(id, title, content);

				listNews.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNews;
	}

	public boolean addNews(News news) {
		String query = "INSERT INTO news ( title, content) VALUES ( ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, news.getTitle());
			stmt.setString(2, news.getContent());

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateNews(News news) {
		String query = "UPDATE news SET title = ?, content = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, news.getTitle());
			stmt.setString(2, news.getContent());
	        stmt.setInt(3, news.getId());

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteNews(int id) {
		String query = "DELETE FROM news WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public News findNewsById(int id) {
	    String query = "SELECT * FROM news WHERE id = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new News(
	                    rs.getInt("id"),
	                    rs.getString("title"),
	                    rs.getString("content")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
