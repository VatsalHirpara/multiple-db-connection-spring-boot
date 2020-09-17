package com.nagarro.multipledbpoc.constants;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public class FilePath {
	private FilePath() {
	}

	@Value("${spring.profiles.active}")
	private static String activeProfile;
	
	private static String currentDate = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
	private static String generatedScript = "./generated_scripts/";
	
	private static String action = "INSERT";

	public static String getEnvironment() {
		return activeProfile;
	}

	public static File getFailedFilePath() {
		String failedRecord = "./failed_record/MSIL_Failed_Recod_" + getEnvironment() + "_" + currentDate + ".csv";
		return new File(failedRecord);
	}

	/**
	 * file location for generated scripts for payment
	 */
	public static class PAYMENT {

		private PAYMENT() {
		}

		public static File getCategoryModels() {
			return new File(
					generatedScript + action + "_category_models_" + getEnvironment() + "_" + currentDate + ".txt");
		}

		public static File getDMSPriceMapping() {
			return new File(
					generatedScript + action + "_dms_price_mapping_" + getEnvironment() + "_" + currentDate + ".txt");
		}
	}
}
