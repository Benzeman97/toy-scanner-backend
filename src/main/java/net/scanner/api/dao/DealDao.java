package net.scanner.api.dao;

import net.scanner.api.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealDao extends JpaRepository<Deal,Integer> {

}
