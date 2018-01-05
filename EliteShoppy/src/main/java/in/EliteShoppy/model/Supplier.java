package in.EliteShoppy.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
@Component
@Entity
public class Supplier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6166096839414648773L;
	@Id
	@Size(min=2,max=16,message="size is b/w 4-16")
	private String supId;
	@OneToMany(mappedBy="supplier",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	Set<Product>product;
	@Column(unique=true)
	@NotEmpty(message="name can not be empty")
	private String sname;
	private String sdescp;
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSdescp() {
		return sdescp;
	}
	public void setSdescp(String sdescp) {
		this.sdescp = sdescp;
	}
	

}
