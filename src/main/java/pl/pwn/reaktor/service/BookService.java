package pl.pwn.reaktor.service;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pl.pwn.reaktor.model.Book;
import pl.pwn.reaktor.model.BookFilter;
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
	
	public Book findById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Book book = (Book) session.get(Book.class, id);
		transaction.commit();
		session.close();
		return book;
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
	
	public void delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();

		Query query = session.createQuery("DELETE FROM Book WHERE id=:id");
		query.setInteger("id", id);
		query.executeUpdate();
		trx.commit();
		session.close();
	}
	
	public List<Book> bookFilter(BookFilter filter) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Book.class);

		if (Objects.nonNull(filter.getAuthor()) && !filter.getAuthor().isEmpty()) {
			criteria.add(Restrictions.eq("author", filter.getAuthor()));
		}
		if (Objects.nonNull(filter.getTitle()) && !filter.getTitle().isEmpty()) {
			criteria.add(Restrictions.eq("title", filter.getTitle()));
		}
		if (Objects.nonNull(filter.getType()) && !filter.getType().isEmpty()) {
			criteria.add(Restrictions.eq("type", filter.getType()));
		}
		if (Objects.nonNull(filter.getRate()) && !filter.getRate().isEmpty()) {
			criteria.add(Restrictions.eq("rate", filter.getRate()));
		}
		if (Objects.nonNull(filter.getStatus()) && !filter.getStatus().isEmpty()) {
			criteria.add(Restrictions.eq("status", filter.getStatus()));
		}
		List<Book> books = criteria.list();

		session.close();
		return books;
	}
	
	public void update(Book selectedItem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.saveOrUpdate(selectedItem);
		transaction.commit();
		session.close();
	}

}
