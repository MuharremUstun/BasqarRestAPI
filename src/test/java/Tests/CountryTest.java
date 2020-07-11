package Tests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import models.Country;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Setup;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CountryTest {
    private Cookies cookies;
    private String countryId;

    @BeforeClass
    public void init() {
        cookies = Setup.authentication();
    }

    @Test
    public void baseBasqarTest() {
        given()
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void listCountries() {
        given()
                .cookies(cookies)
                .when()
                .get("/school-service/api/countries")
                .then()
                .statusCode(200)
                .log().body()
        ;
    }

    @Test
    public void createCountry() {
        Country country = new Country();
        country.setName("My country");
        country.setCode("Mc");

        countryId = given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(country)
                .when()
                .post("/school-service/api/countries")
                .then()
                .statusCode(201)
                .extract().jsonPath().getString("id")
        ;


    }

    @Test
    public void deleteCountry(){
//        createCountry();

        given()
                .cookies(cookies)
                .when()
                .delete("/school-service/api/countries/" + countryId)
                .then()
                .log().body()
                .statusCode(200);

    }
}
