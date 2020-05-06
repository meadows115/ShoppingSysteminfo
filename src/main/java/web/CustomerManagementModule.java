/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAO;
import dao.Interfacedao;
import domain.Customer;
import org.jooby.Jooby;
import org.jooby.Result;
import org.jooby.Status;

/**
 *
 * @author meani898
 */
public class CustomerManagementModule extends Jooby {

    public CustomerManagementModule(CustomerDAO customerDao) {
        //get customer by username
        get("/api/customers/:username", (req) -> {
            String username = req.param("username").value();
            if (customerDao.getCustomer(username) == null) {
                return new Result().status(Status.NOT_FOUND);
            } else {
                return customerDao.getCustomer(username);
            }

        });

        //post- create customer
        post("/api/register", (req, rsp) -> {
            Customer customer = req.body().to(Customer.class);
            customerDao.save(customer);

            //returns a useful response to the client
            rsp.status(Status.CREATED);
        });
    }
}
