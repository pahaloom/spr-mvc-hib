package com.sprhib.selenium;

import com.sprhib.selenium.page.OrganizationListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

public class OrganizationDelete {
	public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:8084/spring/organization/list.html");

		OrganizationListPage page = PageFactory.initElements(driver, OrganizationListPage.class);
		page.deleteOrganization("Hello Selenium");
	}
}
