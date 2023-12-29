package net.scanner.api.service;

import net.scanner.api.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getNewOfferItems();
    List<ProductResponse> getUnder25Items();
    List<ProductResponse> getNewReleaseItems();
    List<ProductResponse> getTrendingItems();
    List<ProductResponse> getBestSellingItems();
    List<ProductResponse> getNewOfferItemList(int page);

    List<ProductResponse> getUnder25ItemList(int page);

    List<ProductResponse> getNewReleaseItemList(int page);

    List<ProductResponse> getBestSellingItemList(int page);

}
