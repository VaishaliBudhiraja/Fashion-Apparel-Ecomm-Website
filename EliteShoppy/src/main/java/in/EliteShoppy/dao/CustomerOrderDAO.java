package in.EliteShoppy.dao;

import java.util.List;

import in.EliteShoppy.model.CustomerOrder;

public interface CustomerOrderDAO {
	public boolean addCustomerOrder(CustomerOrder customerOrder);

	public List<CustomerOrder> getAllOrders();

	public CustomerOrder getCustomerOrderById(int id);

	public int changeOrderStatus(int id, String status);

}
