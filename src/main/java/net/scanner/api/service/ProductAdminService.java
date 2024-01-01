package net.scanner.api.service;

import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.entity.Product;

import java.text.ParseException;
import java.util.List;

public interface ProductAdminService {

    ProductResponse getProductById(int id);

    ProductResponse getProductByName(String name);

    ProductAdminResponse saveProduct(ProductRequest request) throws ParseException;

    List<Product> getProductsByDealId(int id);

    boolean setValueForDealId(int id);

     boolean deleteProduct(int productId);
}
