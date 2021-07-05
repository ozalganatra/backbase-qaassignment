package backbaseQA;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class crudArticle {
	
	private WebDriver driver;
	
	@Test(priority=1, description="Login into alert popup to access BBlog URL")
	public void handleAlert() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ozal\\Downloads\\chromedriver.exe");
		driver= new ChromeDriver();
		
    //launch the chrome browser to open the application url and give username and password in alert popup
	driver.get("https://candidatex:qa-is-cool@qa-task.backbasecloud.com/");
	
	//maximize the browser window
	driver.manage().window().maximize();
	}
	
	@Test(priority=2, description="Login to conduit")
	public void loginConduit() {
		//Click on Sign In button
		driver.findElement(By.xpath("//a[@routerlink='/login']")).click();
		
		//Enter Username and Password
		driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("testadmin@testadmin.com");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("testadmin");
		
		//Click on Submit
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	@Test(priority=3, description="Create New Article")
	public void createNewArticle() {
		//create a new article: Click New Article
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@routerlink='/editor']")));
		driver.findElement(By.xpath("//a[@routerlink='/editor']")).click();
		
		//Add the Title
		driver.findElement(By.xpath("//input[@formcontrolname='title']")).sendKeys("Covid-20 Article");
		
		//Article About
		driver.findElement(By.xpath("//input[@formcontrolname='description']")).sendKeys("Pandemic: Corona Virus");
		
		//body description
		driver.findElement(By.xpath("//textarea[@formcontrolname='body']")).sendKeys("Outburst started in November 2019. It is originated from a place called Wuhan. Its is widely spread virus and far-more dangerous.");
		
		//enter tags
		driver.findElement(By.xpath("//input[@placeholder='Enter tags']")).sendKeys("COVID20");
		
		//click publish article
		driver.findElement(By.xpath("//button[normalize-space()='Publish Article']")).click();
	}
	
	@Test(priority=4, description="Read Article")
	public void readArticle() {
		//Go back to home page
		driver.navigate().to("https://qa-task.backbasecloud.com/");
		
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		//go to global feeds
		driver.findElement(By.xpath("//a[normalize-space()='Global Feed']")).click();
		
		//find any article with the same title
		List<WebElement> allInputElements = driver.findElements(By.tagName("h1"));
		 
	    if(allInputElements.size() != 0) 
	    {
	    System.out.println(allInputElements.size() + " Elements found by TagName as input \n");
		    for(WebElement inputElement : allInputElements) 
		    {
		    	if(inputElement.getText().equals("Covid-20 Article")) {
		    		driver.findElement(By.xpath("//a/h1[contains(text(),'Covid-20 Article')]")).click();
		    		
		    	}
		    }
	    }
	}
	
	@Test(priority=5, description="Update Article")
	public void updateArticle() {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-outline-secondary'])[2]")).click();
		
		//updating body element:
		WebElement element = driver.findElement(By.xpath("//textarea[@formcontrolname='body']"));
		element.sendKeys(Keys.HOME + "Update Body: ");
		
		//update Title
		element = driver.findElement(By.xpath("//input[@formcontrolname='title']"));
		element.sendKeys(Keys.HOME + "Update Title: ");
		
		//update description 
		element = driver.findElement(By.xpath("//input[@formcontrolname='description']"));
		element.sendKeys(Keys.HOME + "Update Description: ");
		
		//click publish article
		driver.findElement(By.xpath("//button[normalize-space()='Publish Article']")).click();
	}
	
	@Test(priority=6, description="Delete Article")
	public void deleteArticle() {
		//create a brand new article and delete that article later
		driver.navigate().to("https://qa-task.backbasecloud.com");
		
		//Click on New Article
		driver.findElement(By.xpath("//a[@routerlink='/editor']")).click();
		
		//Add the Title
		driver.findElement(By.xpath("//input[@formcontrolname='title']")).sendKeys("Economy : India");
				
		//Article About
		driver.findElement(By.xpath("//input[@formcontrolname='description']")).sendKeys("Downfall in the economy");
				
		//body description
		driver.findElement(By.xpath("//textarea[@formcontrolname='body']")).sendKeys("Recently we have seen a steeped fall in the economy because most of the business in India were collapsed on Account of Corona virus. Major losses were seen in different sectors.");
				
		//enter tags
		//driver.findElement(By.xpath("//input[@placeholder='Enter tags']")).sendKeys("IndiaEconomy");
		
		WebElement publishArticle = driver.findElement(By.xpath("//button[normalize-space()='Publish Article']"));
		publishArticle.click();
		
		//To see the effect that article is created and then proceed with Delete
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		
		//proceed with delete 
		driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-outline-danger'])[2]")).click();	
		
		//close the browser
		driver.close();
	}

}
