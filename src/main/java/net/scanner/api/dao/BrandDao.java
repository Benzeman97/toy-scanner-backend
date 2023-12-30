package net.scanner.api.dao;

import net.scanner.api.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandDao extends JpaRepository<Brand,Integer> {

    @Query(value = "select * from brand b where lower(b.brand_name)=:name",nativeQuery = true)
    Optional<Brand> getBrandByName(@Param("name") String name);

    @Query("from Brand b order by b.brandName asc")
    Optional<List<Brand>> getBrandList();
}
