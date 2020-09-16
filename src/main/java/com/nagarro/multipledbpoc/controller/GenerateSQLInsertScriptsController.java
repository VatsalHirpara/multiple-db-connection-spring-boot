package com.nagarro.multipledbpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.multipledbpoc.service.CSVService;
import com.nagarro.multipledbpoc.util.CSVUtil;

@RestController
public class GenerateSQLInsertScriptsController {

	@Autowired
	CSVService csvService;	
	
	@PostMapping("/api/csv/generate-sql-insert-script-category-models")
	public ResponseEntity<String> generateSQLInsertStatementsForCategoryModels(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		if(CSVUtil.hasCSVFormat(file)) {
			try {
				csvService.generateSQLInsertStatementsForCategoryModels(file);
				message = "Successfully generated SQL INSERT statements";
				return ResponseEntity.status(HttpStatus.OK).body(message);
				
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				System.out.println(e.getMessage()+ e.getStackTrace() + e.getCause());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}
		return null;
	}
	
	@PostMapping("/api/csv/generate-sql-insert-script-dms-price-mapping")
	public ResponseEntity<String> generateSQLInsertStatementsForDmsPriceMapping(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		if(CSVUtil.hasCSVFormat(file)) {
			try {
				csvService.generateSQLInsertStatementsForDmsPriceMapping(file);
				message = "Successfully generated SQL INSERT statements";
				return ResponseEntity.status(HttpStatus.OK).body(message);
				
			} catch (Exception e) {
//				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//				System.out.println(e.getMessage()+ e.getStackTrace() + e.getCause());
//				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
				throw e;
			}
		}
		return null;
	}
	
	
}
