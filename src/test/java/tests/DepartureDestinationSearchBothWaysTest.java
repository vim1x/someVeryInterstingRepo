package tests;

import DTO.AirportSearchDTO;
import Listeners.AllureListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

@Listeners(AllureListener.class)
public class DepartureDestinationSearchBothWaysTest extends TestInit{
    @DataProvider(name = "airportDataProvider")
    public Object[][] airportDataProvider() {
        return new Object[][] {
                { new AirportSearchDTO("WAW", "LTN", false)},
                { new AirportSearchDTO("WAW", "LTN", false) },
                { new AirportSearchDTO("WAW", "JFK", true) }
        };
    }

    // Parametrized test method
    @Test(dataProvider = "airportDataProvider")
    public void departureDestinationSearchTest(AirportSearchDTO airportSearchDTO) {
        // Move CheckDepartureDestinationSearch logic into the test method
        homePage.goToFlights();
        flightPage.pickDepartAirport(airportSearchDTO.airport0);
        flightPage.pickDestinationAirport(airportSearchDTO.airport1);

        if(airportSearchDTO.isOneWay)
            flightPage.switchToOneWay();
        flightPage.findFlights();
        flightListPage.listOfFlights.shouldHave(sizeGreaterThan(0));

        // Now verify the departure and destination
        boolean isCorrect = flightListPage.verifyDepartureDestinationOfFlights(airportSearchDTO.airport0, airportSearchDTO.airport1, airportSearchDTO.isOneWay);
        Assert.assertTrue(isCorrect, "Departure airport or destination airport is incorrect for: " + airportSearchDTO.airport0 + " to " + airportSearchDTO.airport1);
    }
}
