/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author meani898
 */
public class Collectiondao implements Interfacedao {
        
	private static final Multimap<String, Product> categoryID = HashMultimap.create();
        private static final Map<String, Product> productIDmap=new HashMap<>();

	/**
	 * method for saving a product, add it to the ArrayList part of this method
	 * is used in the getCategories method as well.
	 *
	 * @param product
	 */
    @Override
	public void saveProduct(Product product) {

		//String category = product.getCategory();		

		//add the product being saved to the HashMap
		categoryID.put(product.getCategory(), product);
                productIDmap.put(product.getProductID(), product);
          
	}
	
	/**
	 * method for searching for a product.
	 * @param searchProdID
	 * @return null if the product is not found
	 */

	//changed to Collection to get rid of error
    @Override
	public Product searchProduct(String searchProdID) {

		//return the product
		return productIDmap.get(searchProdID);	
	}		   
			  
	/**
	 * method that will return all of the categories
	 *
	 * @return listCategories all of the categories
	 */
    @Override
	public Collection<String> getCategories() {
		return categoryID.keySet();

	}

	/**
	 * method for getting all of the products, will return the entire ArrayList
	 *
	 * @return productsAll
	 */
    @Override
	public Collection<Product> getProducts() {
		return productIDmap.values();

	}

	/**
	 * method for removing a product, removes a product from the Array list
	 *
	 * @param product
	 */
    @Override
    public void deleteProduct(Product product) {
//		productsAll.remove(product);
        productIDmap.remove(product.getProductID());
        categoryID.remove(product.getCategory(), product);
    }

	/**
	 * method that will filter the product categories
	 * @param categoryChosen
	 * @return 
	 */
    @Override
	public Collection<Product> filterCategories(String categoryChosen){
			  
			  return categoryID.get(categoryChosen);
			  
	}
}
