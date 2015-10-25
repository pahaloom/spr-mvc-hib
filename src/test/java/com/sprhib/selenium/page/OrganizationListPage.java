package com.sprhib.selenium.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrganizationListPage {
	@FindBy(tagName = "table")
	private WebElement table;

	public void deleteOrganization(String organizationName) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			System.out.println("Row " + row.getText());
			List<WebElement> cols = row.findElements(By.tagName("td"));
			if (cols.size() == 3 && cols.get(1).getText().equals(organizationName)) {
				List<WebElement> links = cols.get(2).findElements(By.tagName("a"));
				if (links.size() == 2 && links.get(1).getText().equals("Delete")) {
					System.out.println("Clicking delete link for " + cols.get(1).getText());
					links.get(1).click();
					return;
				}
			}
		}
	}
}
