
import dao.Interfacedao;
import dao.ProductManagerdao;
import gui.ProductAdministration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author meani898
 */
public class Administration {

	public static void main(String[] args) {

		Interfacedao dao = new ProductManagerdao();
		ProductAdministration mainMenu = new ProductAdministration(dao);
		// centre the frame on the screen
		mainMenu.setLocationRelativeTo(null);
		// show the frame
		mainMenu.setVisible(true);
	}
}
