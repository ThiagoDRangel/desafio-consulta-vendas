package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(Pageable pageable) {
		Page<SaleMinDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleMinDTO>> getSummary(
			@RequestParam(name = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<SaleMinDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/sales/summary")
	public ResponseEntity<Page<SaleSummaryDTO>> getSummary(
			@RequestParam(name = "minDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
			@RequestParam(name = "maxDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
			Pageable pageable) {
		Page<SaleSummaryDTO> summary = service.getSummaryByDateRange(minDate, maxDate, pageable);
		return ResponseEntity.ok(summary);
	}
}
