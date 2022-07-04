package com.apiautos.apiautos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiautos.apiautos.models.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    
}
