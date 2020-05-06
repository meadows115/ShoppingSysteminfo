/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAO;
import dao.CustomerJDBCdao;
import dao.Interfacedao;
import dao.ProductManagerdao;
import dao.SaleDAO;
import dao.SaleJdbcDAO;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author meani898
 */
public class Server extends Jooby {

    private final Interfacedao productDao = new ProductManagerdao();
    private final CustomerDAO customerDao = new CustomerJDBCdao();
	 private final SaleDAO saleDao= new SaleJdbcDAO();

    //implement this when ready to do the sale class. 
    // private final SaleDAO saleDao= new SaleCollectionsDAO();
    public Server() {
        port(8080);
        use(new Gzon());
        //registers the modules
        use(new ProductModule(productDao));
        use(new CustomerManagementModule(customerDao));
		  use(new SaleModule(saleDao));
        use(new AssetModule());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
// wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }

}
