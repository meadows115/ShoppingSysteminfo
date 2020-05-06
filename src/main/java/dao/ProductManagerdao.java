package dao;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author meani898
 */
public class ProductManagerdao implements Interfacedao {

	private String uri = DbConnection.getDefaultConnectionUri();
	//http://localhost:8082/login.jsp?jsessionid=b81f2bb45bab79cba1897774c5109647

	//default no arguement constructor
	public ProductManagerdao() {
	}

	//constructor to initialise the url
	public ProductManagerdao(String newUri) {
		this.uri = newUri;
	}

	@Override
	public void deleteProduct(Product product) {

		String sql = "delete from Product where productID= ?";
		try (
				  // get a connection to the database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

			stmt.setString(1, product.getProductID());

			//execute delete statement
			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Collection<Product> filterCategories(String categoryChosen) {
		//same method as search, but with a while loop!

		String sql = "select * from Product where category=?";

		try (
				  // get connection to database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

			stmt.setString(1, categoryChosen);
			ResultSet rs = stmt.executeQuery();
			Collection<Product> product= new ArrayList<>();
			
			while (rs.next()) { //same as getProduct code above
				// get the data out of the query
				String productID = rs.getString("ProductID");
				String name = rs.getString("Name");
				String description = rs.getString("Description");
				String category = rs.getString("Category");
				BigDecimal listPrice = rs.getBigDecimal("ListPrice");
				Integer quantityInStock = rs.getInt("QuantityInStock");

				// use the data to create a product object
				Product p = new Product();
				p.setProductID(productID);
				p.setName(name);
				p.setDescription(description);
				p.setCategory(category);
				p.setListPrice(listPrice);
				p.setQuantityInStock(quantityInStock);

				product.add(p);
			}
			return product;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Collection<String> getCategories() {

		String sql = "select distinct category from Product order by category";

		try (
				  // get a connection to the database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// execute the query
			ResultSet rs = stmt.executeQuery();

			// Using a List to preserve the order in which the data was returned from the query.
			Collection<String> categories = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String category = rs.getString("Category");

				// and put it in the collection
				categories.add(category);
			}

			return categories;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Collection<Product> getProducts() {

		String sql = "select * from Product order by ProductID";

		try (
				  // get a connection to the database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// execute the query
			ResultSet rs = stmt.executeQuery();

			// Using a List to preserve the order in which the data was returned from the query.
			Collection<Product> product = new ArrayList<>();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				String productID = rs.getString("productID");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				BigDecimal listPrice = rs.getBigDecimal("listPrice");
				Integer quantityInStock = rs.getInt("quantityInStock");

				// use the data to create a product object
				Product p = new Product();
				p.setProductID(productID);
				p.setName(name);
				p.setDescription(description);
				p.setCategory(category);
				p.setListPrice(listPrice);
				p.setQuantityInStock(quantityInStock);

				// and put it in the collection
				product.add(p);
			}

			return product;

		} catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public void saveProduct(Product product) {

		String sql = "insert into Product (productID, Name, Description, Category, listPrice, quantityInStock) values (?,?,?,?,?,?)";

		try (
				  // get connection to database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			// copy the data from the student domain object into the SQL parameters
			stmt.setString(1, product.getProductID());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setString(4, product.getCategory());
			stmt.setBigDecimal(5, product.getListPrice());
			stmt.setInt(6, product.getQuantityInStock());

			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Product searchProduct(String searchProdID) {

		String sql = "select * from Product where productID=?";

		try (
				  // get connection to database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

			stmt.setString(1, searchProdID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) { //same as getProduct code above
				// get the data out of the query
				String productID = rs.getString("productID");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String category = rs.getString("category");
				BigDecimal listPrice = rs.getBigDecimal("listPrice");
				Integer quantityInStock = rs.getInt("quantityInStock");
				


				// use the data to create a product object
		    	Product p = new Product();
				p.setProductID(productID);
				p.setName(name);
				p.setDescription(description);
				p.setCategory(category);
				p.setListPrice(listPrice);
				p.setQuantityInStock(quantityInStock);

				return p;
				
			} else {
				return null;//product not null
			}

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}
}










