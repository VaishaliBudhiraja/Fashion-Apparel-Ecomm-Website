package in.EliteShoppy.dao;

import java.io.IOException;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.EliteShoppy.model.Cart;

@Repository("cartDAO")
@Transactional

public class CartDAOImpl implements CartDAO{
	
	@Autowired(required=true)
	SessionFactory sessionFactory;
	private final Logger logger = LoggerFactory.getLogger(CartDAOImpl.class);

	

	public List<Cart> getCartList(String username) {
		logger.info("Starting getCartList method in cartDao");
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("from Cart where username = '" + username + "' and status='NEW'");
			return query.list();
		} catch (HibernateException e) {
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	public boolean save(Cart cart) {
		// TODO Auto-generated method stub
		logger.info("Starting save method of cartdaoimpl");
		System.out.println("cartdao save method is invoked ");
		try {
			sessionFactory.getCurrentSession().save(cart);
			return true;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		logger.info("Starting delete method of cartdaoimpl");
		try {
			sessionFactory.getCurrentSession().delete(getCartById(id));
			return true;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public boolean update(Cart cart) {
		// TODO Auto-generated method stub
		logger.info("Starting update method in cartDao");
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public long getTotalAmount(String username) {
		// TODO Auto-generated method stub
		logger.info("Starting getTotalAmount method in cartDao");
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"SELECT SUM(price*quantity) FROM Cart where username='" + username + "' and status = 'NEW'");
			if (query.uniqueResult() == null) {
				return 0;
			} else {
				long result =  (Long) query.uniqueResult();
				return result;
			}

		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public Cart getCartByUsername(String username, String productname) {
		// TODO Auto-generated method stub
		try {
			logger.info("Starting getcartbyusername method of cartdaoimpl");

			Query query = sessionFactory.getCurrentSession().createQuery("from Cart WHERE username='" + username
					+ "' and product_name='" + productname + "' and status = 'NEW'");
			logger.info("Ending getcartbyusername method of cartdaoimpl");
			return (Cart) query.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	public int getQuantity(String username, String productname) {
		// TODO Auto-generated method stub
		try {
			logger.info("Starting getquantity method of cartdaoimpl");

			Query query = sessionFactory.getCurrentSession().createQuery("SELECT quantity from Cart WHERE username='"
					+ username + "' and product_name='" + productname + "' and status = 'NEW'");
			logger.info("Ending getquantity method of cartdaoimpl");
			return  (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public long getNumberOfProducts(String username) {
		// TODO Auto-generated method stub
		logger.info("Starting getNumberOfProducts method in cartDao");
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("SELECT SUM(quantity) FROM Cart where username='" + username + "' and status = 'NEW'");
			if (query.uniqueResult() == null) {
				return 0;
			} else {
				long result =  (Long) query.uniqueResult();
				return result;
			}
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public Cart getCartById(int id) {
		// TODO Auto-generated method stub
		logger.info("Starting getCartById method in cartDao");
		try {
			return sessionFactory.getCurrentSession().get(Cart.class, id);
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	
	public int clearCart(String username) {
		// TODO Auto-generated method stub
		logger.info("Starting clearCart method in cartDao");
		try {
			/*
			 * Query query = sessionFactory.getCurrentSession()
			 * .createQuery("UPDATE Cart SET status='OLD' where username = '" +
			 * username + "'");
			 */
			Query query = sessionFactory.getCurrentSession()
					.createQuery("DELETE from Cart where username = '" + username + "'");
			return query.executeUpdate();

		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	public Cart validate(int cartId) throws IOException {
		logger.info("Starting validate method in cartDao");
		Cart cart = getCartById(cartId);
		if (cart == null) {
			throw new IOException(cartId + "");
		}
		update(cart);
		return cart;
	}
}