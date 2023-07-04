package com.devsuperior.dsmeta.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Page<Sale> findByDateBetween(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
