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

    @Query(value = "SELECT *, CASE WHEN p.price <= :maxPrice THEN 'Under $25' WHEN p.price BETWEEN :minPrice AND $:maxPrice THEN '$25-50' WHEN p.price BETWEEN :minPrice AND :maxPrice THEN '$50-100' WHEN p.price >= :minPrice THEN '$200-Above' ELSE 'Any Price' END AS price_range FROM product p WHERE lower(p.for_value) = :forValue AND p.category_id = :ctgId AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) LIMIT 16 OFFSET :offset", nativeQuery = true)
    Optional<List<Product>> getItemsBySearch(@Param("forValue") String forValue, @Param("ctgId") int ctgId, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("offset") int offset);


    @Query(value = "select count(*) from product",nativeQuery = true)
    long getNumOfItems();
}
