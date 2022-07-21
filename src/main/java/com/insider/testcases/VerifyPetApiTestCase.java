package com.insider.testcases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insider.common.Constants;
import com.insider.pojo.Root;
import com.insider.utils.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VerifyPetApiTestCase {

	String name = ReusableMethods.getRandomString(5);
	int id = ReusableMethods.getRandomInteger(2);

	String name_1 = ReusableMethods.getRandomString(4);
	int id_1 = ReusableMethods.getRandomInteger(3);

	String name_2 = ReusableMethods.getRandomString(5);
	int id_2 = ReusableMethods.getRandomInteger(4);

	/**
	 * This method is add new pet
	 * @return
	 * @throws JsonProcessingException
	 */
	public Response addNewPet() throws JsonProcessingException {

		Root root = new Root();
		root.setName(name);
		root.setId(id);

		ObjectMapper mapper = new ObjectMapper();
		String reqBody = mapper.writeValueAsString(root);
		System.out.println(reqBody);

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType("application/json").body(reqBody).when()
					.put(Constants.baseURI).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method is create new pet using id and status
	 * @return
	 * @throws JsonProcessingException
	 */
	public Response createNewPetWithIdAndStatus() throws JsonProcessingException {

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType("application/x-www-form-urlencoded")
					.formParam("name", name_2).formParam("status", Constants.available_status).when()
					.post("https://petstore.swagger.io/v2/pet/" + id).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method upload an image
	 * @return :response
	 * @throws JsonProcessingException
	 */
	public Response uploadAnImage() throws JsonProcessingException {

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType(Constants.formData).multiPart("petId", id)
					.multiPart("additionalMetadata", ReusableMethods.getRandomString(4)).multiPart("file", Constants.Image_path).when()
					.post(Constants.baseURI + "/" + id + Constants.uploadImage).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method update an existing pet
	 * @return
	 * @throws JsonProcessingException
	 */
	public Response updateAnExistingPet() throws JsonProcessingException {

		Root root = new Root();
		root.setName(name_1);
		root.setId(id_1);

		ObjectMapper mapper = new ObjectMapper();
		String reqBody = mapper.writeValueAsString(root);
		System.out.println(reqBody);

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType("application/json").body(reqBody).when()
					.put(Constants.baseURI).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method is get pet using status
	 * @return
	 */
	public Response getPetByUsingStatus() {
		try {
			RestAssured.baseURI = Constants.baseURI;
			Response response = RestAssured.given().accept(ContentType.JSON).when()
					.get(Constants.baseURI + Constants.findByStatus).then().extract().response();
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method is get pet using id
	 * @return
	 */
	public Response getPetByUsingId() {
		try {
			RestAssured.baseURI = Constants.baseURI;
			Response response = RestAssured.given().accept(ContentType.JSON).when().get(Constants.baseURI + "/" + id)
					.then().extract().response();
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method is delete pet using Id
	 * @return
	 */
	public Response deletePetUsingId() throws JsonProcessingException {

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType(ContentType.JSON).when()
					.delete(Constants.baseURI + "/" + id_1).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

//Invalid scenarios

	/**
	 * This method is used for the invalid delete request
	 * @return
	 */
	public Response deletePetUsingInvalidId() throws JsonProcessingException {

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType(ContentType.JSON).when()
					.delete(Constants.baseURI + "/" + id_1).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method is used for the invalid get request using invalid status
	 * @return
	 */
	public Response getPetByUsingInvalidStatus() {
		try {
			RestAssured.baseURI = Constants.baseURI;
			Response response = RestAssured.given().accept(ContentType.JSON).when()
					.get(Constants.baseURI + Constants.invalid_status).then().extract().response();
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	/**
	 * This method is used for the invalid get request using invalid id
	 * @return
	 */
	public Response getPetByUsingInvalidId() {
		try {
			RestAssured.baseURI = Constants.baseURI;
			Response response = RestAssured.given().accept(ContentType.JSON).when()
					.get(Constants.baseURI + Constants.invalidPetId).then().extract().response();
			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * This method is used for the invalid post request to update an existing pet
	 * @return
	 */
	public Response updateAnExistingPetWithInvalidData() throws JsonProcessingException {

		Root root = new Root();
		root.setName(ReusableMethods.getRandomString(4));
		root.setId(Constants.invalid_pet_id);

		ObjectMapper mapper = new ObjectMapper();
		String reqBody = mapper.writeValueAsString(root);
		System.out.println(reqBody);

		try {
			RestAssured.baseURI = Constants.baseURI;

			Response response = RestAssured.given().contentType("application/json").body(reqBody).when()
					.post(Constants.baseURI + "/" + Constants.invalid_pet_id).then().extract().response();

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
