package in.EliteShoppy.dao;

import java.util.List;


import in.EliteShoppy.model.Supplier;

public interface SupDAO {
	public boolean addSuppiler(Supplier s);
	public boolean updateSup(Supplier s);
	List<Supplier>getAllSupplier(); 
		
	public Supplier getSupplierId(String id);
	public boolean deleteSup(String id);

	

}
