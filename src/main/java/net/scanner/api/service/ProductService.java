package net.scanner.api.service;

import net.scanner.api.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getNewOfferItems();
    List<ProductResponse> getUnder25Items();
    List<ProductResponse> getNewReleaseItems();
    List<ProductResponse> getTrendingItems();
    List<ProductResponse> getBestSellingItems();

}
