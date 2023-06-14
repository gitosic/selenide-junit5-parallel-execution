package api;

import api.helpers.Specifications;
import api.requestsAndResponses.*;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class Junit5Tests {
    private final static String URL = "https://reqres.in/";

    /**
     * Test #1
     * 1. Using the service https://reqres.in/ get a list of users from the second page
     * 2. Make sure the users and avatars names match
     * 3. Make sure the user's email ends with reqres.in
     */
    @Test
    public void checkAvatarAndIdTest() {
//        Through the specification we check "ContentType.JSON" and "expectStatusCode(200)"
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

//        First variant. By obtaining a separate list with avatars and IDs
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
        assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

//        Second variant. Directly get from our list separately an avatars and a separate IDs
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    /**
     * Test #2
     * 1. Using the https://reqres.in/ service, test user registration in the system
     * 2. It is necessary to create 2 tests:
     * - Successful registration
     * - registration with an error due to the absence of a field
     * 3. Check error codes
     */
    @Test
    public void successRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);

//         Checked that id and token in the response are not empty
        assertNotNull(successReg.getId());
        assertNotNull(successReg.getToken());

        assertEquals(id, successReg.getId());
        assertEquals(token, successReg.getToken());
    }

    @Test
    public void unSuccessRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);

        assertEquals("Missing password", unSuccessReg.getError());
    }

    /**
     * Test #3
     * 1. Using the https://reqres.in/ service, make sure that the LIST<RESOURCE> operation returns data,
     * sorted by years
     */
    @Test
    public void sortedYearsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
//        Here we have collected the years in the list
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        assertEquals(sortedYears, years);
        System.out.println("years = " + years);
        System.out.println("sortedYears = " + sortedYears);
    }

    /**
     * Test #4
     * 1. Using the service https://reqres.in/ try to delete the second user and compare the status code
     */
    @Test
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(204));
        given()
                .when()
                .delete("api/users/12")
                .then().log().all();
    }

    /**
     * Test #5
     * 1. Using the service https://reqres.in/ update user information and compare the update date
     * with the current date on the computer
     */
    @Test
    public void timeTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{8})$";
        String regexResp = "(.{5})$";
        String currentTimeWithoutRegularExpression = Clock.systemUTC().instant().toString();
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        String respTimeWithoutRegularExpression = response.getUpdatedAt();
        String respTime = response.getUpdatedAt().replaceAll(regexResp, "");
        System.out.println("currentTimeWithoutRegularExpression = " + currentTimeWithoutRegularExpression);
        System.out.println("respTimeWithoutRegularExpression = " + respTimeWithoutRegularExpression);
        assertEquals(currentTime, respTime);
    }

}
