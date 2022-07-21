package com.insider.common;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.insider.common.Constants;

public class Constants {

	public static final String TESTNG = "testng.xml";
	public static String Image_path;
	public static String RESOURCES = "src/main/resources";
	public static Properties prop;
	public static String File_Path;
	public static final String baseURI = "https://petstore.swagger.io/v2/pet";
	public static final String findByStatus = "/findByStatus?status=" + Constants.available_status;
	public static final String validPetId = "/" + Constants.valid_pet_id;
	public static final String invalidPetId = "/" + Constants.invalid_pet_id;
	public static final String uploadImage = "/uploadImage";
	

	public static final String available_status = "available";
	public static final String pending_status = "pending";
	public static final String sold_status = "sold";
	public static final String invalid_status = "Unavailablezase";
	public static final int valid_pet_id = 10;
	public static final int invalid_pet_id = -1;
	public static final String error_message = "Pet not found";
	public static final String unknown_message = "unknown";

	public static final String formData = "multipart/form-data";


	public static String ReportFileName;
	public static String REPORTPATH = "/Reports/";
	public static String test = "";

	public static String totalTestCasesCount;
	public static String passedTestCasesCount;
	public static String failedTestCasesCount;
	public static String skippedTestCasesCount;

	public static void initConfig() {
		prop = getPropertisData();

		 Image_path = System.getProperty("user.dir") + File.separator + Constants.RESOURCES + File.separator + "Data"
				+ File.separator + "download.jpg";
	}

	public static Properties getPropertisData() {
		Properties prop = null;
		FileReader reader = null;
		try {
			prop = new Properties();
			prop.load(reader);
		} catch (Exception e) {
			System.out.println("Problem initilaising config file." + e.getMessage());
		}
		return prop;
	}

}
