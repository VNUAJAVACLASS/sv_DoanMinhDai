package service;

import java.util.List;

import dao.NewsDAO;
import model.News;

public class NewsService {
	private NewsDAO newsDAO;
	
	public NewsService(){
		newsDAO = new NewsDAO();
	}
	
	public List<News> getAllNews() {
		List<News> listNews = newsDAO.findAllNews();
		if(listNews.isEmpty())
			return null;
		return listNews;
	}
	
	public News getNewsById(int id) {
		News news = newsDAO.findNewsById(id);
		if(news.equals(null))
			return null;
		return news;
	}
	
	public boolean addNews(News news) {
		if(newsDAO.addNews(news))
			return true;
		return false;
	}
	
	public boolean deleteNews(int id) {
		if(newsDAO.deleteNews(id))
			return true;
		return false;
	}
	
	public boolean updateNews(News news) {
		if(newsDAO.updateNews(news))
			return true;
		return false;
	}
}
