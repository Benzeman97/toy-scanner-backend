package net.scanner.api.dao;

import net.scanner.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

    @Query(value = "select * from prodduct p where p.offer_type='offer' order by p.updated_date desc limit 10",nativeQuery = true)
    Optional<List<Product>> getNewOfferItems();

    @Query(value = "select * from prodduct p where p.price < 25.00 limit 10",nativeQuery = true)
    Optional<List<Product>> getUnder25Items();

    @Query(value = "select * from prodduct p where p.offer_type='release' order by p.updated_date desc limit 10",nativeQuery = true)
    Optional<List<Product>> getNewReleaseItems();

    @Query(value = "select * from prodduct p where p.price < 100.00 order by p.selling_rate desc limit 10 offset 10",nativeQuery = true)
    Optional<List<Product>> getTrendingItems();

    @Query(value = "select * from prodduct p order by p.selling_rate desc limit 10",nativeQuery = true)
    Optional<List<Product>> getBestSellingItems();

}
