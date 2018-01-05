package in.EliteShoppy.dao;



import java.util.List;

import in.EliteShoppy.model.Category;
import in.EliteShoppy.model.Product;

public interface ProdDAO {
	public boolean addProduct(Product p);
	public boolean updateProd(Product p);
	//this method displays the list of  products
	List<Product>getAllProduct();	
	//this method is used to delete the product by product id
	public boolean deleteProduct(String name);
	//this method is used to get category by id in product
	public Product getProductName(String name);
	// this method is used to get product by category
	List<Product> getProductByCategory(String catId);
	// this method is used to get product by Product Id
	public Product getProductId(int id);


}
