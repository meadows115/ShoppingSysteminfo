package domain;

import java.math.BigDecimal;

/**
 *
 * @author meani898
 */
public class SaleItem {
	private int quantityPurchased;
	private BigDecimal salePrice;
	
	//showing the relationship with sale and product classes
	private Product product;
	private Sale sale;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	
	
	public int getQuantityPurchased() {
		return quantityPurchased;
	}

	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	//method that returns the total cost of a sale
	public BigDecimal getItemTotal(){
		BigDecimal result=salePrice.multiply(new BigDecimal(quantityPurchased));
		return result;
	}	
}
