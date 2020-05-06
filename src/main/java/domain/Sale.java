package domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author meani898
 */
public class Sale {
	private Integer saleID;
	private LocalDate date;
	private String status;
	
	//each sale is associated with one and only one customer
	private Customer customer;
	
	//private ArrayList <SaleItem> items= new ArrayList<>();
    private Collection <SaleItem> items= new ArrayList<>();

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	 
	public Collection<SaleItem> getItems() {
		return items;
	}

	public void setItems(Collection<SaleItem> items) {
		this.items = items;
	}
	 
	public Integer getSaleID() {
		return saleID;
	}

	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//sum all the item totals for all of the items and get the result
	public BigDecimal getTotal(){
		BigDecimal grandTotal=BigDecimal.ZERO;
	
		//add each sale to the list
		for(SaleItem item :items) {
			grandTotal.add(item.getItemTotal());
		}
		return grandTotal;
	}
	
	//take the SaleItem and add it to the items collection
	public void addItem(SaleItem saleItem){
		items.add(saleItem);
	}

	@Override
	public String toString() {
		return "Sale{" + "saleID=" + saleID + ", date=" + date + ", status=" + status + ", customer=" + customer + ", items=" + items + '}';
	}
}


