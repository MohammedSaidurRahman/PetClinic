package com.qa.PetClinicJUnit;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.Util.Constant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetClinicTest {
	
	
	static ExtentReports report;
	static ExtentTest testReport;
	static int i=1;
	WebDriver driver;
	
	PetClinicHome homePage;
	PetClinicOwnersList ownersPage;
	PetClinicOwnerInformation ownersInformationPage;
	
	Response response;
	RequestSpecification request;
	JSONObject owner;
	JSONObject pets;
	JSONObject type;
	JSONObject visits;
	JSONObject roles;
	
	JSONArray nestedOwner;
	JSONArray nestedPets;
	JSONArray nestedType;
	JSONArray nestedVisits;
	JSONArray nestedRoles;
	
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Testing\\chromedriver.exe");
		driver = new ChromeDriver();
		report = Constant.report;
		testReport = report.startTest("Pet Clinic Scenario Test: " + i);
		driver.manage().window().maximize();

	}
	
	
	
	
	@Test
	public void userStory() {
		driver.get(Constant.homeURL);
		homePage = PageFactory.initElements(driver, PetClinicHome.class);

		if(homePage.getWelcomeTitle().equals("Welcome to Petclinic")) {
			testReport.log(LogStatus.PASS, "Welcome page has loaded successfully.");
		} else {
			testReport.log(LogStatus.FAIL, "Welcome page has failed to load.");
		}
		
		assertEquals("Welcome to Petclinic", homePage.getWelcomeTitle());
		
		homePage.navigateToOwners();

		ownersPage = PageFactory.initElements(driver, PetClinicOwnersList.class);
		
		if(ownersPage.getOwnersTitle().equals("Owners")) {
			testReport.log(LogStatus.PASS, "Owners page has loaded successfully. A list of owners is displayed.");
		} else {
			testReport.log(LogStatus.FAIL, "Owners page has failed to load.");
		}
		
		ownersPage.viewOwnerInfo();
		
		ownersInformationPage = PageFactory.initElements(driver, PetClinicOwnerInformation.class);
		
		if(ownersInformationPage.getOwnerInformationPageTitle().equals("Owner Information")) {
			testReport.log(LogStatus.PASS, "Individual owner information is displayed alongside contact and pet details.");
		} else {
			testReport.log(LogStatus.FAIL, "The individual owners information has failed to load.");
		}
		
		assertEquals("Owner Information", ownersInformationPage.getOwnerInformationPageTitle());
		
		
	}
	
	
	@Test
	public void checkConnection() {
	Response response = given().contentType(ContentType.JSON)
	.when()
	.get("http://10.0.10.10:9966/petclinic/swagger-ui.html#/");
	System.out.println("Test 1 - Check connection to site");
	System.out.println(response.getStatusCode());
	;


	}

	@Test
	public void createUser() {
		
		System.out.println("Test 2 - Update Admin Information");
		request = given().contentType(ContentType.JSON);	
		
		RequestSpecification request = RestAssured.given();

		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/users";
		request.header("Content-Type", "application/json");
		
		
		roles = new JSONObject();
		
		roles.put("id", 10);
		roles.put("name", "root");
		
		
		nestedRoles = new JSONArray();
		
		nestedRoles.put(roles);
		
		JSONObject requestParams = new JSONObject();

		requestParams.put("enabled", true);
		requestParams.put("password", "root");
		requestParams.put("roles", nestedRoles);
		requestParams.put("username", "root");

		response = request.when().
				body(requestParams.toString()).
				post("http://10.0.10.10:9966/petclinic/api/users");
		
		System.out.println(response.body().prettyPrint());
		System.out.println(response.getStatusCode());

	}
	
	@Test
	public void createOwner() {
		
		System.out.println("Test 3 - Create Owner");
		request = given().contentType(ContentType.JSON);	
		
		RequestSpecification request = RestAssured.given();

		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/owners";
		request.header("Content-Type", "application/json");
		
		
		
		
		JSONObject petter = new JSONObject();

		visits = new JSONObject();
		visits.put("date", "2018/12/25");
		visits.put("description", "Rodger has trouble eating.");
		visits.put("id", 100);
		visits.put("pet", petter);
		nestedVisits = new JSONArray();
		nestedVisits.put(visits);
		
		type = new JSONObject();
		type.put("id", 100);
		type.put("name", "hamster");
		nestedType = new JSONArray();
		nestedType.put(type);
		
		
		pets = new JSONObject();
		
		pets.put("id", 100);
		pets.put("name", "Rodger");
		pets.put("birthDate", "2012/08/06");
		pets.put("type", nestedType);
		pets.put("owner", 6);
		pets.put("visits", nestedVisits);
		nestedPets = new JSONArray();
		nestedPets.put(pets);
		

		JSONObject requestParams = new JSONObject();
		requestParams.put("id", 100);
		requestParams.put("firstName", "Mohammed");
		requestParams.put("lastName", "Rahman");
		requestParams.put("address", "128 Edna Street");
		requestParams.put("city", "Stockport");
		requestParams.put("telephone", "7655522814");
		requestParams.put("pets", nestedPets);
	
		response = request.when().
				body(requestParams.toString()).post("http://10.0.10.10:9966/petclinic/api/owners");
		
		System.out.println(response.body().prettyPrint());
		System.out.println(response.getStatusCode());
	
		
	}
	
	
	@Test
	public void addAnimal() {
		
		System.out.println("Test 4 - Add/Update animal");
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/pets/";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject requestParams = new JSONObject();
		
		
		pets = new JSONObject();
		owner = new JSONObject();
		type = new JSONObject();
		visits = new JSONObject();
		
		owner.put("address", "128 Edna Street");
		owner.put("city", "Stockport");
		owner.put("firstName", "Mohammed");
		owner.put("id", 1);
		owner.put("lastName", "Rahman");
		owner.put("pets", nestedPets);
		owner.put("telephone", "55522814");
		
		type.put("id", 200);
		type.put("name", "hamster");
		
		visits.put("date", "2018/12/25");
		visits.put("description", "Rodger has trouble eating.");
		visits.put("id", 200);
		visits.put("pet", nestedPets);
		
		nestedType = new JSONArray();
		nestedType.put(type);
		
		nestedVisits = new JSONArray();
		nestedVisits.put(visits);
		
		nestedOwner = new JSONArray();
		nestedOwner.put(owner);
		
		
		//Start requesting what information we want

		requestParams.put("birthDate", "2018/09/27");
		requestParams.put("id", 1);
		requestParams.put("name", "Rodger");
		requestParams.put("owner", nestedOwner);
		requestParams.put("type", nestedType);
		requestParams.put("visits", nestedVisits);

		//Send the request we have made
		response = request.when().
				body(requestParams.toString()).post("http://10.0.10.10:9966/petclinic/api/pets/");
		
		System.out.println(response.body().prettyPrint());
		System.out.println(response.getStatusCode());

	}
	/*
	@Test
	public void deleteAnimal() {
		System.out.println("Test 5 - Delete Animal");
		Response response = given().contentType(ContentType.JSON).when()
				.delete("http://10.0.10.10:9966/petclinic/api/pets/100");
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}
	

*/
	
	@After
	public void tearDown() {
		report.endTest(testReport);
		report.flush();
		i=i+1;
		driver.quit();
	}

}
