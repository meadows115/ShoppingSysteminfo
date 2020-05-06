/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.Interfacedao;
import dao.ProductManagerdao;
import org.jooby.Jooby;

/**
 *
 * @author meani898
 */
public class ProductModule extends Jooby {

    public ProductModule(Interfacedao productDao) {
        //get all prodycts and get by ID
        get("/api/products", () -> productDao.getProducts());
        get("/api/products/:id", (req) -> {
            String id = req.param("id").value();
            return productDao.searchProduct(id);
        });

        //get all product categories
        get("/api/categories", () -> productDao.getCategories());

        //get products by category
        get("/api/categories/:category", (req) -> {
            String category = req.param("category").value();
            return productDao.filterCategories(category);
        });
    }
}
