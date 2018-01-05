package in.EliteShoppy.dao;

import java.util.List;

import in.EliteShoppy.model.Category;

public interface CategoryDAO {
	//----------inserting table values in database------------
public boolean addCategory(Category c);


       /*-----------Update the category ----------*/
public boolean update(Category c);


List<Category>getAllCategory();
public Category getCategoryId(String id);
public boolean deleteCat(String id);

}
