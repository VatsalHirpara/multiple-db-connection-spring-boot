package com.nagarro.multipledbpoc.serviceimpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.multipledbpoc.domain.vehiclepricing.VehiclePricing;
import com.nagarro.multipledbpoc.repository.commonmasterdata.CityRepository;
import com.nagarro.multipledbpoc.repository.payment.paymentRepository;
import com.nagarro.multipledbpoc.repository.subscribevehiclemanagement.CategoryMasterRepository;
import com.nagarro.multipledbpoc.service.CSVService;
import com.nagarro.multipledbpoc.util.CSVUtil;

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

	private HashMap<String, Long> cityIdHashMap = new HashMap<>();

	private HashMap<String, Long> categoryIdHashMap = new HashMap<>();

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

		LocalDateTime currentDateTime = LocalDateTime.now();
		String dateString = currentDateTime.getDayOfMonth() + "_" + currentDateTime.getMonthValue() + "_"
				+ currentDateTime.getYear() + "_" + currentDateTime.getHour() + "_" + currentDateTime.getMinute() + "_"
				+ currentDateTime.getSecond();

		File file = new File("insert_category_models_" + this.activeProfile + "_" + dateString + ".txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		for (VehiclePricing vehiclePricing : vehiclePricings) {
			long category_id;
			if (categoryIdHashMap.containsKey(vehiclePricing.getCategoryName().trim())) {
				category_id = categoryIdHashMap.get(vehiclePricing.getCategoryName().trim());
			} else {
				category_id = categoryMasterRepository.findIdByName(vehiclePricing.getCategoryName().trim());
				categoryIdHashMap.put(vehiclePricing.getCategoryName().trim(), category_id);
			}

			long city_id;
			if (cityIdHashMap.containsKey(vehiclePricing.getCity().trim())) {
				city_id = cityIdHashMap.get(vehiclePricing.getCity().trim());
			} else {
				city_id = cityRepository.findIdByName(vehiclePricing.getCity().trim());
				cityIdHashMap.put(vehiclePricing.getCity().trim(), city_id);
			}

			String model_cd = vehiclePricing.getVariantCode().trim().substring(0, 2);
			String variant_cd = vehiclePricing.getVariantCode().trim();
			long tenure_id = vehiclePricing.getTenor();
			String color_type = vehiclePricing.getColor().trim();
			String unique_code = vehiclePricing.getUniqueCode().trim();

			String insertStatement = "INSERT INTO public.category_models(category_id, model_cd, variant_cd, city_id, tenure_id,color_type,unique_code) VALUES "
					+ String.format("(%d, '%s', '%s', %d, %d,'%s', '%s');\n", category_id, model_cd, variant_cd,
							city_id, tenure_id, color_type, unique_code);
			writer.append(insertStatement);
		}
		writer.close();
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
		LocalDateTime currentDateTime = LocalDateTime.now();
		String dateString = currentDateTime.getDayOfMonth() + "_" + currentDateTime.getMonthValue() + "_"
				+ currentDateTime.getYear() + "_" + currentDateTime.getHour() + "_" + currentDateTime.getMinute() + "_"
				+ currentDateTime.getSecond();

		File file = new File("insert_dms_price_mapping" + this.activeProfile + "_" + dateString + ".txt");
		FileWriter writer = new FileWriter(file);
//		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		for (VehiclePricing vehiclePricing : vehiclePricings) {

			long city_id;
			if (cityIdHashMap.containsKey(vehiclePricing.getCity().trim())) {
				city_id = cityIdHashMap.get(vehiclePricing.getCity().trim());
			} else {
				city_id = cityRepository.findIdByName(vehiclePricing.getCity().trim());
				cityIdHashMap.put(vehiclePricing.getCity().trim(), city_id);
			}

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

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);

			LocalDate valid_from = LocalDate.parse(vehiclePricing.getValidFrom(), formatter);
			LocalDate valid_to = LocalDate.parse(vehiclePricing.getValidTo(), formatter);
			String created_on = "now()";
			String created_by = "admin";

			String insertStatement = "INSERT INTO public.dms_price_mapping(monthly_pre_gst, monthly_gst, monthly_post_gst, discount_pre_gst, discount_gst, discount_post_gst, net_monthly_pre_gst, net_monthly_gst, net_monthly_post_gst, security_pre_gst, security_gst, security_post_gst, p_fee_pre_gst, p_fee_gst, p_fee_post_gst, customer_payable_pre_gst, customer_payable_gst, customer_payable_post_gst, valid_from, valid_to, created_on, created_by) VALUES "
					+ "(" 
					+ String.format(" %d, %d, %d,", monthly_pre_gst, monthly_gst, monthly_post_gst)
					+ String.format(" %d, %d, %d,", discount_pre_gst, discount_gst, discount_post_gst)
					+ String.format(" %d, %d, %d,", net_monthly_pre_gst, net_monthly_gst, net_monthly_post_gst)
					+ String.format(" %d, %d, %d,", security_pre_gst, security_gst, security_post_gst)
					+ String.format(" %d, %d, %d,", p_fee_pre_gst, p_fee_gst, p_fee_post_gst)
					+ String.format(" %d, %d, %d,", customer_payable_pre_gst, customer_payable_gst,
							customer_payable_post_gst)
					+ String.format(" '%s', '%s', %s, '%s'", valid_from.toString(), valid_to.toString(), created_on,
							created_by)
					+ " );\n";

			writer.append(insertStatement);
			writer.flush();
		}
		writer.close();
	}
}