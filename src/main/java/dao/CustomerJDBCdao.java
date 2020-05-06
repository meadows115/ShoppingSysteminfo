/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author meani898
 */
public class CustomerJDBCdao implements CustomerDAO {

	private String uri = DbConnection.getDefaultConnectionUri();

	public CustomerJDBCdao() {
	}

	//constructor to initialise the url
	public CustomerJDBCdao(String newUri) {
		this.uri = newUri;
	}

	@Override
	public void save(Customer customer) {
		String sql = "insert into Customer (username, firstName, surname, password, emailAddress, shippingAddress) values (?,?,?,?,?,?)";

		try (
				  // get connection to database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {

			stmt.setString(1, customer.getUsername());
			stmt.setString(2, customer.getFirstName());
			stmt.setString(3, customer.getSurname());
			stmt.setString(4, customer.getPassword());
			stmt.setString(5, customer.getEmailAddress());
			stmt.setString(6, customer.getShippingAddress());

			stmt.executeUpdate();  // execute the statement

		} catch (SQLException ex) {  // we are forced to catch SQLException
			// don't let the SQLException leak from our DAO encapsulation
			throw new DAOException(ex.getMessage(), ex);
		}
	}

	@Override
	public Customer getCustomer(String username) {

		String sql = "select * from Customer where username=?";

		try (
				  // get a connection to the database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			stmt.setString(1, username);
			// execute the query
			ResultSet rs = stmt.executeQuery();

			// iterate through the query results
			while (rs.next()) {

				// get the data out of the query
				Integer customerID = rs.getInt("customerID");
				String Username = rs.getString("username");
				String firstName = rs.getString("firstName");
				String surname = rs.getString("surname");
				String password = rs.getString("password");
				String emailAddress = rs.getString("emailAddress");
				String shippingAddress = rs.getString("shippingAddress");

				// use the data to create a customer object
				Customer c = new Customer();
				c.setCustomerID(customerID);
				c.setUsername(Username);
				c.setFirstName(firstName);
				c.setSurname(surname);
				c.setPassword(password);
				c.setEmailAddress(emailAddress);
				c.setShippingAddress(shippingAddress);

				return c;
			}

			return null;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}

	}

	@Override
	public Boolean validateCredentials(String username, String password) {
		String sql = "select * from customer where username=? and password=?";

		try (
				  // get a connection to the database
				  Connection dbCon = DbConnection.getConnection(uri);
				  // create the statement
				  PreparedStatement stmt = dbCon.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			// execute the query
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return true;
			}
			return null;

		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage(), ex);
		}
	}
}
