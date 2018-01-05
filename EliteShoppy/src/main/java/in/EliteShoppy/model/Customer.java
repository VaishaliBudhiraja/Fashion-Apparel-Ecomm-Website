package in.EliteShoppy.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
	@Component
	public class Customer implements Serializable {

		
		private static final long serialVersionUID = 3607187884251752653L;

		@Id
		@Column
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;

		@Email(message="Please provide a valid email address")
		@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
		@Column(unique = true)
		private String email;
 
		@NotEmpty(message="name can not be empty")
		@Column(unique = true)
		private String username;

		private String password;
		
		@NotEmpty(message="password can not be empty")
		@Column(unique = true)
		private String mobileno;

		@OneToOne
		@JoinColumn(name = "BILLINGADD_ID")
		private BillingAddress billingAddress;

		@OneToOne
		@JoinColumn(name = "SHIPPINGADD_ID")
		private ShippingAddress shippingAddress;

		@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
		private List<Cart> cartItems;

		@OneToOne
		private User users;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMobileno() {
			return mobileno;
		}

		public void setMobileno(String mobileno) {
			this.mobileno = mobileno;
		}

		public BillingAddress getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(BillingAddress billingAddress) {
			this.billingAddress = billingAddress;
		}

		public ShippingAddress getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(ShippingAddress shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public List<Cart> getCartItems() {
			return cartItems;
		}

		public void setCartItems(List<Cart> cartItems) {
			this.cartItems = cartItems;
		}

		public User getUsers() {
			return users;
		}

		public void setUsers(User users) {
			this.users = users;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

}

