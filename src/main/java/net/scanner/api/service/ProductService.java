package net.scanner.api.service;

import net.scanner.api.dto.response.ProductListResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getNewOfferItems();
    List<ProductResponse> getUnder25Items();
    List<ProductResponse> getNewReleaseItems();
    List<ProductResponse> getTrendingItems();
    List<ProductResponse> getBestSellingItems();
    ProductListResponse getNewOfferItemList(int page);

    ProductListResponse getUnder25ItemList(int page);

    ProductListResponse getNewReleaseItemList(int page);

    ProductListResponse getBestSellingItemList(int page);

    ProductListResponse getItemsByBrand(String name,int page);

    ProductListResponse getItemsBySearch(String f_value,int ctg_id,String value,int page);

    List<Product> getProductsByDealId(int id);


    ProductListResponse getOffersByFilter();

}
