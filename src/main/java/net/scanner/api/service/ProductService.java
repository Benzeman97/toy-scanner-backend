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

    ProductListResponse getItemsBySearch(String f_value,String ctg_name,String value,int page);

    List<Product> getProductsByDealId(int id);

    ProductListResponse getOffersByFilter(String filter,int page);

    ProductListResponse getUnder25ByFilter(String filter,int page);

    ProductListResponse getReleasesByFilter(String filter,int page);

    ProductListResponse getBestSellingByFilter(String filter,int page);

    ProductListResponse getBrandItemsByFilter(String filter,String brandName,int page);

    ProductListResponse getSearchItemsByFilter(String filter,String f_value,String ctg_name,String value,int page);

    void closeOffer(int prodId);

    ProductListResponse getProductsByCategory(String ctg,int page);

    ProductListResponse getProductByCategoryFilter(String ctg,String filter,int page);

    List<Product> getProductListByCategory(int ctgId);
}
