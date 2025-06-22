package com.stepdef.API;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;

import java.util.HashMap;
import java.util.Map;

public class UserTokenStepDef {
    private Map<String, Object> requestBody;
    private Response response;

    @Given("the user has email {string} and password {string}")
    public void the_user_has_email_and_password(String email, String password) {
        requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
    }

    @When("the user sends a POST request to {string}")
    public void the_user_sends_a_post_request_to(String endpoint) {
        RestAssured.baseURI = "https://reqres.in";
        response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then()
                .log().all()
                .assertThat()
                .statusCode(statusCode);
    }

    @Then("the response JSON should contain a non-null {string}")
    public void the_response_JSON_should_contain_a_non_null(String fieldName) {
        response.then()
                .assertThat()
                .body(fieldName, CoreMatchers.notNullValue());
    }
}