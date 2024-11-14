package objectPages;

import com.codeborne.selenide.BaseElementsCollection;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FlightPage {
    public SelenideElement inputFromLoc = $("button[data-ui-name='input_location_from_segment_0']");
    public SelenideElement inputToLoc = $("button[data-ui-name='input_location_to_segment_0']");
    public SelenideElement autoDepartAirport = $("span[class='Text-module__root--variant-body_2___QdAaF Tags-module__item___ESG4A'");
    public SelenideElement inputSelector = $("input[class='AutoComplete-module__textInput___Qh3I- '");
    public ElementsCollection listOfAirports = $$("li[class='List-module__location___w04Kf'").filter(enabled);
    public SelenideElement searchButton = $("button[data-ui-name='button_search_submit'");
    public SelenideElement oneWayTrigger = $("div[data-ui-name='search_type_oneway'");


    public void pickDepartAirport(String airport) {
        inputFromLoc.shouldBe(clickable).click();
        autoDepartAirport.shouldBe(visible).click();
        inputSelector.sendKeys(airport);
        listOfAirports.shouldHave(sizeGreaterThan(0));
        for(SelenideElement element: listOfAirports) {
            if(element.shouldBe(visible).getText().contains(airport) && !element.shouldBe(visible).getText().contains("Усі аеропорти")) {
                element.shouldBe(clickable, Duration.ofSeconds(1)).click();
                break;
            }
        }
    }

    public void pickDestinationAirport(String airport) {
        inputToLoc.shouldBe(clickable).click();
        inputSelector.shouldBe(visible).sendKeys(airport);
        listOfAirports.shouldHave(sizeGreaterThan(0));
        for(SelenideElement element: listOfAirports) {
            if(element.shouldBe(visible).getText().contains(airport) && !element.shouldBe(visible).getText().contains("Усі аеропорти")) {
                element.shouldBe(clickable).click();
                break;
            }
        }
    }

    public void switchToOneWay() {
        oneWayTrigger.click();
    }
    public void findFlights() {

        searchButton.click();
    }
}
