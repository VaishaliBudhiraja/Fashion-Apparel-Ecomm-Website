package in.EliteShoppy.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.EliteShoppy.model.Author;
import in.EliteShoppy.model.Customer;
import in.EliteShoppy.model.User;

@Repository("userDAO")
@Transactional

public class UserDAOImpl implements UserDAO {
	@Autowired 
	SessionFactory sessionFactory;
	private final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	

	/* public class UserDAOImpl implements UserDAO {
	 * @Autowired
	SessionFactory sessionFactory;
	public boolean addUser(User u) {
		Session sf=	sessionFactory.getCurrentSession();
		u.setUserId(u.getName());
		Author auth=new Author();
		auth.setName(u.getUserId());
		auth.setRole("ROLE_USER");
		u.setActive(true);
		
		
		try {
			sf.persist(auth);
			sf.persist(u);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public List<User> getUser() {
		Session ss=sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query qu=ss.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> listUser=(List<User>)qu.list();
		return listUser;
}*/
	
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		
		logger.info("Starting getAllCustomer method of customerDao");
		try {
			return sessionFactory.getCurrentSession().createQuery("from Customer").list();
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
		}
	}

	public boolean save(Customer customer) {

		logger.info("Starting method save() in CustomerDaoImpl");

		try {
			Session session = sessionFactory.getCurrentSession();

			customer.getBillingAddress().setCustomer(customer);
			customer.getShippingAddress().setCustomer(customer);

			session.saveOrUpdate(customer);
			session.saveOrUpdate(customer.getBillingAddress());
			session.saveOrUpdate(customer.getShippingAddress());

			logger.info("Customer details inserted");

			Author auth = new Author();
			auth.setRole("ROLE_USER");
			auth.setUsername((customer.getUsername()));
			auth.setEmail(customer.getEmail());
			session.persist(auth);
			logger.info("User role assigned");

			User users = new User();
			users.setActive(true);
			users.setUserId(customer.getUsername());
			users.setUsername(customer.getUsername());
			users.setPassword(customer.getPassword());
			users.setCustomer(customer);
			users.setwEmail(customer.getEmail());
			session.saveOrUpdate(users);
			customer.setUsers(users);
			session.persist(customer);
			logger.info("User detail inserted");

			return true;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Customer customer) {
		
		logger.info("Starting update method of customerDao");
		try {
			Session session = sessionFactory.getCurrentSession();

//			customer.getBillingAddress().setCustomer(customer);
//			customer.getShippingAddress().setCustomer(customer);

			session.update(customer);
			session.update(customer.getBillingAddress());
			session.update(customer.getShippingAddress());
			
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
		}
	}

	
	/*public boolean delete(int id) {
		
		logger.info("Starting delete method of customerDao");
		return false;
	}*/

	
	public Customer getUserById(int id) {
		
		logger.info("Starting getCustomerById method of customerDao");
		try {
			return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
			
		}
	}

	
	public Customer getUserByUserName(String username) {
		
		logger.info("Starting getCustomerByUsername method of customerDao");
		try {
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery("from Customer where username= '" + username + "'");
			Customer customer = (Customer) query.uniqueResult();

			return customer;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
		}
	}

/*	public String getUserRole(String username)  {
		
		logger.info("Starting getUserRole method of customerDao");
		try {
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession()
					.createQuery("from Author where username= '" + username + "'");
			Author auth= (Author) query.uniqueResult();
			return auth.getUsername();

		} catch (HibernateException e) {
		
			e.printStackTrace();
			throw e;
		}
	}*/


	@SuppressWarnings({ "rawtypes", "rawtypes" })
	public Customer getUserByCustomerName(String name) {
		
		logger.info("Starting getCustomerByCustomerName method of customerDao");
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Customer where username= '" + name + "'");
			Customer customer = (Customer) query.uniqueResult();

			return customer;
		} catch (HibernateException e) {
		
			e.printStackTrace();
			throw e;
		}
	}

	public User getUsersById(int id) {
		
		logger.info("Starting getUsersById method of customerDao");
		try {
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery("FROM User where customerId=" + id);
			return (User) query.uniqueResult();
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
		}
	}

	public boolean getStatus(int id) {
		logger.info("Starting getStatus method of customerDao");
		User users = getUsersById(id);
		return users.isActive();
	}

	
	public int changeStatus(int id)  {
		
		logger.info("Starting changeStatus method of customerDao");
		try {
			User users = getUsersById(id);
			boolean isEnable = users.isActive();

			if (isEnable) {
				@SuppressWarnings("rawtypes")
				Query query = sessionFactory.getCurrentSession()
						.createQuery("UPDATE User SET enabled = " + false + " WHERE customerId = " + id);
				return query.executeUpdate();
			} else {
				@SuppressWarnings("rawtypes")
				Query query = sessionFactory.getCurrentSession()
						.createQuery("UPDATE User SET enabled = " + true + " WHERE customerId = " + id);
				return query.executeUpdate();
			}
		} catch (HibernateException e) {
			
			e.printStackTrace();
			throw e;
			
		}
	}

}


