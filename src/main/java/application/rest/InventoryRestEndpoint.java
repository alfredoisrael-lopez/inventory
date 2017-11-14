/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package application.rest;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import application.data.ProductDAO;
import application.data.ProductDAOImpl;
import application.dto.Product;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@ApplicationPath("/api")
@Path("/")
public class InventoryRestEndpoint extends Application {

    ProductDAO productDao = new ProductDAOImpl();

    @GET
    @Path("/products")
    @ApiOperation(value = "REST endpoint for Products",
                  notes = "GET operation to list the product catalog")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Products found"),
                            @ApiResponse(code = 404, message = "No products found")} )              
    public Response listProducts() {
        List<Product> products = productDao.findProducts();
        return Response.ok(products.toArray(new Product[products.size()])).build();
    }

    @POST
    @Path("/products")
    @ApiOperation(value = "REST endpoint for Products",
                  notes = "POST operation to save the product into the catalog")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Product saved"),
                            @ApiResponse(code = 404, message = "No product saved")} )              
    public Response saveProduct(Product product) {
        productDao.saveProduct(product);
        return Response.ok(product).build();
    }

}