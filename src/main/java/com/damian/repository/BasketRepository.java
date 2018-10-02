package com.damian.repository;

import com.damian.domain.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Basket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query(value = "select distinct basket from Basket basket left join fetch basket.products",
        countQuery = "select count(distinct basket) from Basket basket")
    Page<Basket> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct basket from Basket basket left join fetch basket.products")
    List<Basket> findAllWithEagerRelationships();

    @Query("select basket from Basket basket left join fetch basket.products where basket.id =:id")
    Optional<Basket> findOneWithEagerRelationships(@Param("id") Long id);

}
