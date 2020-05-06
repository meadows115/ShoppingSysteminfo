package web;

import dao.SaleDAO;
import domain.Customer;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author meani898
 */
public class SaleModule extends Jooby {

	public SaleModule(SaleDAO saleDao) {
		
		//post- create sale
		post("/api/sales", (req, rsp) -> {
			Sale sale = req.body().to(Sale.class);
			saleDao.save(sale);
			//returns a useful response to the client
			rsp.status(Status.CREATED);
		});
	}
}
