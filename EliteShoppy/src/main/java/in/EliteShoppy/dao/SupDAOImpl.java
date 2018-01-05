package in.EliteShoppy.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import in.EliteShoppy.model.Supplier;

@Transactional
@Repository("supDAO")
public class SupDAOImpl implements SupDAO{
	
	Logger log=LoggerFactory.getLogger("SupDAOImpl.class");
	
	@Autowired
	SessionFactory sessionFactory;

	/*---This method is used to create the table in database and inserting the supplier details in database---*/
	
public boolean addSuppiler(Supplier s) {
	
	try {
		log.debug("Starting of Add Supplier Method");
		
		
		Session sf=sessionFactory.getCurrentSession();
		
	//	s.setSupId(s.getSupId());
		
		sf.persist(s);
		
		log.debug("Sucessfully Added Supplier");
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
		log.error("Error in Add Supplier METHOD");
		
		return false;
	}
	return true;
}
 
       /*---This Method is used to update the entries in database that have already exists---*/

public boolean updateSup(Supplier s) {
	
	
	try {
		log.debug("Intializing the updateSup method for database");
		
		Session sf=sessionFactory.getCurrentSession();
		
		sf.update(s);
		
		log.debug("Sucessfully Updated values in database");
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
		log.error("Error in updateSup method");
		
		return false;
	}
	return true;
}

         /*This List<Supplier> method is used to get all the supplier from database  */

public List<Supplier> getAllSupplier() {
	log.debug("Intializing the List Method generic to Supplier");
	
	Session ss=sessionFactory.getCurrentSession();
	
	log.debug("Creating query for Supplier table");
	
	@SuppressWarnings("rawtypes")
	Query qu=ss.createQuery("from Supplier");
	
	log.debug("Sucessfully implementing Query");
	
	@SuppressWarnings("unchecked")
	List<Supplier> listSup=(List<Supplier>)qu.list();
	
	return listSup;
}

         /*-- This method is used to get Supplier details from database on the basis of supplier Id --*/

public Supplier getSupplierId(String id) {
	log.debug("Intializing the getSupplierId Method");
	Session sg=sessionFactory.getCurrentSession();
	return sg.get(Supplier.class,id);

}

             /*-- This method is used to delete the entries from supplier table in database*/

public boolean deleteSup(String id) {
	
	log.debug("Intializing the delete method");
	
	Session s=sessionFactory.getCurrentSession();
	
	s.delete(getSupplierId(id));
	
	log.debug("Sucessfully deleting the supplier");
	
return true;
}


}


	
	


