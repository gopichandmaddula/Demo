package pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM_TABLE")
@NamedQueries ({
	@NamedQuery(name="findItemsOfCustomer", 
						query="SELECT i FROM Item i INNER JOIN i.order o INNER JOIN o.customer c "
								+ "WHERE c.customerId = :customerId")
})
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;

	private Integer price;
	private String description;
	private Integer quantity;

	public Item() {
	}

	public Item(Integer itemId, Integer price, String description, Integer quantity) {
		super();
		this.itemId = itemId;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

	@ManyToOne
	@JoinColumn(name = "orderId")
	private CustomerOrder order;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", price=" + price + ", description=" + description + ", quantity=" + quantity
				+ ", order=" + order.getOrderId() + "]";
	}
}
