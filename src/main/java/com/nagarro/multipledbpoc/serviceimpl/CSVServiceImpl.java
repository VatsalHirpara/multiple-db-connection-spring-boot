package com.nagarro.multipledbpoc.serviceimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.multipledbpoc.constants.FilePath;
import com.nagarro.multipledbpoc.domain.vehiclepricing.VehiclePricing;
import com.nagarro.multipledbpoc.exception.CategoryIdNotFoundException;
import com.nagarro.multipledbpoc.exception.CityIdNotFoundException;
import com.nagarro.multipledbpoc.repository.commonmasterdata.CityRepository;
import com.nagarro.multipledbpoc.repository.payment.paymentRepository;
import com.nagarro.multipledbpoc.repository.subscribevehiclemanagement.CategoryMasterRepository;
import com.nagarro.multipledbpoc.service.CSVService;
import com.nagarro.multipledbpoc.util.CSVUtil;
import com.nagarro.multipledbpoc.util.QueryUtil;
import com.nagarro.multipledbpoc.util.WriteIntoTextFile;

@Service
public class CSVServiceImpl implements CSVService {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	CategoryMasterRepository categoryMasterRepository;

	@Autowired
	paymentRepository paymentRepository;

	@Autowired
	Environment environment;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	private static Logger log = Logger.getLogger(CSVServiceImpl.class);

	private HashMap<String, String> cityIdHashMap = new HashMap<>();

	private HashMap<String, String> categoryIdHashMap = new HashMap<>();

	@Override
	public void generateSQLInsertStatementsForCategoryModels(MultipartFile file) {
		try {
			List<VehiclePricing> vehiclePricings = CSVUtil.csvToVehiclePricings(file.getInputStream());

			generateSQLInsertStatementsForCategoryModels(vehiclePricings);

		} catch (IOException e) {
			throw new RuntimeException("fail to generate SQL data: " + e.getMessage());
		}
	}

	private void generateSQLInsertStatementsForCategoryModels(List<VehiclePricing> vehiclePricings) throws IOException {

		for (VehiclePricing vehiclePricing : vehiclePricings) {
			String category_id = "";
			try {
				category_id = getCategoryId(vehiclePricing.getCategoryName().trim());
			} catch (CategoryIdNotFoundException e) {
				e.printStackTrace();
			}

			String city_id = "";
			try {
				city_id = getCityId(vehiclePricing.getCity().trim());
			} catch (CityIdNotFoundException e) {
				e.printStackTrace();
			}
			
			if (!category_id.isEmpty() && !city_id.isEmpty() && !category_id.equals("null") && !city_id.equals("null")) {
				String model_cd = vehiclePricing.getVariantCode().trim().substring(0, 2);
				String variant_cd = vehiclePricing.getVariantCode().trim();
				long tenure_id = vehiclePricing.getTenor();
				String color_type = vehiclePricing.getColor().trim();
				String unique_code = vehiclePricing.getUniqueCode().trim();

				String insertQuery = QueryUtil.getInsertQueryForCategoryModels(category_id, model_cd, variant_cd,
						city_id, tenure_id, color_type, unique_code);
				WriteIntoTextFile.writeString(FilePath.PAYMENT.getCategoryModels(), insertQuery);
			} else {
				System.out.println("skipping current record:  " + vehiclePricing);
				log.error("skipping current record =>> " + vehiclePricing);
			}
		}
	}

	@Override
	public void generateSQLInsertStatementsForDmsPriceMapping(MultipartFile file) {
		try {
			List<VehiclePricing> vehiclePricings = CSVUtil.csvToVehiclePricings(file.getInputStream());

			generateSQLInsertStatementsForDmsPriceMapping(vehiclePricings);

		} catch (IOException e) {
			throw new RuntimeException("fail to generate SQL data: " + e.getMessage());
		}
	}

	private void generateSQLInsertStatementsForDmsPriceMapping(List<VehiclePricing> vehiclePricings)
			throws IOException {

		for (VehiclePricing vehiclePricing : vehiclePricings) {
			int monthly_pre_gst = vehiclePricing.getMonthlyRentalPreGST();
			int monthly_gst = vehiclePricing.getMonthlyRentalGST();
			int monthly_post_gst = vehiclePricing.getMonthlyRentalPostGST();

			int discount_pre_gst = vehiclePricing.getDiscountPreGST();
			int discount_gst = vehiclePricing.getDiscountGST();
			int discount_post_gst = vehiclePricing.getDiscountPostGST();

			int net_monthly_pre_gst = vehiclePricing.getNetMonthlyRentalPreGST();
			int net_monthly_gst = vehiclePricing.getNetMonthlyRentalGST();
			int net_monthly_post_gst = vehiclePricing.getNetMonthlyRentalPostGST();

			int security_pre_gst = vehiclePricing.getSecurityDepositPreGST();
			int security_gst = vehiclePricing.getSecurityDepositGST();
			int security_post_gst = vehiclePricing.getSecurityDepositPostGST();

			int p_fee_pre_gst = vehiclePricing.getProcFeePreGST();
			int p_fee_gst = vehiclePricing.getProcFeeGST();
			int p_fee_post_gst = vehiclePricing.getProcFeePostGST();

			int customer_payable_pre_gst = vehiclePricing.getCustomerNeedstoPayPreGST();
			int customer_payable_gst = vehiclePricing.getCustomerNeedstoPayGST();
			int customer_payable_post_gst = vehiclePricing.getCustomerNeedstoPayPostGST();
			String created_on = "now()";
			String created_by = "admin";

			String valid_from = vehiclePricing.getValidFrom();
			String valid_to = vehiclePricing.getValidTo();

			String insertStatement = QueryUtil.getInsertQueryForDmsPriceMapping(monthly_pre_gst, monthly_gst,
					monthly_post_gst, discount_pre_gst, discount_gst, discount_post_gst, net_monthly_pre_gst,
					net_monthly_gst, net_monthly_post_gst, security_pre_gst, security_gst, security_post_gst,
					p_fee_pre_gst, p_fee_gst, p_fee_post_gst, customer_payable_pre_gst, customer_payable_gst,
					customer_payable_post_gst, valid_from, valid_to, created_on, created_by);

			WriteIntoTextFile.writeString(FilePath.PAYMENT.getDMSPriceMapping(), insertStatement);

		}
	}

	private String getCategoryId(String categoryName) throws CategoryIdNotFoundException {
		String category_id;
		if (categoryIdHashMap.containsKey(categoryName)) {
			category_id = categoryIdHashMap.get(categoryName);
		} else {
			category_id = String.valueOf(categoryMasterRepository.findIdByName(categoryName));
			categoryIdHashMap.put(categoryName, category_id.trim());
		}
		return category_id;
	}

	private String getCityId(String cityName) throws CityIdNotFoundException {
		String city_id;
		if (cityIdHashMap.containsKey(cityName)) {
			city_id = cityIdHashMap.get(cityName);
		} else {
			city_id = String.valueOf(cityRepository.findIdByName(cityName));
			cityIdHashMap.put(cityName, city_id);
		}
		return city_id;
	}
}