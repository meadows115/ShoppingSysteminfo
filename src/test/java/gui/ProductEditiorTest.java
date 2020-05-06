package gui;

import dao.Interfacedao;
import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author meani898
 */
public class ProductEditiorTest {
	
	private Interfacedao dao;
	private DialogFixture fixture;
	private Robot robot;

	public ProductEditiorTest() {
	}
	
	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a little bit - default is 30 (milliseconds).
		// Do NOT make it less than 10 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(75);

		// add some majors for testing with
		Collection<String> categories = new HashSet<>();
		categories.add("cat1");
		//categories.add("cat2");

		// create a mock for the DAO
		dao = mock(Interfacedao.class);

		// stub the getMajors method to return the test majors
		when(dao.getCategories()).thenReturn(categories);
	}
	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}
	
	@Test
	public void testSaveProduct() {
		// create the dialog passing in the mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();
	
		// enter some details into the UI components
		fixture.textBox("txtID").enterText("1234");
		fixture.textBox("nameTxt").enterText("ProductOne");
		fixture.textBox("descriptionTxt").enterText("A test product");
		fixture.comboBox("categoryCombo").selectItem("cat1");
		fixture.textBox("priceTxt").enterText("10.00");
		fixture.textBox("quantityTxt").enterText("3");
		
		// click the save button
		fixture.button("saveButton").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed student from the captor
		Product savedProduct = argument.getValue();

		// test that the student's details were properly saved
		assertEquals("Ensure the ID was saved", "1234", savedProduct.getProductID());
		assertEquals("Ensure the name was saved", "ProductOne", savedProduct.getName());
		assertEquals("Ensure the description was saved", "A test product", savedProduct.getDescription());
		assertEquals("Ensure the category was saved", "cat1", savedProduct.getCategory());
		assertEquals("Ensure the price was saved", new BigDecimal( "10.00"), savedProduct.getListPrice());
		assertEquals("Ensure the quantity was saved", new Integer("3"), savedProduct.getQuantityInStock());
	}	
}
