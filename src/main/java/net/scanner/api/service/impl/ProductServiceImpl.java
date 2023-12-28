package net.scanner.api.service.impl;

import net.scanner.api.dao.ProductDao;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.entity.Product;
import net.scanner.api.exceptional.DataNotFoundException;
import net.scanner.api.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    final private static Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    public static final String NEW_OFFER_KEY = "NEW_OFFER_ITEM";
    public static final String U25_KEY = "U25_ITEM";
    public static final String NEW_RELEASE_KEY = "NEW_RELEASE_ITEM";
    public static final String TRENDING_KEY = "TRENDING_ITEM";
    public static final String BEST_SELLING_KEY = "BEST_SELLING_ITEM";

    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao){
       this.productDao=productDao;
    }

    @Override
    @Cacheable(key="#root.target.NEW_OFFER_KEY",value="SCANNER")
    public List<ProductResponse> getNewOfferItems() {

       List<Product> products = productDao.getNewOfferItems()
               .orElseThrow(()->{
                   LOGGER.error(String.format("new offer item list is empty"));
                   throw new DataNotFoundException(String.format("new offer item list is empty"));
               });

        LOGGER.info(String.format("new offer item list is returned"));

        Collections.shuffle(products);

        List<ProductResponse> productResponses = products.parallelStream().limit(6).map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getProdUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate())).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    @Cacheable(key="#root.target.U25_KEY",value="SCANNER")
    public List<ProductResponse> getUnder25Items() {

        List<Product> products = productDao.getUnder25Items()
                .orElseThrow(()->{
                    LOGGER.error(String.format("under 25 item list is empty"));
                    throw new DataNotFoundException(String.format("under 25 item list is empty"));
                });

        LOGGER.info(String.format("under 25 item list is returned"));

        Collections.shuffle(products);

        List<ProductResponse> productResponses = products.parallelStream().limit(6).map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getProdUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate())).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    @Cacheable(key="#root.target.NEW_RELEASE_KEY",value="SCANNER")
    public List<ProductResponse> getNewReleaseItems() {

        List<Product> products = productDao.getNewReleaseItems()
                .orElseThrow(()->{
                    LOGGER.error(String.format("new release item list is empty"));
                    throw new DataNotFoundException(String.format("new offer item list is empty"));
                });

        LOGGER.info(String.format("new release item list is returned"));

        Collections.shuffle(products);

        List<ProductResponse> productResponses = products.parallelStream().limit(6).map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getProdUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate())).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    @Cacheable(key="#root.target.NEW_RELEASE_KEY",value="SCANNER")
    public List<ProductResponse> getTrendingItems() {

        List<Product> products = productDao.getTrendingItems()
                .orElseThrow(()->{
                    LOGGER.error(String.format("trending item list is empty"));
                    throw new DataNotFoundException(String.format("trending item list is empty"));
                });

        LOGGER.info(String.format("trending item list is returned"));

        Collections.shuffle(products);

        List<ProductResponse> productResponses = products.parallelStream().limit(6).map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getProdUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate())).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    @Cacheable(key="#root.target.BEST_SELLING_KEY",value="SCANNER")
    public List<ProductResponse> getBestSellingItems() {

        List<Product> products = productDao.getBestSellingItems()
                .orElseThrow(()->{
                    LOGGER.error(String.format("best selling item list is empty"));
                    throw new DataNotFoundException(String.format("best selling item list is empty"));
                });

        LOGGER.info(String.format("best selling item list is returned"));

        Collections.shuffle(products);

        List<ProductResponse> productResponses = products.parallelStream().limit(6).map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getProdUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate())).collect(Collectors.toList());

        return productResponses;
    }
}
