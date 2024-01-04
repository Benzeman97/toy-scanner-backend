package net.scanner.api.service.impl;

import net.scanner.api.dao.ProductDao;
import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.entity.Product;
import net.scanner.api.service.ProductAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductAdminServiceImpl implements ProductAdminService {

    final private static Logger LOGGER = LogManager.getLogger(ProductAdminServiceImpl.class);

    private ProductDao productDao;

    public ProductAdminServiceImpl(ProductDao productDao){
        this.productDao=productDao;
    }

    @Override
    public ProductResponse getProductById(int id) {

         Product product = productDao.findById(id)
                 .orElse(new Product());

        LOGGER.info(String.format("product is returned with %d",id));

        int productId = Objects.isNull(product.getProductName()) ? ((productDao.getLastProduct().getProductId())+1) : product.getProductId();

        return new ProductResponse(productId, product.getProductName(), product.getPrice(), product.getPrevPrice(),
                product.getDiscount(), product.getDiscountPercentage(),product.getMaterial(),product.getShippingCountry(),
                gerOfferEndDate(product.getOfferExpDate()), product.getProductImg(),product.getBrandImg(), product.getPlatformImg(), product.getBrandName(),
                product.getPlatformUrl(),product.getPlatform(), product.getItemUrl(), product.getOfferType(), product.getSellingRate(),
                product.getForValue(),product.getCategoryId(),product.getDealId());

    }

    @Override
    public ProductResponse getProductByName(String name) {

        Product product = productDao.getProductByName(name.toLowerCase())
                .orElse(new Product());

        LOGGER.info(String.format("product is returned with %s",name));

        int productId = Objects.isNull(product.getProductName()) ? ((productDao.getLastProduct().getProductId())+1) : product.getProductId();

        return new ProductResponse(productId, product.getProductName(), product.getPrice(), product.getPrevPrice(),
                product.getDiscount(), product.getDiscountPercentage(),product.getMaterial(),product.getShippingCountry(),
                gerOfferEndDate(product.getOfferExpDate()), product.getProductImg(),product.getBrandImg(), product.getPlatformImg(), product.getBrandName(),
                product.getPlatformUrl(),product.getPlatform(), product.getItemUrl(), product.getOfferType(), product.getSellingRate(),
                product.getForValue(),product.getCategoryId(),product.getDealId());
    }

    private String gerOfferEndDate(LocalDateTime dateTime){

        if(Objects.isNull(dateTime))
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return dateTime.format(formatter);
    }

    @Override
    public ProductAdminResponse saveProduct(ProductRequest request) throws ParseException {

        Product product = productDao.getProductByName(request.getProductName().toLowerCase())
                .orElse(new Product());

        product = productDao.save(setProduct(product,request));

        LOGGER.info(String.format("product has been saved with %d", product.getProductId()));

        return new ProductAdminResponse(product.getProductId(),product.getProductName(),true);
    }

    @Override
    public boolean deleteProduct(int productId) {

        Product product = productDao.findById(productId)
                .orElse(null);

        if(Objects.isNull(product)) {
            LOGGER.info(String.format("product is not found with %d", productId));
            return false;
        }

        productDao.delete(product);

        LOGGER.info(String.format("product is deleted with %d", productId));

        return true;

    }

    private Product setProduct(Product product,ProductRequest request) throws ParseException {


        if(Objects.isNull(product.getProductName())) {
            product.setInsertedDate(LocalDateTime.now());
        }

         product.setProductId(request.getProductId());
         product.setProductName(request.getProductName());
         product.setPrice(request.getPrice());
         product.setPrevPrice(request.getPrevPrice());
         product.setDiscount(getDiscount(request.getPrice(),request.getPrevPrice()));
         product.setDiscountPercentage(getDiscountPercentage(request.getPrice(),request.getPrevPrice()));
         product.setMaterial(request.getMaterial());
         product.setShippingCountry(request.getShippingCountry());
         if(request.getOfferType().equalsIgnoreCase("offer"))
         product.setOfferExpDate(getOfferEndDate(request.getOfferExpDate()));
         else product.setOfferExpDate(null);
         product.setProductImg(request.getProductImg());
         product.setBrandImg(request.getBrandImg());
         product.setPlatformImg(request.getPlatformImg());
         product.setBrandName(request.getBrandName());
         product.setPlatformUrl(request.getPlatformUrl());
         product.setPlatform(request.getPlatform());
         product.setItemUrl(request.getItemUrl());
         product.setSellingRate(request.getSellingRate());
         product.setOfferType(request.getOfferType());
         product.setForValue(request.getForValue());
         product.setCategoryId(request.getCtgId());
         product.setDealId(request.getDealId());
         product.setUpdatedDate(LocalDateTime.now());

         return product;
    }

    private BigDecimal getDiscount(BigDecimal price,BigDecimal prevPrice){
        return prevPrice.subtract(price);
    }

    private int getDiscountPercentage(BigDecimal price,BigDecimal prevPrice){

        BigDecimal discountValue = prevPrice.subtract(price);

        return discountValue.divide(prevPrice, 2, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(100)).intValue();
    }

    private LocalDateTime getOfferEndDate(String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date_ = dateFormat.parse(date);

        LocalDateTime dateTime = LocalDateTime.ofInstant(date_.toInstant(), ZoneId.systemDefault())
                .withHour(23)
                .withMinute(59)
                .withSecond(59);

        return dateTime;
//        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//
//        Date inputDate = inputDateFormat.parse(date);
//
//        Map<String, String> monthAbbreviationMap = new HashMap<>();
//        monthAbbreviationMap.put("Jan", "January");
//        monthAbbreviationMap.put("Feb", "February");
//        monthAbbreviationMap.put("Mar", "March");
//        monthAbbreviationMap.put("Apr", "April");
//        monthAbbreviationMap.put("May", "May");
//        monthAbbreviationMap.put("Jun", "June");
//        monthAbbreviationMap.put("Jul", "July");
//        monthAbbreviationMap.put("Aug", "August");
//        monthAbbreviationMap.put("Sep", "September");
//        monthAbbreviationMap.put("Oct", "October");
//        monthAbbreviationMap.put("Nov", "November");
//        monthAbbreviationMap.put("Dec", "December");
//
//        SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
//        String monthAbbreviation = new SimpleDateFormat("MMM").format(inputDate);
//        String fullMonthName = monthAbbreviationMap.get(monthAbbreviation);
//        String outputDateString = outputDateFormat.format(inputDate).replace(monthAbbreviation, fullMonthName);
//
//        outputDateString = outputDateString.replace("00:00:00", "23:59:59");
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
//
//        return LocalDateTime.parse(outputDateString, formatter);

    }

    @Override
    public boolean setValueForDealId(int id) {

        List<Product> products = getProductsByDealId(id);

        products = products.stream().map(p->setValue(p)).collect(Collectors.toList());

        productDao.saveAll(products);

        LOGGER.error(String.format("deal id are set to 0 with id %d",id));

        return true;
    }

    private Product setValue(Product p){
        p.setDealId(0);
        return  p;
    }

    @Override
    public List<Product> getProductsByDealId(int id){

        List<Product> products = productDao.getProductByDealId(id)
                .orElse(Arrays.asList());

        LOGGER.error(String.format("item list is returned with id %d",id));

        return products;

    }




}
