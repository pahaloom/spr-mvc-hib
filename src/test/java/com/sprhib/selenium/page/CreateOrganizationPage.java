package com.sprhib.selenium.page;

import org.openqa.selenium.WebElement;

public class CreateOrganizationPage {
	private WebElement organization;
	private WebElement name;

	public void createOrganization(String organizationName) {
		name.sendKeys(organizationName);
		organization.submit();
	}
}
