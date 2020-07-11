package utilities;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Setup {
    public static Cookies authentication() {
        baseURI = PropertiesFile.getData("baseURI");
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials.put("username", PropertiesFile.getData("username"));
        loginCredentials.put("password", PropertiesFile.getData("password"));

        Cookies cookies = given()
                .contentType(ContentType.JSON)
                .body(loginCredentials)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().response().getDetailedCookies()
        ;

        return cookies;
    }
}
