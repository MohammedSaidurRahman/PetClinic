package com.qa.PetClinicJUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetClinicHome {
	
	@FindBy(xpath = "/html/body/app-root/app-welcome/h1")
	private WebElement welcomeTitle;
	
	@FindBy(xpath = "/html/body/app-root/div[1]/nav/div/ul/li[2]/a")
	private WebElement navBarOwners;
	
	@FindBy(xpath = "/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[1]/a")
	private WebElement allOwnersLink;
	
	
	public String getWelcomeTitle() {
		return welcomeTitle.getText();
	}
	
	public void navigateToOwners() {
		navBarOwners.click();
		allOwnersLink.click();
	}

}
