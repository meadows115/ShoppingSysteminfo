/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author meani898
 */
public interface Interfacedao {

    /**
     * method for removing a product, removes a product from the Array list
     *
     * @param product
     */
    void deleteProduct(Product product);

    /**
     * method that will filter the product categories
     * @param categoryChosen
     * @return
     */
    Collection<Product> filterCategories(String categoryChosen);

    /**
     * method that will return all of the categories
     *
     * @return listCategories all of the categories
     */
    Collection<String> getCategories();

    /**
     * method for getting all of the products, will return the entire ArrayList
     *
     * @return productsAll
     */
    Collection getProducts();

    /**
     * method for saving a product, add it to the ArrayList part of this method
     * is used in the getCategories method as well.
     *
     * @param product
     */
    void saveProduct(Product product);

    /**
     * method for searching for a product.
     * @param searchProdID
     * @return null if the product is not found
     */
    //changed to Collection to get rid of error
    Product searchProduct(String searchProdID);
    
}
