package com.swt.init.service;


import com.swt.init.model.Details;
import com.swt.init.properties.AppProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Scrapper {

	
	//private final static Logger logger = LoggerFactory.getLogger(CarWashScrapper.class);

    ChromeDriver driver;


    public AppProperties mProperties;

    public Scrapper(AppProperties mProperties) {

    	this.mProperties = mProperties;
    }
    



	public List<Details> run(String carWashCity, String carWashStore) {
		// TODO Auto-generated method stub
		System.out.println("From run "+mProperties.getChromeDriverPath());
		System.setProperty("webdriver.chrome.driver", mProperties.getChromeDriverPath());
		ChromeDriver driver = new ChromeDriver();
		boolean selectValue =getData(carWashCity, carWashStore, driver);
		List<Details> detailsList = new ArrayList<>();
		if(selectValue) {
			
			
			List<WebElement> address = driver.findElements(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_divImage\"]/div[1]/div/div[2]/p"));
			List<WebElement> email = driver.findElements(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_divImage\"]/div[1]/div/div[2]/p[2]"));
			List<WebElement> phoneNumber = driver.findElements(By.id("ctl00_ContentPlaceHolder1_divPhoneNumber"));
			List<WebElement> workingHrs = driver.findElements(By.xpath("//*[@id=\"store_locator_mid_div\"]/span/p"));
			//String workingTime = driver.findElement(By.xpath("//*[@id=\"store_locator_mid_div\"]/span/p/text()[1]")).getText();
			//String workingDay = driver.findElement(By.xpath("//*[@id=\"store_locator_mid_div\"]/span/p/text()[2]")).getText();
			
			
			
			Details details= new Details();
			
			details.setCity(carWashCity);
			details.setStore(carWashStore);
			if(address.size()>0) {
				details.setAddress(driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_divImage\"]/div[1]/div/div[2]/p")).getText());
				
			}
			if(email.size()>0) {
				details.setEmail(driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_divImage\"]/div[1]/div/div[2]/p[2]")).getText());
			}
			if(phoneNumber.size()>0) {
				details.setPhoneNumber(driver.findElement(By.id("ctl00_ContentPlaceHolder1_divPhoneNumber")).getText());
			}
			if(workingHrs.size()>0 ) {
				
				
				String[] time =workingHrs.get(0).getText().toString().split("\n") ;
				
				details.setWorkingHrs(time[0]+" "+time[1]+" "+time[2]);
			}
			
			
			detailsList.add(details);
		//	logger.info("CITY: "+details.getCity()+"STORE: "+details.getStore()+"ADDRESS: "+details.getAddress()+"PHONE NUMBER: "+details.getPhoneNumber()+"EMAIL: "+details.getEmail()+"WORKING HRS:"+details.getWorkingHrs());
			
			driver.quit();
			return detailsList;
			
			
		}else {
			//logger.info("----ERROR----");
			return null;
		}
		
	}
	
	private boolean getData(String city, String store, ChromeDriver driver) {
        //logger.info("logging in");
        driver.get("http://carcarestores.3mindia.co.in/store-locator/gurgaon");
        waitUntilPageLoaded(driver);
        
        
        int retry = 5;
        while (retry > 0 && driver.findElements(By.name("ctl00$ContentPlaceHolder1$ddlCity")).size() == 0) {
            driver.navigate().refresh();
            waitUntilPageLoaded(driver);
            retry--;
        }
        
        Select selectCity = new Select( driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlCity")));
        selectCity.selectByVisibleText(city);
        waitUntilPageLoaded(driver);
        Select selectStore = new Select( driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlStores")));
        selectStore.selectByVisibleText(store);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_imgbtnGo")).click();
        
        waitUntilPageLoaded(driver);
        if (driver.findElements(By.className("text-danger")).size() > 0) {
          //  logger.error("Error logging in to : {}",driver.getTitle());
            return false;
        }
        return true;
    }
	
	
	private void waitUntilPageLoaded(ChromeDriver webDriver) {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        new WebDriverWait(webDriver, 90).until(pageLoadCondition);
    }
}
