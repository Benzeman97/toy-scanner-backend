package net.scanner.api.service;

import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;

import java.text.ParseException;

public interface ProductAdminService {

    ProductResponse getProductById(int id);

    ProductResponse getProductByName(String name);

    ProductAdminResponse saveProduct(ProductRequest request) throws ParseException;

     boolean deleteProduct(int productId);
}
