package com.apiautos.apiautos.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apiautos.apiautos.models.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {

    @Query("SELECT a FROM Auto a WHERE"
            + " CONCAT(a.id, a.modelo, a.año)"
            + " LIKE %?1%")
    public Page<Auto> findAll(String filtro, Pageable pageable);

}
