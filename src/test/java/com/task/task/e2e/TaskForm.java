package com.task.task.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TaskForm {

    @Given("I am on the task form page")
    public void i_am_on_the_task_form_page() {
        System.out.println("Navigating to the task form page");
    }

    @When("I enter the name {string}")
    public void i_enter_the_name(String name) {
        System.out.println("Entering the name: " + name);
    }

    @When("I enter the company {string}")
    public void i_enter_the_company(String company) {
        System.out.println("Entering the company: " + company);
    }

    @When("I enter the number {string}")
    public void i_enter_the_number(String number) {
        System.out.println("Entering the number: " + number);
    }

    @When("I enter the email {string}")
    public void i_enter_the_email(String email) {
        System.out.println("Entering the email: " + email);
    }

    @When("I click the Save button")
    public void i_click_the_save_button() {
        System.out.println("Clicking the Save button");
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        System.out.println("Verifying the success message");
    }
}
