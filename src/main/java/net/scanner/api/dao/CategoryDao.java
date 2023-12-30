package net.scanner.api.dao;

import net.scanner.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {

    @Query(value = "select * from category c where lower(c.category_name)=:name",nativeQuery = true)
    Optional<Category> getCategoryByName(@Param("name") String name);
}
