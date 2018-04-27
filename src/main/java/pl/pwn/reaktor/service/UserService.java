package pl.pwn.reaktor.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.pwn.reaktor.model.Users;
import pl.pwn.reaktor.util.HibernateUtil;

public class UserService {
	public int save (Users user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		int id = (int) session.save(user);
		transaction.commit();
		session.close();
		return id;
	}
}
