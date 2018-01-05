package in.EliteShoppy.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import in.EliteShoppy.model.Author;
import in.EliteShoppy.model.BillingAddress;
import in.EliteShoppy.model.Cart;
import in.EliteShoppy.model.Category;
import in.EliteShoppy.model.Customer;
import in.EliteShoppy.model.CustomerOrder;
import in.EliteShoppy.model.Product;

import in.EliteShoppy.model.ShippingAddress;
import in.EliteShoppy.model.Supplier;
import in.EliteShoppy.model.User;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages ="in.EliteShoppy")
public class HibernateConfig {
	@Bean("dataSource")
	public DataSource geth2DataSource()
	{
	DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("h2");
		dataSource.setPassword("h2");
		return dataSource;
		
	}
	@Autowired
	@Bean("sessionFactory")
	public  SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionFactory=new LocalSessionFactoryBuilder(dataSource);
		
		sessionFactory.addAnnotatedClass(User.class);
		sessionFactory.addAnnotatedClass(Supplier.class);
		
		sessionFactory.addAnnotatedClass(Product.class);
		sessionFactory.addAnnotatedClass(Category.class);
		sessionFactory.addAnnotatedClass(Author.class);
		sessionFactory.addAnnotatedClass(BillingAddress.class);
		
		sessionFactory.addAnnotatedClass(ShippingAddress.class);
		sessionFactory.addAnnotatedClass(Customer.class);
		sessionFactory.addAnnotatedClass(CustomerOrder.class);

		sessionFactory.addAnnotatedClass(Cart.class);
		sessionFactory.addProperties(getHibernateProperties());
		
		return sessionFactory.buildSessionFactory();
		
	}
	public Properties getHibernateProperties()
	{

		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		return properties;
	}
	@Autowired
	@Bean
	public HibernateTransactionManager geTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}


		}
