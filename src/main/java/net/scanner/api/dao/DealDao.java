package net.scanner.api.dao;

import net.scanner.api.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealDao extends JpaRepository<Deal,Integer> {

    @Query(value = "select * from deal d where lower(d.deal_name)=:name",nativeQuery = true)
    Optional<Deal> getDealByName(@Param("name") String name);

    @Query(value = "select * from deal d order by d.launched_date desc limit 4",nativeQuery = true)
    List<Deal> getDeals();

    @Query(value = "select * from deal d order by d.deal_id desc limit 1",nativeQuery = true)
    Deal getLastDeal();

}
