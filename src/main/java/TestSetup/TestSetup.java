package TestSetup;

import Utilities.ConfigFileReader;
import Utilities.Log;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static Utilities.Log.endLog;
import static Utilities.Log.startLog;


public class TestSetup {

    public static WebDriver webDriver;
    //public static String browser = System.getProperty("browser");
    public static File jsonTestData;
    public static File jsonExpectedResults;
    public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();


    public TestSetup() {
        PageFactory.initElements(webDriver, this);
    }

    @Parameters("browser")
    public void setupWebDriver(String br) {
        if (br == null) {
            Log.error("Incorrect Browser Name. Please provide a valid browser");
            System.exit(1);
        }else if(br.equalsIgnoreCase("chrome"))
        {
            chromeDriver();
        }else if(br.equalsIgnoreCase("firefox")){
            firefoxDriver();
        }
    }

    public void chromeDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(getAppURL());
        Log.info("Navigate to saucedemo.com ");
    }

    public void firefoxDriver()
    {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get(getAppURL());
        Log.info("Navigate to saucedemo.com ");
    }

    public static String getAppURL() {
        ConfigFileReader configFileReader = null;
        try {
            configFileReader = new ConfigFileReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = configFileReader.getBaseUrl();
        return url;
    }

    public static void initJsonTestData() {
        jsonTestData = new File(System.getProperty("user.dir") + "//src//test//java//TestData//staticMockTestData.json");
        jsonExpectedResults = new File(System.getProperty("user.dir") + "//src//test//java//TestData//expectedResults.json");
    }

    @BeforeSuite(alwaysRun = true)
    public void initiateBrowser() {
        Reporter.log("Testing Started");
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void initialize(String br)
    {
        setupWebDriver(br);
        initJsonTestData();
        ExtentTest extentTest = Reporting.ExtentManager.startTest(this.getClass().getName());
        parentTest.set(extentTest);
    }


    @BeforeMethod(alwaysRun = true)
    public void setup(ITestResult iTestResult) {
        ExtentTest child = parentTest.get().createNode(iTestResult.getMethod().getDescription());
        extTest.set(child);
        startLog(this.getClass().getName());
        Reporter.log(this.getClass().getSimpleName());

    }

    @AfterMethod(alwaysRun = true)
    public void terminateBrowser() {
        endLog(this.getClass().getName());
       // webDriver.close();
    }

    @Parameters("browser")
    @AfterSuite(alwaysRun = true)
    public void tearDown(String br) {
        if(br.equalsIgnoreCase("chrome")){
            webDriver.quit();
        }

    }
}
