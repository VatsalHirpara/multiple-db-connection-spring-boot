package com.nagarro.multipledbpoc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.multipledbpoc.domain.vehiclepricing.VehiclePricing;

public class CSVUtil {
	public static String TYPE = "text/csv";
	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<VehiclePricing> csvToVehiclePricings(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<VehiclePricing> vehiclePricings = new ArrayList<VehiclePricing>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				vehiclePricings.add(csvRecordToVehiclePricing(csvRecord));
			}
			return vehiclePricings;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	
	private static VehiclePricing csvRecordToVehiclePricing(CSVRecord csvRecord) {
		VehiclePricing vehiclePricing = new VehiclePricing();
		
		vehiclePricing.setCity(csvRecord.get("city"));
		vehiclePricing.setNewOrUsed(csvRecord.get("New/ Used"));
		vehiclePricing.setCategoryName(csvRecord.get("Category Name"));
		vehiclePricing.setCategoryCode(csvRecord.get("Category Code"));
		vehiclePricing.setLeasingCompanyName(csvRecord.get("Leasing Company Name"));
		vehiclePricing.setLeasingCompanyCode(csvRecord.get("Leasing Company Code"));
		vehiclePricing.setCategoryLeasingConcode(csvRecord.get("Category Leasing Concode"));
		vehiclePricing.setValidFrom(csvRecord.get("Valid From"));
		vehiclePricing.setValidTo(csvRecord.get("Valid To"));
		vehiclePricing.setModel(csvRecord.get("Model"));
		vehiclePricing.setVariant(csvRecord.get("Variant"));
		vehiclePricing.setTransmission(csvRecord.get("Transmission"));
		vehiclePricing.setColor(csvRecord.get("Color"));
		vehiclePricing.setDescription(csvRecord.get("Description"));
		vehiclePricing.setVariantCode(csvRecord.get("Variant Code"));
		vehiclePricing.setTenor(Long.parseLong(csvRecord.get("Tenor")));
		vehiclePricing.setUniqueCode(csvRecord.get("Unique Code"));
		vehiclePricing.setMonthlyRentalPreGST(Integer.parseInt(csvRecord.get("Monthly Rental Pre-GST")) );
		vehiclePricing.setMonthlyRentalGST(Integer.parseInt(csvRecord.get("Monthly Rental GST")));
		vehiclePricing.setMonthlyRentalPostGST(Integer.parseInt(csvRecord.get("Monthly Rental Post-GST")));
		vehiclePricing.setDiscountPreGST(Integer.parseInt(csvRecord.get("Discount Pre-GST")));
		vehiclePricing.setDiscountGST(Integer.parseInt(csvRecord.get("Discount GST")));
		vehiclePricing.setDiscountPostGST(Integer.parseInt(csvRecord.get("Discount Post-GST")));
		vehiclePricing.setNetMonthlyRentalPreGST(Integer.parseInt(csvRecord.get("Net Monthly Rental Pre-GST")));
		vehiclePricing.setNetMonthlyRentalGST(Integer.parseInt(csvRecord.get("Net Monthly Rental GST")));
		vehiclePricing.setNetMonthlyRentalPostGST(Integer.parseInt(csvRecord.get("Net Monthly Rental Post-GST")));
		vehiclePricing.setSecurityDepositPreGST(Integer.parseInt(csvRecord.get("Security Deposit Pre-GST")));
		vehiclePricing.setSecurityDepositGST(Integer.parseInt(csvRecord.get("Security Deposit GST")));
		vehiclePricing.setSecurityDepositPostGST(Integer.parseInt(csvRecord.get("Security Deposit Post-GST")));
		vehiclePricing.setProcFeePreGST(Integer.parseInt(csvRecord.get("Proc. Fee Pre-GST")));
		vehiclePricing.setProcFeeGST(Integer.parseInt(csvRecord.get("Proc. Fee GST")));
		vehiclePricing.setProcFeePostGST(Integer.parseInt(csvRecord.get("Proc. Fee Post-GST")));
		vehiclePricing.setCustomerNeedstoPayPreGST(Integer.parseInt(csvRecord.get("Customer Needs to Pay Pre-GST")));
		vehiclePricing.setCustomerNeedstoPayGST(Integer.parseInt(csvRecord.get("Customer Needs to Pay GST")));
		vehiclePricing.setCustomerNeedstoPayPostGST(Integer.parseInt(csvRecord.get("Customer Needs to Pay Post-GST")));
		
		return vehiclePricing;
	}
	
}
