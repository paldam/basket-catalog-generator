package com.damian.repository;

import com.damian.domain.CatalogArchive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the CatalogArchive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatalogArchiveRepository extends JpaRepository<CatalogArchive, Long> {

    @Query(value = "select distinct catalog_archive from CatalogArchive catalog_archive left join fetch catalog_archive.baskets",
        countQuery = "select count(distinct catalog_archive) from CatalogArchive catalog_archive")
    Page<CatalogArchive> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct catalog_archive from CatalogArchive catalog_archive left join fetch catalog_archive.baskets")
    List<CatalogArchive> findAllWithEagerRelationships();

    @Query("select catalog_archive from CatalogArchive catalog_archive left join fetch catalog_archive.baskets where catalog_archive.id =:id")
    Optional<CatalogArchive> findOneWithEagerRelationships(@Param("id") Long id);

}
