package com.nagarro.multipledbpoc.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class QueryUtil {

	public QueryUtil() {
	}

	public static String getInsertQueryForCategoryModels(String category_id, String model_cd, String variant_cd,
			String city_id, long tenure_id, String color_type, String unique_code) {

		return "INSERT INTO public.category_models(category_id, model_cd, variant_cd, city_id, tenure_id,color_type,unique_code) VALUES "
				+ String.format("(%s, '%s', '%s', %s, %d,'%s', '%s');\n", category_id, model_cd, variant_cd, city_id,
						tenure_id, color_type, unique_code);

	}

	public static String getInsertQueryForDmsPriceMapping(long monthly_pre_gst, long monthly_gst, long monthly_post_gst,
			long discount_pre_gst, long discount_gst, long discount_post_gst, long net_monthly_pre_gst,
			long net_monthly_gst, long net_monthly_post_gst, long security_pre_gst, long security_gst,
			long security_post_gst, long p_fee_pre_gst, long p_fee_gst, long p_fee_post_gst,
			long customer_payable_pre_gst, long customer_payable_gst, long customer_payable_post_gst, String valid_from,
			String valid_to, String created_on, String created_by) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);

		LocalDate validFrom = LocalDate.parse(valid_from, formatter);
		LocalDate validTo = LocalDate.parse(valid_to, formatter);

		return "INSERT INTO public.dms_price_mapping(monthly_pre_gst, monthly_gst, monthly_post_gst, discount_pre_gst, discount_gst, discount_post_gst, net_monthly_pre_gst, net_monthly_gst, net_monthly_post_gst, security_pre_gst, security_gst, security_post_gst, p_fee_pre_gst, p_fee_gst, p_fee_post_gst, customer_payable_pre_gst, customer_payable_gst, customer_payable_post_gst, valid_from, valid_to, created_on, created_by) VALUES "
				+ "(" + String.format(" %d, %d, %d,", monthly_pre_gst, monthly_gst, monthly_post_gst)
				+ String.format(" %d, %d, %d,", discount_pre_gst, discount_gst, discount_post_gst)
				+ String.format(" %d, %d, %d,", net_monthly_pre_gst, net_monthly_gst, net_monthly_post_gst)
				+ String.format(" %d, %d, %d,", security_pre_gst, security_gst, security_post_gst)
				+ String.format(" %d, %d, %d,", p_fee_pre_gst, p_fee_gst, p_fee_post_gst)
				+ String.format(" %d, %d, %d,", customer_payable_pre_gst, customer_payable_gst,
						customer_payable_post_gst)
				+ String.format(" '%s', '%s', %s, '%s'", validFrom.toString(), validTo.toString(), created_on,
						created_by)
				+ " );\n";
	}
}
