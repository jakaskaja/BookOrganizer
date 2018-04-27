package pl.pwn.reaktor.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.pwn.reaktor.model.Book;
import pl.pwn.reaktor.util.HibernateUtil;

public class BookService {
	public int addNewBook (Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		int id = (int) session.save(book);
		transaction.commit();
		session.close();
		return id;
	}
	
	public List<Book> getAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();

		Query query = session.createQuery("SELECT b FROM Book b");
		List<Book> books = query.list();
		trx.commit();
		session.close();
		return books;
	}
}
