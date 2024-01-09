package net.scanner.api.dao;

import net.scanner.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

    @Query(value = "select * from product p where lower(p.product_name)=:name",nativeQuery = true)
    Optional<Product> getProductByName(@Param("name") String name);
    @Query(value = "select * from product p where p.offer_type='offer' order by p.updated_date desc limit 10",nativeQuery = true)
    Optional<List<Product>> getNewOfferItems();

    @Query(value = "select * from product p where p.price <= 25.00 limit 10",nativeQuery = true)
    Optional<List<Product>> getUnder25Items();

    @Query(value = "select * from product p where p.offer_type='release' order by p.updated_date desc limit 10",nativeQuery = true)
    Optional<List<Product>> getNewReleaseItems();

    @Query(value = "select * from product p where p.price <= 100.00 order by p.selling_rate desc limit 10 offset 10",nativeQuery = true)
    Optional<List<Product>> getTrendingItems();

    @Query(value = "select * from product p order by p.selling_rate desc limit 10",nativeQuery = true)
    Optional<List<Product>> getBestSellingItems();

    @Query(value = "select * from product p where p.offer_type='offer' order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewOfferItemList(@Param("offset") int offset);

    @Query(value = "select * from product p where p.price <= 25.00 limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getUnder25ItemList(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='release' order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewReleaseItemList(@Param("offset") int offset);


    @Query(value = "select * from product p order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBestSellingItemList(@Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.brand_name)=:brandName limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getItemsByBrand(@Param("brandName") String brandName,@Param("offset") int offset);

    @Query(value = "select * from product p where p.category_id=:ctgId limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getItemsByCategory(@Param("ctgId") int ctgId,@Param("offset") int offset);

//    @Query(value = "SELECT *, CASE WHEN p.price <= :maxPrice THEN 'Under $25' WHEN p.price BETWEEN :minPrice AND $:maxPrice THEN '$25-50' WHEN p.price BETWEEN :minPrice AND :maxPrice THEN '$50-100' WHEN p.price >= :minPrice THEN '$200-Above' ELSE 'Any Price' END AS price_range FROM product p WHERE lower(p.for_value) = :forValue AND p.category_id = :ctgId AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) LIMIT 16 OFFSET :offset", nativeQuery = true)
//    Optional<List<Product>> getItemsBySearch(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice limit 16 offset :offset", nativeQuery = true)
    Optional<List<Product>> getItemsBySearch(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select count(*) from product",nativeQuery = true)
    long getNumOfItems();

    @Query(value = "select * from product d where d.deal_id=:id limit 6",nativeQuery = true)
    Optional<List<Product>> getProductByDealId(@Param("id") int id);

    @Query(value = "select count(*) from product p where p.offer_type='offer'",nativeQuery = true)
    long getNumOfNewOfferItems();

    @Query(value = "select * from product p where p.offer_type='offer' order by p.price asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewOffersByPriceLowToHigh(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='offer' order by p.price desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewOffersByPriceHighToLow(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='offer' order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewOffersByDateNewToOld(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='offer' order by p.updated_date asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewOffersByDateOldToNew(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='offer' order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewOffersByBestSelling(@Param("offset") int offset);

    @Query(value = "select count(*) from product p where p.price <= 25.00",nativeQuery = true)
    long getNumOfUnder25Items();

    @Query(value = "select * from product p where p.price <= 25.00 order by p.price asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getUnder25ByPriceLowToHigh(@Param("offset") int offset);

    @Query(value = "select * from product p where p.price <= 25.00 order by p.price desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getUnder25ByPriceHighToLow(@Param("offset") int offset);

    @Query(value = "select * from product p where p.price <= 25.00 order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getUnder25ByDateNewToOld(@Param("offset") int offset);

    @Query(value = "select * from product p where p.price <= 25.00 order by p.updated_date asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getUnder25ByDateOldToNew(@Param("offset") int offset);

    @Query(value = "select * from product p where p.price <= 25.00 order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getUnder25ByBestSelling(@Param("offset") int offset);

    @Query(value = "select count(*) from product p where p.offer_type='release'",nativeQuery = true)
    long getNumOfNewReleaseItems();

    @Query(value = "select * from product p where p.offer_type='release' order by p.price asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewReleaseByPriceLowToHigh(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='release' order by p.price desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewReleaseByPriceHighToLow(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='release' order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewReleaseByDateNewToOld(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='release' order by p.updated_date asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewReleaseByDateOldToNew(@Param("offset") int offset);

    @Query(value = "select * from product p where p.offer_type='release' order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getNewReleaseByBestSelling(@Param("offset") int offset);


    @Query(value = "select * from product p order by p.price asc, p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBestSellingByPriceLowToHigh(@Param("offset") int offset);

    @Query(value = "select * from product p order by p.price desc, p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBestSellingByPriceHighToLow(@Param("offset") int offset);

    @Query(value = "select * from product p order by p.updated_date desc, p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBestSellingByDateNewToOld(@Param("offset") int offset);

    @Query(value = "select * from product p order by p.updated_date asc, p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBestSellingByDateOldToNew(@Param("offset") int offset);

    @Query(value = "select count(*) from product p where lower(p.brand_name)=:brandName",nativeQuery = true)
    long getNumOfBrandItems(@Param("brandName") String brandName);

    @Query(value = "select * from product p where lower(p.brand_name)=:brandName order by p.price asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBrandItemsByPriceLowToHigh(@Param("brandName") String brandName, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.brand_name)=:brandName order by p.price desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBrandItemsByPriceHighToLow(@Param("brandName") String brandName, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.brand_name)=:brandName order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBrandItemsByDateNewToOld(@Param("brandName") String brandName, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.brand_name)=:brandName order by p.updated_date asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBrandItemsByDateOldToNew(@Param("brandName") String brandName, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.brand_name)=:brandName order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getBrandItemsByBestSelling(@Param("brandName") String brandName, @Param("offset") int offset);

    @Query(value = "select count(*) from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice",nativeQuery = true)
    long getNumOfSearchItems(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query(value = "select * from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice order by p.price asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getSearchItemsByPriceLowToHigh(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice order by p.price desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getSearchItemsByPriceHighToLow(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getSearchItemsByDateNewToOld(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice order by p.updated_date asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getSearchItemsByDateOldToNew(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select * from product p where lower(p.for_value) = :forValue and p.category_id = :ctgId and p.price >= :minPrice and p.price <= :maxPrice order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getSearchItemsByBestSelling(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);

    @Query(value = "select * from product p order by p.inserted_date desc limit 1",nativeQuery = true)
    Product getLastProduct();

    @Query(value = "select * from product p where p.category_id=:ctgId",nativeQuery = true)
    Optional<List<Product>> getProductListByCategory(@Param("ctgId") int ctgId);

    @Query(value = "select * from product p where p.category_id=:ctgId order by p.price asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getCategoryItemsByPriceLowToHigh(@Param("ctgId") int ctgId, @Param("offset") int offset);

    @Query(value = "select * from product p where p.category_id=:ctgId order by p.price desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getCategoryItemsByPriceHighToLow(@Param("ctgId") int ctgId, @Param("offset") int offset);

    @Query(value = "select * from product p where p.category_id=:ctgId order by p.updated_date desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getCategoryItemsByDateNewToOld(@Param("ctgId") int ctgId, @Param("offset") int offset);

    @Query(value = "select * from product p where p.category_id=:ctgId order by p.updated_date asc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getCategoryItemsByDateOldToNew(@Param("ctgId") int ctgId, @Param("offset") int offset);

    @Query(value = "select * from product p where p.category_id=:ctgId order by p.selling_rate desc limit 16 offset :offset",nativeQuery = true)
    Optional<List<Product>> getCategoryItemsByBestSelling(@Param("ctgId") int ctgId, @Param("offset") int offset);

    @Query(value = "select count(*) from product p where p.category_id=:ctgId",nativeQuery = true)
    long getNumOfCategoryItems(@Param("ctgId") int ctgId);

}
