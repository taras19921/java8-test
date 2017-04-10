package selenium_test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BillPage
{
    private final WebDriver driver;

    public BillPage(WebDriver driver) {
	this.driver = driver;
    }

    public void scrapeBill(String electricityBoard, String consumerId)
    {

	driver.get("http://paytm.com/electricity-bill-payment");
	// driver.findElement(By.id("input_1")).sendKeys(electricityBoard);
	// driver.findElement(By.id("input_25")).sendKeys(consumerId);
	try
	{
	    // WebElement formElement = driver.findElements(By.name("id"));
	    // List<WebElement> allFormChildElements =
	    // driver.findElements(By.tagName("input"));
	    List<WebElement> box1Class = driver.findElements(By.className("box1"));

	    for (WebElement box1 : box1Class)
	    {
		List<WebElement> inputs = box1.findElements(By.xpath("//*[@id]"));
		for (WebElement input : inputs)
		{
		    System.out.println("input.getText(): " + input.getText());
		}
		// box1.findElements(By.xpath("//*[@id]"));
	    }

	    // driver.findElements(By.xpath("//*[@id]"));
	    // List<WebElement> el = driver.findElements(By.cssSelector("*"));
	    //
	    // for ( WebElement e : el ) {
	    // e.getText();
	    // }
	    // WebElement allFormChildElements =
	    // driver.findElement(By.cssSelector("Input[class*='ng-pristine
	    // ng-valid md-input ng-empty ng-valid-maxlength ng-touched'"));
	    // for (int i = 0; i < allFormChildElements.size(); i++)
	    // {
	    // System.out.println(allFormChildElements.get(i).getText());
	    //
	    // }
//	    driver.findElement(By.id("input_11")).sendKeys(consumerId);

	} catch (Exception e)
	{
	    e.printStackTrace();
	    driver.findElement(By.id("input_25")).sendKeys(consumerId);
	}
	// driver.findElement(By
	// .className("ng-valid md-input ng-valid-maxlength ng-not-empty
	// ng-dirty ng-valid-parse ng-touched"))
	// .sendKeys(consumerId);
	// driver.findElement(By.className("md-primary btn-block mt10 md-button
	// md-default-theme")).submit();
    }

}
