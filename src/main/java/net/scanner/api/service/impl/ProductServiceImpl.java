package net.scanner.api.service.impl;

import net.scanner.api.dao.ProductDao;
import net.scanner.api.dto.response.ProductListResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.entity.Product;
import net.scanner.api.exceptional.DataNotFoundException;
import net.scanner.api.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

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
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

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
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

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
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

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
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    @Cacheable(key = "{#page,#root.methodName}",value = "SCANNER")
    public ProductListResponse getNewOfferItemList(int page) {

        int itemPerPage = 16;

        int start=page;

        if(start==1)
            start=0;
        else
            start= (page-1) * itemPerPage;

        List<Product> products = productDao.getNewOfferItemList(start)
                .orElseThrow(()->{
                    LOGGER.error(String.format("new offer item list is empty"));
                    throw new DataNotFoundException(String.format("new offer item list is empty"));
                });

        LOGGER.error(String.format("new offer list is returned with page %d",page));

        List<ProductResponse> productResponses = products.stream().map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

        long numOfItems = productDao.getNumOfItems();

        return new ProductListResponse(productResponses,numOfItems);
    }

    @Override
    @Cacheable(key = "{#page,#root.methodName}",value = "SCANNER")
    public ProductListResponse getUnder25ItemList(int page) {

        int itemPerPage = 16;

        int start=page;

        if(start==1)
            start=0;
        else
            start= (page-1) * itemPerPage;

        List<Product> products = productDao.getUnder25ItemList(start)
                .orElseThrow(()->{
                    LOGGER.error(String.format("under 25 item list is empty"));
                    throw new DataNotFoundException(String.format("under 25 item list is empty"));
                });

        LOGGER.error(String.format("under 25 list is returned with page %d",page));

        List<ProductResponse> productResponses = products.stream().map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

        long numOfItems = productDao.getNumOfItems();

        return new ProductListResponse(productResponses,numOfItems);
    }

    @Override
    @Cacheable(key = "{#page,#root.methodName}",value = "SCANNER")
    public ProductListResponse getNewReleaseItemList(int page) {

        int itemPerPage = 16;

        int start=page;

        if(start==1)
            start=0;
        else
            start= (page-1) * itemPerPage;

        List<Product> products = productDao.getNewReleaseItemList(start)
                .orElseThrow(()->{
                    LOGGER.error(String.format("new release item list is empty"));
                    throw new DataNotFoundException(String.format("new release item list is empty"));
                });

        LOGGER.error(String.format("new release item list is returned with page %d",page));

        List<ProductResponse> productResponses = products.stream().map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

        long numOfItems = productDao.getNumOfItems();

        return new ProductListResponse(productResponses,numOfItems);
    }

    @Override
    @Cacheable(key = "{#page,#root.methodName}",value = "SCANNER")
    public ProductListResponse getBestSellingItemList(int page) {

        int itemPerPage = 16;

        int start=page;

        if(start==1)
            start=0;
        else
            start= (page-1) * itemPerPage;

        List<Product> products = productDao.getBestSellingItemList(start)
                .orElseThrow(()->{
                    LOGGER.error(String.format("best selling item list is empty"));
                    throw new DataNotFoundException(String.format("best selling item list is empty"));
                });

        LOGGER.error(String.format("best selling item list is returned with page %d",page));

        List<ProductResponse> productResponses = products.stream().map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getProductId())).collect(Collectors.toList());

        long numOfItems = productDao.getNumOfItems();

        return new ProductListResponse(productResponses,numOfItems);
    }

    @Override
    @Cacheable(key = "{#name,#page,#root.methodName}",value = "SCANNER")
    public ProductListResponse getItemsByBrand(String name, int page) {

        int itemPerPage = 16;

        int start=page;

        if(start==1)
            start=0;
        else
            start= (page-1) * itemPerPage;

        List<Product> products = productDao.getItemsByBrand(name.toLowerCase(),page)
                .orElseThrow(()->{
                    LOGGER.error(String.format("%s item list is empty",name));
                    throw new DataNotFoundException(String.format("%s item list is empty",name));
                });

        LOGGER.error(String.format("%s item list is returned with page %d",name,page));

        List<ProductResponse> productResponses = products.stream().map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

        long numOfItems = productDao.getNumOfItems();

        return new ProductListResponse(productResponses,numOfItems);

    }

    @Override
    @Cacheable(key = "{#f_value,#ctg_id,#value,#page,#root.methodName}",value = "SCANNER")
    public ProductListResponse getItemsBySearch(String f_value, int ctg_id, String value, int page) {

        int itemPerPage = 16;

        int start=page;

        if(start==1)
            start=0;
        else
            start= (page-1) * itemPerPage;

        BigDecimal minPrice;
        BigDecimal maxPrice;

        String priceRange = value;

        switch (priceRange) {
            case "u1":
                minPrice = new BigDecimal("00.01");
                maxPrice = new BigDecimal("25.00");
                break;
            case "u2":
                minPrice = new BigDecimal("25.00");
                maxPrice = new BigDecimal("50.00");
                break;
            case "u3":
                minPrice = new BigDecimal("50.00");
                maxPrice = new BigDecimal("100.00");
                break;
            case "u4":
                minPrice = new BigDecimal("100.00");
                maxPrice = new BigDecimal("200.00");
                break;
            case "u5":
                minPrice = new BigDecimal("200.00");
                maxPrice = new BigDecimal("50000.00"); // Highest possible value
                break;
            case "u6":
                minPrice = BigDecimal.ZERO;
                maxPrice = new BigDecimal("50000.00");
                break;
            default:
                minPrice = BigDecimal.ZERO;
                maxPrice = new BigDecimal("50000.00");
                break;
        }

        List<Product> products = productDao.getItemsBySearch(f_value.toLowerCase(),ctg_id,minPrice,maxPrice,page)
                .orElseThrow(()->{
                    LOGGER.error(String.format("item list is empty"));
                    throw new DataNotFoundException(String.format("item list is empty"));
                });

        LOGGER.error(String.format("item list is returned with page %d",page));

        List<ProductResponse> productResponses = products.stream().map(p->new ProductResponse(p.getProductId(),p.getProductName(),
                p.getPrice(),p.getPrevPrice(),p.getDiscount(),p.getDiscountPercentage(),p.getMaterial(),p.getShippingCountry(),
                p.getOfferExpDate(),p.getProductImg(),p.getBrandImg(),p.getPlatformImg(),p.getBrandName(),p.getPlatformUrl(),
                p.getPlatform(),p.getItemUrl(),p.getOfferType(),p.getSellingRate(),p.getForValue(),p.getCategoryId())).collect(Collectors.toList());

        long numOfItems = productDao.getNumOfItems();

        return new ProductListResponse(productResponses,numOfItems);

    }
}
