 package in.EliteShoppy.dao;


import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.EliteShoppy.model.Category;
import in.EliteShoppy.model.Product;
@Repository("productDAO")
@Transactional

public class ProdDAOImpl implements ProdDAO {
@Autowired
SessionFactory sessionFactory;

//----------this method is used to insert the product in database-------------	
	public boolean addProduct(Product p) {
		Session f=sessionFactory.getCurrentSession();
		//p.setProductId(p.getProductId());
		try {
			f.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

	//======================this method is used to get product details but that should be unique==================
	
	public Set<Product> getProduct() {
		
		Session ss=sessionFactory.getCurrentSession();
		Query qu=ss.createQuery("from Product");
		Set<Product> setProduct=(Set<Product>)qu.list();
		return setProduct;
	}


//-------------------this method is used to update the value in database--------------------------------

	public boolean updateProd(Product p) {
		try {
			Session s=sessionFactory.getCurrentSession();
			s.update(p);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//---------------------this method is used to display the product list on jsp page---------------

	
	public List<Product> getAllProduct() {
		
		
		Session ss=sessionFactory.getCurrentSession();
		Query qu=ss.createQuery("from Product");
		@SuppressWarnings("unchecked")
		List<Product> listProduct=(List<Product>)qu.list();
		return listProduct;
	}
	
	//-----------------this method is used to get the product that you have stored in database via name------------
	
	
	public Product getProductName(String name) {
		Session s=sessionFactory.getCurrentSession();
		Query q=s.createQuery("from Product where name=?");
		q.setString(0, name);
		return (Product) q.list().get(0);
		//return s.get(Product.class,name);
		
		
	}



 	//-----------------this method is used to delete product from data base as well as jsp page-----------
	public boolean deleteProduct(String name) {
		Session sg=sessionFactory.getCurrentSession();
		sg.delete(getProductName(name));
		return true;
	}



	
	


	//===============this method is used to get product by category==================
	
	
	public List<Product> getProductByCategory(String catId) {
	Session s=sessionFactory.getCurrentSession();
	Query query=s.createQuery("from Product where catId=?");
	query.setString(0, catId);
	List<Product> listCatPro=(List<Product>)query.getResultList();
	return listCatPro;
	}


//---------------This method is used to get product by Id
	@SuppressWarnings("deprecation")
	public Product getProductId(int id) {
		Session s=sessionFactory.getCurrentSession();
		Query q=s.createQuery("from Product where productId=?");
			q.setInteger(0, id);	
		
		return (Product) q.list().get(0);
		//return s.get(Product.class,name);
	}

}
