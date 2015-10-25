package com.sprhib.selenium;

import com.sprhib.selenium.page.CreateOrganizationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

public class OrganizationCreate {
	public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8084/spring/organization/add.html");

		CreateOrganizationPage page = PageFactory.initElements(driver, CreateOrganizationPage.class);
		page.createOrganization("Hello Selenium");
	}
}
