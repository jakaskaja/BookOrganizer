package pl.pwn.reaktor.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.pwn.reaktor.model.User;
import pl.pwn.reaktor.util.HibernateUtil;



public class LoginService {
	public boolean login(String mail, String password) {
		Session session = HibernateUtil
				.getSessionFactory()
				.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("FROM User WHERE mail=:mail and password=:password");
		query.setString("mail", mail);
		query.setString("password", password);
		List<User> list = query.list();
		transaction.commit();
		session.close();
		
		if (list.isEmpty()) {
			return false;
		}
		User user = list.get(0);
		System.out.println("Zalogowano użytkownika: "+user.getName()+" "+user.getLastName());
		return true;
	}
}
