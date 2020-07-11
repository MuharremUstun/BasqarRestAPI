package Tests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import models.Country;
import models.Location;
import models.School;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.PropertiesFile;
import utilities.RandomString;
import utilities.Setup;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LocationTest {
    private Cookies cookies;
    private String locationId;
    School school = new School();
    private String schoolId = "5e035f8c9ea1a129f71ac585";
    private String apiPath;

    @BeforeClass
    public void init() {
        cookies = Setup.authentication();
        school.setId(schoolId);
        apiPath = "/school-service/api/location/";
    }

    private Location getLocationRequestBody() {
        Location location = new Location();
        location.setName(RandomString.alphaNumericWord(12));
        location.setShortName(RandomString.alphaNumericWord(3));
        location.setCapacity("20");
        location.setType("CLASS");
        location.setSchool(school);

        return location;
    }

    @Test
    public void listLocations() {
        given()
                .cookies(cookies)
                .when()
                .get("/school-service/api/school/" + schoolId + "/location")
                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void createLocation() {

        Location location = getLocationRequestBody();

        locationId = given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body(location)
                .when()
                .post(apiPath)
                .then()
                .statusCode(201)
                .extract().jsonPath().getString("id")
        ;
    }

    @Test
    public void deleteLocation(){
        given()
                .cookies(cookies)
                .when()
                .delete(apiPath + locationId)
                .then()
                .statusCode(200);

    }
}
