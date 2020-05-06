package gui;

import dao.Interfacedao;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author meani898
 */
public class ViewProductsTest {

	private Interfacedao dao;
	private DialogFixture fixture;
	private Robot robot;

	private Product prodOne;
	private Product prodTwo;
	private Product prodThree;
	private Product prodFour;

	public ViewProductsTest() {
	}

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a little bit - default is 30 (milliseconds).
		// Do NOT make it less than 10 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(75);

		// add some products for testing with
		this.prodOne = new Product("1", "name1", "desc1", "cat1",
				  new BigDecimal("11.00"), new Integer("22"));
		this.prodTwo = new Product("2", "name2", "desc2", "cat2",
				  new BigDecimal("33.00"), new Integer("44"));
		this.prodThree = new Product("3", "name3", "desc3", "cat3",
				  new BigDecimal("55.00"), new Integer("66"));
		this.prodFour = new Product("4", "name4", "desc4", "cat2",
				  new BigDecimal("77.00"), new Integer("88"));

		Collection<Product> products = new HashSet<>();
		products.add(prodOne);
		products.add(prodTwo);
		products.add(prodThree);
		//categories.add("cat2");

		// create a mock for the DAO
		dao = mock(Interfacedao.class);
		// stub the getProducts method 
		when(dao.getProducts()).thenReturn(products);
		// stub the deleteProduct method
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				// remove the product from the collection that getProducts() uses
				products.remove(prodOne);
				return null;
			}
		}).when(dao).deleteProduct(prodOne);
	}

	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}

	@Test
	public void testViewProducts() {
		// create the dialog passing in the mocked DAO
		ViewProducts dialog = new ViewProducts(null, true, dao);
		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		//https://javapointers.com/tutorial/use-verify-in-mockito/
		//verify that the dao method getProducts was called
		verify(dao).getProducts();
		// get the model
		SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();
		// check the contents
		assertTrue("list contains the expected product", model.contains(prodOne));
		assertTrue("list contains the expected product", model.contains(prodTwo));
		assertTrue("list contains the expected product", model.contains(prodThree));
		assertEquals("list contains the correct number of products", 3, model.getSize());

	}

	@Test
	public void testDeleteProducts() {
		//similar to the save product test- have reused code from that test. 

		// create the dialog passing in the mocked DAO
		ViewProducts dialog = new ViewProducts(null, true, dao);
		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		// select item to delete in the list
		fixture.list("productList").selectItem(prodOne.toString());
		// click the delete button
		fixture.button("deleteJButton").click();

		// ensure a confirmation dialog is displayed
		DialogFixture confirmDialog = fixture.dialog(withTitle("confirmation").andShowing()).requireVisible();

                
		// click the Yes button on the confirmation dialog
		confirmDialog.button(withText("Yes")).click();
		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
		verify(dao).deleteProduct(argument.capture());

		// retrieve the passed student from the captor
		Product deletedProduct = argument.getValue();
		assertEquals("deletedProduct", deletedProduct, prodOne);
		
	}
}






