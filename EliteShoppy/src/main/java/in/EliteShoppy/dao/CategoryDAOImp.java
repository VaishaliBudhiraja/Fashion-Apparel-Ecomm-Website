package in.EliteShoppy.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.EliteShoppy.model.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImp implements CategoryDAO {
@Autowired
SessionFactory sessionFactory;
	public boolean addCategory(Category c) {
		Session s=sessionFactory.getCurrentSession();
		c.setCatId(c.getCname());
	try {
		s.persist(c);
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}	
		
		return true;
	}

	public boolean update(Category c) {
		Session sf=sessionFactory.getCurrentSession();		
		try {
			sf.update(c);
		}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		
}
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategory() {
		
		Session ss=sessionFactory.getCurrentSession();
		Query qu=ss.createQuery("from Category");
		
		List<Category> listCategory=(List<Category>)qu.list();
		return listCategory;
	}

	
	

	

	public Category getCategoryId(String id) {
		Session sg=sessionFactory.getCurrentSession();
		return sg.get(Category.class,id);
	
		
		//Query q=sg.createQuery("from Category where catId=?");
	

		//	q.setString(0,id);
	
		//	return (Category)q.list().get(0);
		
		
	}
	public boolean deleteCat(String id){
		Session sg=sessionFactory.getCurrentSession();
		sg.delete(getCategoryId(id));
		return true;
		
	}
	
	
	
	

	
}
	
