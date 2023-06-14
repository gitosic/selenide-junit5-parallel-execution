package api.stepdefinitions;

import api.helpers.Specifications;
import api.requestsAndResponses.UserData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStepdefs {

    private final static String URL = "https://reqres.in/";
    private static List<UserData> users = null;

    @When("Get a list of users from {string} page")
    public void get_a_list_of_users_from_page(String page) {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        users = given()
                .when()
                .get(page)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }

    @Then("users and avatars names match")
    public void users_and_avatars_names_match() {
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

}
