package com.qa.PetClinicJUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetClinicOwnerInformation {
	
	@FindBy(xpath = "/html/body/app-root/app-owner-detail/div/div/h2[1]")
	private WebElement ownerInformationPageTitle;
	
	public String getOwnerInformationPageTitle() {
		return ownerInformationPageTitle.getText();
	}
}
