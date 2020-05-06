/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author meani898
 */
public class CollectiondaoTest {

	private Product prodOne;
	private Product prodTwo;
	private Product prodThree;
	private Product prodFour;

	//an instance of the dao class
	//private final Collectiondao dao = new Collectiondao();
	
	private final Interfacedao dao = new ProductManagerdao("jdbc:h2:mem:tests;INIT=runscript from 'src/main/resources/schema.sql'");

	public CollectiondaoTest() {
	}

	@Before
	public void setUp() {
		this.prodOne = new Product("1", "name1", "desc1", "cat1",
				  new BigDecimal("11.00"), new Integer("22"));
		this.prodTwo = new Product("2", "name2", "desc2", "cat2",
				  new BigDecimal("33.00"), new Integer("44"));
		this.prodThree = new Product("3", "name3", "desc3", "cat3",
				  new BigDecimal("55.00"), new Integer("66"));
		this.prodFour = new Product("4", "name4", "desc4", "cat2",
				  new BigDecimal("77.00"), new Integer("88"));
		
// save the products- add the products
		dao.saveProduct(prodOne);
		dao.saveProduct(prodTwo);
		dao.saveProduct(prodFour);
// Note: Intentionally not saving prodThree

	}

	@After
	public void tearDown() {
		dao.deleteProduct(prodOne);
		dao.deleteProduct(prodTwo);
		dao.deleteProduct(prodThree);
		dao.deleteProduct(prodFour);

	}

	//this test will test that prodThree is saved, and then will make sure it can access prodThree once it has been saved.
	@Test
	public void testSaveProduct() {
		//fail("Not implemented yet");
		// save the product using the DAO
		dao.saveProduct(prodThree);
// ensure that the data store includes the product
		assertTrue("Ensure that the product was saved",
				  dao.getProducts().contains(prodThree));
	}

	@Test
	public void testGetProducts() {
		Collection<Product> products = dao.getProducts();
// ensure the result includes the two saved products
		assertTrue("prodOne should exist", products.contains(prodOne));
		assertTrue("prodTwo should exist", products.contains(prodTwo));
		assertTrue("prodFour should exist", products.contains(prodFour));
// ensure the result ONLY includes the two saved products
		assertEquals("Only 3 products in result", 3, products.size());
	}

	@Test
	public void testDeleteProduct() {
		// sanity check to make sure prodOne does exist before we delete it
		assertTrue("Ensure that the product does exist",
				  dao.getProducts().contains(prodOne));
// delete the product via the DAO
		dao.deleteProduct(prodOne);
// ensure that the product no longer exists
		assertFalse("Ensure that the product does not exist",
				  dao.getProducts().contains(prodOne));
	}

	@Test
	public void testGetCategories() {
		//fail("Not implemented yet");
		Collection<String> categories = dao.getCategories();
		// ensure the result includes the two saved products
		assertTrue("category 1 should exist", categories.contains(prodOne.getCategory()));
		assertTrue("category 2 should exist", categories.contains(prodTwo.getCategory()));

		// ensure the result ONLY includes the two saved products
		assertEquals("Only 2 categories in result", 2, categories.size());
	}
	
	@Test
	public void testSearchProduct(){
		Product returnedProduct = dao.searchProduct("1");
		//ensure that the result is prodOne
		assertEquals("returnedProduct is prodOne", returnedProduct, prodOne);
		//ensure all of the details of prodOne that is returned is correct and prodOne
		assertEquals(prodOne.getProductID(),returnedProduct.getProductID());
		assertEquals(prodOne.getName(),returnedProduct.getName());
		assertEquals(prodOne.getDescription(), returnedProduct.getDescription());
		assertEquals(prodOne.getCategory(), returnedProduct.getCategory());
		assertEquals(prodOne.getListPrice(), returnedProduct.getListPrice());
		assertEquals(prodOne.getQuantityInStock(), returnedProduct.getQuantityInStock());
		
		//test that if a non existent product ID is entered the method returns null
		Product invalidID= dao.searchProduct ("20");
		assertNull("should not return a product",invalidID);
	}
	
	@Test
	public void testFilterCategories(){
		//Ensure that the filter method is actually filtering and not returning all products
		Collection <Product> productsFiltered= dao.filterCategories("cat2");
		assertEquals("prodTwo and prodFour should be the only thing returned",2, productsFiltered.size());
		assertTrue("prodTwo returned category", productsFiltered.contains(prodTwo));
		assertTrue("prodFour returned category", productsFiltered.contains(prodFour));		
	}
}

