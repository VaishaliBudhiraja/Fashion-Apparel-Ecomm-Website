package in.EliteShoppy.dao;
import org.hibernate.HibernateException;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.EliteShoppy.model.Customer;
import in.EliteShoppy.model.CustomerOrder;

@Transactional
@Repository("customerOrderDAO")
public class CustomerOrderDAOImpl implements CustomerOrderDAO{

	private final Logger logger = LoggerFactory.getLogger(CustomerOrderDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	public boolean addCustomerOrder(CustomerOrder customerOrder) {
		logger.info("Starting addCustomerOrder method of customerOrderDao");
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.getCurrentSession();

			Customer customer = customerOrder.getCustomer();
			customerOrder.setBillingAddress(customer.getBillingAddress());
			customerOrder.setShippingAddress(customer.getShippingAddress());
			customerOrder.setOrderStatus("Placed");
			session.saveOrUpdate(customerOrder);
			session.saveOrUpdate(customer);
			session.saveOrUpdate(customer.getBillingAddress());
			session.saveOrUpdate(customer.getShippingAddress());

			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	public List<CustomerOrder> getAllOrders() {
		logger.info("Starting getAllOrders method of customerOrderDao");
		try {
                                            //HQL
			return sessionFactory.getCurrentSession().createQuery("FROM CustomerOrder").list();

		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	}

	public CustomerOrder getCustomerOrderById(int id) {
		
		logger.info("Starting getCustomerOrderById method of customerOrderDao");
		return sessionFactory.getCurrentSession().get(CustomerOrder.class, id);
	}

	
	public int changeOrderStatus(int id, String status) {
		
		logger.info("Starting changeOrderStatus method of customerOrderDao");
		try {

			Query query = sessionFactory.getCurrentSession()
					.createQuery("UPDATE CustomerOrder SET orderStatus = '" + status + "' where id = " + id);
			return query.executeUpdate();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Exception occured" + e);
			throw e;
		}
	
}
}


