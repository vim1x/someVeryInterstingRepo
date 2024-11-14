package tests;

import com.beust.ah.A;
import com.codeborne.selenide.Configuration;
import objectPages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestInit {
    public HomePage homePage;
    public FlightPage flightPage;
    public FlightListPage flightListPage;
    public ApartmentsListPage apartmentsListPage;
    public ApartmentPage apartmentPage;
    @BeforeMethod
    public void setup() {
        open("https://booking.com");
        homePage = new HomePage();
        flightPage = new FlightPage();
        flightListPage = new FlightListPage();
        apartmentsListPage = new ApartmentsListPage();
        apartmentPage = new ApartmentPage();
        Configuration.timeout = 20000;
        if (homePage.dismissButton.exists()) {
            homePage.dismissButton.click();
        }
    }
    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}
