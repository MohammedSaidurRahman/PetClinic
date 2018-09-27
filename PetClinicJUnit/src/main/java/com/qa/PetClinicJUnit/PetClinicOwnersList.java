package com.qa.PetClinicJUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PetClinicOwnersList {
	
	@FindBy(xpath = "/html/body/app-root/app-owner-list/div/div/h2")
	private WebElement ownersTitle;
	
	@FindBy(xpath = "/html/body/app-root/app-owner-list/div/div/div/table/tbody/tr[1]/td[1]/a")
	private WebElement georgeFranklin;
	
	public String getOwnersTitle() {
		return ownersTitle.getText();
	}
	
	public void viewOwnerInfo() {
		georgeFranklin.click();
	}
	
	
	

}
