package com.example.gluecode;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import com.example.page.ApplicationPage;
import com.example.page.CandidatePage;
import com.example.page.LogIn;
import com.example.page.ViewAllJobsPage;
import com.example.page.ViewApplications;
import com.example.page.ViewSelfPostings;
import com.example.page.WelcomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApplyForJobTests {
	public WelcomePage wp;
	public LogIn lp;
	public WelcomePage wp2;
	public LogIn lp2;
	public CandidatePage cp;
	public ViewAllJobsPage vajp;
	public ApplicationPage ap;
	public ViewApplications vap;

	/*@Given("a user is at the welcome page of BurntOut")
	public void a_user_is_at_the_welcome_page_of_burnt_out() throws InterruptedException {
	    TimeUnit.SECONDS.sleep(1);
	    this.wp = new WelcomePage(BurntOutDriverUtility.driver);
	    assertEquals(this.wp.header.getText(), "Welcome to BurntOut!");
	}
	
	@When("a user inputs clicks the Sign in button")
	public void a_user_inputs_clicks_the_sign_in_button() {
		this.wp.signInButton.click();
	}
	@Then("the user is redirected to the login page")
	public void the_user_is_redirected_to_the_login_page() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		this.lp = new LogIn(BurntOutDriverUtility.driver);
		assertEquals(this.lp.header.getText(), "Welcome to BurntOut!");
	}
	@When("the user is at the Log in page")
	public void the_user_is_at_the_log_in_page() {
		assertEquals(this.lp.header.getText(), "Welcome to BurntOut!");
	}
	@When("the user enters their {string} and {string}")
	public void the_user_enters_their_and(String string, String string2) {
	    this.lp.logIn(string, string2);
	}*/
	
	@Then("the user is redirected to the candidate screen")
	public void the_user_is_redirected_to_the_candidate_screen() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		this.cp = new CandidatePage(BurntOutDriverUtility.driver);
	    assertEquals(this.cp.title.getText(), "Welcome to BurntOut!");
	}
	
	@When("the candidate clicks the View Jobs link\"")
	public void the_candidate_clicks_the_view_jobs_link() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		this.cp.clickViewJobsLink();
	}
	
	@Then("the candidate is redirected to the View Jobs page")
	public void the_candidate_is_redirected_to_the_view_jobs_page() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		this.vajp = new ViewAllJobsPage (BurntOutDriverUtility.driver);
		TimeUnit.SECONDS.sleep(2);
		assertEquals(this.vajp.title.getText(), "Search for a job");
		TimeUnit.SECONDS.sleep(2);
	}

	@When("the candidate clicks the Apply button for the chosen posting")
	public void the_candidate_clicks_the_apply_button_for_the_chosen_posting() throws InterruptedException {
	    this.vajp.clickApplyButton();
	    TimeUnit.SECONDS.sleep(5);
	}
	@Then("the candidate is redirected to the Submit Application screen")
	public void the_candidate_is_redirected_to_the_submit_application_screen() throws InterruptedException {
		this.ap = new ApplicationPage(BurntOutDriverUtility.driver);
		TimeUnit.SECONDS.sleep(5);
	}
	@Then("the only information the candidate provides is the resume")
	public void the_only_information_the_candidate_provides_is_the_resume() throws InterruptedException {
	  this.ap.uploadResume();
	  TimeUnit.SECONDS.sleep(5);
	}
	@When("the candidate clicks Submit Application")
	public void the_candidate_clicks_submit_application() throws InterruptedException {
		this.ap.clickSubmitButton();
		TimeUnit.SECONDS.sleep(3);
		BurntOutDriverUtility.driver.switchTo().alert().accept();
		TimeUnit.SECONDS.sleep(3);
	}
	@Then("the application is submitted")
	public void the_application_is_submitted() throws InterruptedException {
		this.ap.clickViewApplicationsLink();
		TimeUnit.SECONDS.sleep(3);
		this.vap = new ViewApplications(BurntOutDriverUtility.driver);
		TimeUnit.SECONDS.sleep(3);
		//Make sure it was added to the table of application postings
		//assertEquals(this.ap., this.vap.checkLastTitleAdded());
		assertEquals("http://localhost:4200/view-applications", BurntOutDriverUtility.driver.getCurrentUrl());
	
	}
}
