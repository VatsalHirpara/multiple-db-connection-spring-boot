package com.nagarro.multipledbpoc.service;

import org.springframework.web.multipart.MultipartFile;

public interface CSVService {

	void generateSQLInsertStatementsForCategoryModels(MultipartFile file);

	void generateSQLInsertStatementsForDmsPriceMapping(MultipartFile file);

}