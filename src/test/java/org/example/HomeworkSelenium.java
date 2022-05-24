package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class HomeworkSelenium {
    protected static WebDriver driver;
    @BeforeMethod
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/java/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
    @Test
    public void UserShouldBeAbleToRegisterSuccessfullyAndVerifyRegistrationMessage(){
        //For not changing Email everytime you run
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        String CurrentDate = formatter.format(date);
        System.out.println(CurrentDate);
        //Click on register
        ClickElement(By.className("ico-register"));
        //select Radio Button for Male or Female
        SelectRadioButton(By.id("gender-male"));
        //Enter Text for First Name
        typetext(By.xpath("//input[@name='FirstName']"),"joy");
        //Enter Text for Last Name
        typetext(By.id("LastName"),"Tomer");
        // select BirthDay
        Select Birthday = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Birthday.selectByIndex(3);
        // select BirthMonth
        Select BirthMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        BirthMonth.selectByValue("2");
        //select BirthYear
        Select BirthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        BirthYear.selectByValue("1977");
        //Enter Email
        typetext(By.id("Email"),"joy123"+CurrentDate+"@gmail.com");
        //Enter Password
        typetext(By.name("Password"),"Joy123");
        //Enter Confirm Password
        typetext(By.id("ConfirmPassword"),"Joy123");
        //Click on Register button
        ClickElement(By.name("register-button"));
        //Get Text from Registration Message
        getTextFromElement(By.className("result"));
        System.out.println(driver.findElement(By.className("result")).getText());
        //Verify Actual Message is Same as Expected Message
        String actualmessage=driver.findElement(By.className("result")).getText();
        String expectedmessage="Your registration completed";
        Assert.assertEquals(actualmessage,expectedmessage,"Registration is not working");
    }
    @Test
    public void UserShouldBeAbleToAddToCartForBuildYourOwnComputerAndVerifyInShoppingCart(){
        //Click on Computers
        ClickElement(By.xpath("//ul[1]/li[1]/a[@href='/computers']"));
        //Click on Desktops
        ClickElement(By.xpath("//a[@title='Show products in category Desktops']/parent::h2"));
        //Click on Add to cart for Build your own computer
        ClickElement(By.xpath("//div[@class='product-grid']//div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //Select Processor to 2.2 GHz
        Select Processor = new Select(driver.findElement(By.id("product_attribute_1")));
        Processor.selectByValue("1");
        //Select RAM to 2 GB
        Select RAM = new Select(driver.findElement(By.id("product_attribute_2")));
        RAM.selectByValue("3");
        //Select HDD Radio button 320 GB
        SelectRadioButton(By.id("product_attribute_3_6") );
        //Select OS Radio button vista premium
        SelectRadioButton(By.id("product_attribute_4_9"));
        //Select all Checkbox for Software
        SelectCheckBox(By.id("product_attribute_5_11"));
        SelectCheckBox(By.id("product_attribute_5_12"));
        //Click on Add to cart
        ClickElement(By.id("add-to-cart-button-1"));
        //Click on Shopping cart
        ClickElement(By.className("cart-label"));
        //Verify Shopping cart and Build your own computer in Shopping cart
        String actualText=driver.findElement(By.xpath("//h1[contains(text(),'Shopping cart')]")).getText();
        String expectedText="Shopping cart";
        Assert.assertEquals(actualText,expectedText,"This is not Shopping cart");
        String actualItemAdded=driver.findElement(By.className("product-name")).getText();
        String expectedItemToBeAdded="Build your own computer";
        Assert.assertEquals(actualItemAdded,expectedItemToBeAdded,"Wrong Item has been added");
    }
    @Test
    public void UserShouldBeAbleToSeeUSDollarOREuroSignOnPriceForBuildYourOwnComputerWhenSelectedInDropdownMenu(){
        //Selecting Euro
        Select CurrencyEuro = new Select(driver.findElement(By.id("customerCurrency")));
        CurrencyEuro.selectByValue("https://demo.nopcommerce.com/changecurrency/6?returnUrl=%2F");
        //Verify if the Price for Build your own computer is changed to Euro
        String actualPriceForBuildYourOwnComputerInEuro=driver.findElement(By.xpath("//span[contains(text(),'€1032.00')]")).getText();
        String expectedPriceForBuildYourOwnComputerInEuro="€1032.00";
        Assert.assertEquals(actualPriceForBuildYourOwnComputerInEuro,expectedPriceForBuildYourOwnComputerInEuro,"Wrong currency");
        //Selecting USDollar
        Select CurrencyUSDollar = new Select(driver.findElement(By.id("customerCurrency")));
        CurrencyUSDollar.selectByValue("https://demo.nopcommerce.com/changecurrency/1?returnUrl=%2F");
        //verify if the Price for Build your own computer is changed to USDollar
        String actualPriceForBuildYourOwnComputerInUSD=driver.findElement(By.xpath("//span[contains(text(),'$1,200.00')]")).getText();
        String expectedPriceForBuildYourOwnComputerInUSD="$1,200.00";
        Assert.assertEquals(actualPriceForBuildYourOwnComputerInUSD,expectedPriceForBuildYourOwnComputerInUSD,"Wrong currency");
    }

    @Test
    public void UserShouldBeAbleToReferAFriendForBuildYouOwnComputerAndVerifyYourMessageHasBeenSent(){
        //For not changing Email everytime you run
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        String currentdate = formatter.format(date);
        //Method called For Register successfully
        UserShouldBeAbleToRegisterSuccessfullyAndVerifyRegistrationMessage();
        //Click on Continue
        ClickElement(By.xpath("//a[contains(text(),'Continue')]"));
        //Click on Build your own computer
        ClickElement(By.xpath("//a[contains(text(),'Build your own computer')]"));
        //Click on Email a Friend
        ClickElement(By.xpath("//button[contains(text(),'Email a friend')]"));
        typetext(By.id("FriendEmail"),"loy123"+currentdate+"@gmail.com");
        // Enter comment in Personal message
        typetext(By.id("PersonalMessage"),"This is a very good product");
        //Click on Send Email
        ClickElement(By.xpath("//button[contains(text(),'Send email')]"));
        //Verify Your message has been sent
        getTextFromElement(By.className("result"));
        System.out.println(driver.findElement(By.className("result")).getText());
        //Verify Actual Message is Same as Expected Message
        String actualmessage1=driver.findElement(By.className("result")).getText();
        String expectedmessage1="Your message has been sent.";
        Assert.assertEquals(actualmessage1,expectedmessage1,"Message has not been sent");
    }

    //User defined methods for driver
    public static void typetext(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }
    public static void ClickElement(By by)
    {
        driver.findElement(by).click();
    }
    public static void getTextFromElement(By by)
    {
        driver.findElement(by).getText();
    }
    public static void SelectRadioButton(By by){
        WebElement radio1 = driver.findElement(by);
        radio1.click();
    }
    public static void SelectCheckBox(By by) {
        WebElement Checkbox = driver.findElement(by);
        Checkbox.click();
        for (int i = 0; i < 2; i++) {
            System.out.println(Checkbox.isSelected());
        }
    }

    //Explicit Wait methods
    // wait before it clicks the element
    public static void waittillclickable(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    // wait till element selected
    public static void waittillselsect(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }
    // wait till confirming url
    public static void urltobe(int time,String url){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlToBe(url));
    }
    //wait till url contains
    public static void waittillurlcontains(int time,String url){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(url));
    }
    // wait till title contain specific string
    public static void titlecontain(int time,String title){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.titleContains(title));
    }
    // wait till certain text selected at given location
    public static void waittillTexttobe(int time,String text,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBe(by,text));
    }
    // wait till title loaded as given
    public static void waittilltitleis(int time,String title){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.titleIs(title));
    }
    // wait till element becomes invisible
    public static void waittillinvisibility(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    // wail till element becomes present
    public static void waittillpresenceofelement(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    // wait till text becomes present
    public static void waittillitexttobepresent(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBePresentInElementValue(by,"First"));
    }
    // wait till element is visible
    public static void waittillvisibile_element(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    //wait till attribute to be
    public static void waittillattributetobe(int time,By by,String id,String path){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBe(by,id,path));
    }
    //wait till attribute contains
    public static void waittillattributecontains(int time,WebElement button,String name,String strvalue){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeContains(button,name,strvalue));
    }
    //wait till attribute to be not empty
    public static void waittillattributetobenotempty(int time,WebElement button,String name){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(button,name));
    }
    //wait till element is visible
    public static void waittillElementIsVisible(int time,WebElement register){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(register));
    }
    //wait till element is present
    public static void waittillalertispresent(int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    //wait till element selected
    public static void waittillelementselected(int time,WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }




    }
