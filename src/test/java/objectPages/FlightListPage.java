package objectPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class FlightListPage {
    public ElementsCollection listOfFlights = $$("div[data-testid='searchresults_card']");

    public boolean verifyDepartureDestinationOfFlights(String airport0, String airport1, boolean switchToOneWay) {
        if (listOfFlights.isEmpty()) {
            return false; // No flights available
        }

        boolean allValid = true;

        for (SelenideElement flight : listOfFlights) {
            SelenideElement departureAirport0 = flight.$("div[data-testid='flight_card_segment_departure_airport_0']");
            SelenideElement destinationAirport0 = flight.$("div[data-testid='flight_card_segment_destination_airport_0']");

            if (!departureAirport0.getText().equals(airport0)) {
                allValid = false;
            }
            if (!destinationAirport0.getText().equals(airport1)) {
                allValid = false;
            }
        }
        if(!switchToOneWay) {
            for(SelenideElement flight : listOfFlights) {
                SelenideElement departureAirport1 = flight.$("div[data-testid='flight_card_segment_departure_airport_1']");
                SelenideElement destinationAirport1 = flight.$("div[data-testid='flight_card_segment_destination_airport_1']");

                if (!departureAirport1.getText().equals(airport1)) {
                    allValid = false;
                }
                if (!destinationAirport1.getText().equals(airport0)) {
                    allValid = false;
                }
            }
        }

        return allValid;
    }

}
