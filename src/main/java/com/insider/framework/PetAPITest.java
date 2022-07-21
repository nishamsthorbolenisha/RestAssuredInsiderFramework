package com.insider.framework;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insider.common.Constants;
import com.insider.common.Reporter;
import com.insider.testcases.VerifyPetApiTestCase;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class PetAPITest {

	VerifyPetApiTestCase verifyPetApiTestCase;

	@BeforeTest
	public void before_test() {
		verifyPetApiTestCase = new VerifyPetApiTestCase();
		Constants.initConfig();
	}


	/**
	 * This method verify new pet is added
	 * @throws JsonProcessingException
	 */
	@Test(priority = 1)
	public void verifyNewPetIsAdded() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyNewPetIsAdded");
		Reporter.info("verify New Pet Is Added");
		Response response = verifyPetApiTestCase.addNewPet();
		if (!(response == null)) {

			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			System.out.println(responseOutput);
			Reporter.info(responseOutput);
			if (statusCode == 200) {

				Reporter.pass("New Pet is Created");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	
	/**
	 * This method verify pet is updated using id and status
	 * @throws JsonProcessingException
	 */
	@Test(priority = 2)
	public void verifyPetIsUpdatedUsingIdAndStatus() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyPetIsUpdatedUsingIdAndStatus");
		Reporter.info("Verify Pet Is Updated Using Id And Status");
		Response response = verifyPetApiTestCase.createNewPetWithIdAndStatus();
		if (!(response == null)) {

			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			System.out.println(responseOutput);
			Reporter.info(responseOutput);

			if (statusCode == 200) {

				Reporter.pass("Successfully Pet is Updated using Id and status");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}


	/**
	 * This method verify image is uploaded
	 * @throws JsonProcessingException
	 */
	@Test(priority = 3)
	public void verifyImageIsUploaded() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyImageIsUploaded");
		Reporter.info("verify image is uploaded");
		Response response = verifyPetApiTestCase.uploadAnImage();
		if (!(response == null)) {

			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			System.out.println(responseOutput);
			Reporter.info(responseOutput);

			if (statusCode == 200) {

				Reporter.pass("Image is uploaded successfully");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	
	/**
	 * This method verify existing pet is updated
	 * @throws JsonProcessingException
	 */
	@Test(priority = 4)
	public void verifyExistingPetUpdated() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyExistingPetUpdated");
		Reporter.info("verify existing pet is updated");
		Response response = verifyPetApiTestCase.updateAnExistingPet();
		if (!(response == null)) {

			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			System.out.println(responseOutput);
			Reporter.info(responseOutput);

			if (statusCode == 200) {

				Reporter.pass("Existing Pet is upadted");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	
	/**
	 * This method verify get pet by using status
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void verifyGetPetByUsingStatus() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyGetPetByUsingStatus");
		Reporter.info("verify get pet using status");
		Response response = verifyPetApiTestCase.getPetByUsingStatus();
		if (!(response == null)) {

			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			System.out.println(responseOutput);
			Reporter.info(responseOutput);

			if (statusCode == 200) {

				Reporter.pass("Successfully got the pet by using status");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();

	}

	/**
	 * This method verify get pet by using id
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void verifyGetPetByUsingId() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyGetPetByUsingId");
		Reporter.info("verify get by using id");
		Response response = verifyPetApiTestCase.getPetByUsingId();
		if (!(response == null)) {
			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			System.out.println(responseOutput);
			Reporter.info(responseOutput);

			if (statusCode == 200) {

				Reporter.pass("Successfully got the pet by using id");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}

		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	
	/**
	 * This method verify pet is deleted by using id
	 * @throws JsonProcessingException
	 */
	@Test(priority = 7)
	public void verifyPetDeletedUsingId() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyPetDeletedUsingId");
		Reporter.info("verify pet deleted using id");
		Response response = verifyPetApiTestCase.deletePetUsingId();
		if (!(response == null)) {
			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();
			Reporter.info(responseOutput);

			if (statusCode == 200) {

				Reporter.pass("New Pet is Created");
				Reporter.pass(responseOutput);
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Reporter.fail(responseOutput);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}


	/**
	 * This method verify Pet is not Deleted using invalid id
	 * @throws JsonProcessingException
	 */
	@Test(priority = 8)
	public void verifyPetIsNotDeletedUsingInvalidId() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyPetIsNotDeletedUsingInvalidId");
		Reporter.info("verify error message after pet is deleted using invalid id");
		Response response = verifyPetApiTestCase.deletePetUsingInvalidId();
		if (!(response == null)) {
			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();

			if (statusCode == 404) {

				Reporter.pass("We can not delete already deleted pet using id");
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	/**
	 * This method verify pet is not getting by using invalid status
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void verifyPetIsNotGetByUsingInvalidStatus() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyPetIsNotGetByUsingInvalidStatus");
		Reporter.info("verify error message after pet get using invalid status");
		Response response = verifyPetApiTestCase.getPetByUsingInvalidStatus();
		if (!(response == null)) {
			int statusCode = response.getStatusCode();

			if (statusCode == 404) {
				Reporter.pass("API is working fine as Status Code is: " + statusCode);
				String responseOutput = response.getBody().asString();
				System.out.println(responseOutput);
				Reporter.info(responseOutput);

				String type = JsonPath.read(responseOutput, "$.type");
				if (type.equals(Constants.unknown_message)) {
					Reporter.pass(responseOutput);
					Reporter.pass("API Schema is correct.");
				} else {
					Reporter.fail(responseOutput);
					softAssert.fail(responseOutput);
				}
			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	/**
	 * This method verify pet is not get by using invalid ids
	 * @throws Exception
	 */
	@Test(priority = 10)
	public void verifyPetIsNotGetByUsingInvalidId() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyPetIsNotGetByUsingInvalidId");
		Reporter.info("verify error message after pet get using invalid id");
		Response response = verifyPetApiTestCase.getPetByUsingInvalidId();
		if (!(response == null)) {
			int statusCode = response.getStatusCode();

			if (statusCode == 404) {
				Reporter.pass("API is working fine for invalid id as Status Code is: " + statusCode);
				String responseOutput = response.getBody().asString();

				System.out.println(responseOutput);

				String actual_error_message = JsonPath.read(responseOutput, "$.message");
				if (actual_error_message.equals(Constants.error_message)) {

					Reporter.pass("API Schema is correct.");
				} else {
					Reporter.fail(responseOutput);
					softAssert.fail(responseOutput);
				}
			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Assert.fail("API is not working as Status Code is: " + statusCode);
			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

	/**
	 * This methhod verify pet is not updated and existing with invalid data
	 * @throws JsonProcessingException
	 */
	@Test(priority = 11)
	public void verifyPetIsNotUpdateAnExistingWithInvalidData() throws JsonProcessingException {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("verifyPetIsNotUpdateAnExistingWithInvalidData");
		Reporter.info("verify error message after update an existing pet with invalid data");
		Response response = verifyPetApiTestCase.updateAnExistingPetWithInvalidData();
		if (!(response == null)) {
			int statusCode = response.getStatusCode();
			String responseOutput = response.getBody().asString();

			if (statusCode == 415) {

				Reporter.pass("We can not update an existing pet with invalid data");
				Reporter.pass("API is working fine as Status Code is: " + statusCode);

			} else {
				Reporter.fail("API is working fine as Status Code is: " + statusCode);
				Assert.fail("API is not working as Status Code is: " + statusCode);

			}
		} else {
			Reporter.fail("API is not working fine as response is null");
			Assert.fail("API is not working fine as response is null");
		}
		softAssert.assertAll();
	}

}
