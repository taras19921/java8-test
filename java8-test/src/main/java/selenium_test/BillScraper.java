package selenium_test;
import org.openqa.selenium.chrome.ChromeDriver;

public class BillScraper
{

    public static void main(String[] args)
    {
	System.setProperty("webdriver.chrome.driver", "/home/user/Java/Install/chromedriver");
	BillPage billPage = new BillPage(new ChromeDriver());
	billPage.scrapeBill("Bangalore Electricity Supply Company Ltd. (BESCOM)", "1234567");

    }

}
