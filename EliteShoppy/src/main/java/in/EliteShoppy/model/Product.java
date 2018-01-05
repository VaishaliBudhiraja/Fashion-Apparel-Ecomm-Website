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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7407873376694130123L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String catId;
	private String supId;
	 
	
@ManyToOne
	@JoinColumn(name="catId",insertable=false,nullable=false,updatable=false)
private Category category;
	@ManyToOne
	@JoinColumn(name="supId",insertable=false,nullable=false,updatable=false)
private	Supplier supplier;
@NotEmpty(message="Please Fill Details Correctly")
	@Column(unique=true)
	private String name;
	private String descp;
	private int qty;
	private Long price;
	@Transient
	MultipartFile image;
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public Long getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Long price) {
		this.price = price;
	}


	
	
	

}
